/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.entity.DifferedEntity;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.mapper.ScalableWebAppMapper;
import com.github.sarvesh27sharma.scalablewebapp.repository.DifferedRepository;
import com.github.sarvesh27sharma.scalablewebapp.service.ErrorKeys;
import com.github.sarvesh27sharma.scalablewebapp.service.ScalableWebAppService;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;
import com.github.sarvesh27sharma.v1.model.DifferenceDTO;
import com.github.sarvesh27sharma.v1.model.ErrorDTO;
import com.github.sarvesh27sharma.v1.model.ErrorsDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Implementation for ScalableWebAppService
 * 
 * @author Sarvesh Sharma
 *
 */
@Service
@AllArgsConstructor
@Log4j2
public class ScalableWebAppServiceImpl implements ScalableWebAppService {

	private final ScalableWebAppMapper mapper;
	private final DifferedRepository repository;

	@Override
	public boolean saveData(DifferedDTO differedDTO) throws ScalableWebApiException {
		DifferedEntity entity = mapper.mapDifferedDTOToEntity(differedDTO);
		log.info("entity to be persist :",entity);
		repository.save(entity);
		return true;
	}

	@Override
	public ApiResponseDTO getById(Long id) throws ScalableWebApiException {
		Optional<DifferedEntity> optionalDifferedEntity = repository.findById(id);
		if(optionalDifferedEntity.isPresent()) {
			DifferedDTO differedDTO = mapper.mapDifferedEntityToDTO(optionalDifferedEntity.get());
			inValidDifferedDTO(differedDTO);
			return findDiff(differedDTO);			
		}
		ErrorsDTO errorsDTO = new ErrorsDTO();
		ErrorDTO error = new ErrorDTO();
		error.setCode(ErrorKeys.ID_DOESNOT_EXISTS.getCode());
		error.setMessage(ErrorKeys.ID_DOESNOT_EXISTS.getMessage());
		errorsDTO.errors(Arrays.asList(error));
		throw new ScalableWebApiException(errorsDTO, HttpStatus.NOT_FOUND);
	}

	/**
	 * method to Validate the request and throw error response in case of invalid
	 * request
	 * 
	 * @param differedDTO input data to be validated
	 * @throws ScalableWebApiException instance of custom exception class in case of
	 *                                 invalid request
	 */
	private void inValidDifferedDTO(DifferedDTO differedDTO) throws ScalableWebApiException {
		ErrorsDTO errorsDTO = new ErrorsDTO();
			if (null == differedDTO.getLeft()) {
				ErrorDTO error = new ErrorDTO();
				error.setCode(ErrorKeys.LEFT_SIDE_DATA_DOESNOT_EXISTS.getCode());
				error.setMessage(ErrorKeys.LEFT_SIDE_DATA_DOESNOT_EXISTS.getMessage());
				errorsDTO.errors(Arrays.asList(error));

			}
			if (null == differedDTO.getRight()) {
				ErrorDTO error = new ErrorDTO();
				error.setCode(ErrorKeys.RIGHT_SIDE_DATA_DOESNOT_EXISTS.getCode());
				error.setMessage(ErrorKeys.RIGHT_SIDE_DATA_DOESNOT_EXISTS.getMessage());
				errorsDTO.errors(Arrays.asList(error));
			}
			throw new ScalableWebApiException(errorsDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * method to Calcuate diff for successful validated request
	 * 
	 * @param differedDTO input data to be diffed
	 * @return response as per OAS
	 */
	private ApiResponseDTO findDiff(DifferedDTO differedDTO) {
		ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
		String left = differedDTO.getLeft();
		String right = differedDTO.getRight();
		apiResponseDTO.setStatus(getStatus(left, right));
		if (apiResponseDTO.getStatus().compareTo(ApiResponseDTO.StatusEnum.DIFFERENT) > 0) {
			apiResponseDTO.setDifferences(getDifferencesList(left, right));
		}
		log.info("apiResponseDTO for input request id {} is {}",differedDTO.getId(),apiResponseDTO);
		return apiResponseDTO;
	}

	/**
	 * method to calculate status of diffed operation as per the required logic
	 * STATUS to be: 1. EQUAL = if both left and right side data are equal 2.
	 * DIFFERENT SIZE = if the side of left and right side data are of different
	 * size 3. DIFFERENT = if left and right size data are different
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	private ApiResponseDTO.StatusEnum getStatus(String left, String right) {
		if (left.equals(right)) {
			return ApiResponseDTO.StatusEnum.EQUAL;
		} else if (left.length() != right.length()) {
			return ApiResponseDTO.StatusEnum.DIFFERENT_SIZE;
		}
		return ApiResponseDTO.StatusEnum.DIFFERENT;
	}

	/**
	 * method to find the differences between the data as per required logic
	 * 
	 * @param left  left side data
	 * @param right right side data
	 * @return list of differences
	 */
	private List<DifferenceDTO> getDifferencesList(String left, String right) {
		List<DifferenceDTO> differences = new ArrayList<>();

		long initialOffset = 0;
		long length = 0;
		boolean isDifferent = false;

		for (int i = 0; i < left.length(); i++) {
			if (left.charAt(i) != right.charAt(i)) {
				if (!isDifferent) {
					initialOffset = i;
				}
				length += 1;
				isDifferent = true;
			} else {
				if (isDifferent) {
					DifferenceDTO dto = new DifferenceDTO();
					dto.setLength(length);
					dto.setInitialOffset(initialOffset);
					dto.setFinalOffset(length + initialOffset);
					differences.add(dto);
					isDifferent = false;
					length = 0;
				}
			}
		}
		if (isDifferent) {
			DifferenceDTO dto = new DifferenceDTO();
			dto.setLength(length);
			dto.setInitialOffset(initialOffset);
			dto.setFinalOffset(length + initialOffset);
			differences.add(dto);
		}
		return differences;
	}
}
