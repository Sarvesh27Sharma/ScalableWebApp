/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.entity.DifferedEntity;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.mapper.ScalableWebAppMapper;
import com.github.sarvesh27sharma.scalablewebapp.repository.DifferedRepository;
import com.github.sarvesh27sharma.scalablewebapp.service.ScalableWebAppService;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;
import com.github.sarvesh27sharma.v1.model.DifferenceDTO;

import lombok.AllArgsConstructor;

/**
 * Implementation for ScalableWebAppService
 * 
 * @author Sarvesh Sharma
 *
 */
@Service
@AllArgsConstructor
public class ScalableWebAppServiceImpl implements ScalableWebAppService {

	private final ScalableWebAppMapper mapper;
	private final DifferedRepository repository;

	@Override
	public boolean saveLeftSideData(DifferedDTO differedDTO) throws ScalableWebApiException {
		DifferedEntity entity = mapper.mapDifferedDTOToEntity(differedDTO);
		save(entity);
		return true;
	}

	@Override
	public boolean saveRightSideData(DifferedDTO differedDTO) throws ScalableWebApiException {
		DifferedEntity entity = mapper.mapDifferedDTOToEntity(differedDTO);
		save(entity);
		return false;
	}

	@Override
	public ApiResponseDTO getById(Long id) throws ScalableWebApiException {
		Optional<DifferedEntity> optionalDifferedEntity = repository.findById(id);
		DifferedEntity entity = optionalDifferedEntity.orElseThrow(ScalableWebApiException::new);
		DifferedDTO differedDTO = mapper.mapDifferedEntityToDTO(entity);
		if (inValidateDifferedDTO(differedDTO)) {
			throw new ScalableWebApiException();
		}
		return findDiff(differedDTO);
	}

	private ApiResponseDTO findDiff(DifferedDTO differedDTO) {
		ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
		String left = differedDTO.getLeft();
		String right = differedDTO.getRight();
		apiResponseDTO.setStatus(getStatus(left, right));
		if (apiResponseDTO.getStatus().compareTo(ApiResponseDTO.StatusEnum.DIFFERENT) > 0) {
			apiResponseDTO.setDifferences(getDifferencesList(left, right));
		}
		return apiResponseDTO;
	}

	private boolean inValidateDifferedDTO(DifferedDTO differedDTO) {
		if (null == differedDTO.getLeft() || null == differedDTO.getRight())
			return true;
		return false;
	}

	private void save(DifferedEntity entity) {
		repository.save(entity);
	}

	private ApiResponseDTO.StatusEnum getStatus(String left, String right) {
		if (left.equals(right)) {
			return ApiResponseDTO.StatusEnum.EQUAL;
		} else if (left.length() != right.length()) {
			return ApiResponseDTO.StatusEnum.DIFFERENT_SIZE;
		}
		return ApiResponseDTO.StatusEnum.DIFFERENT;
	}

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
