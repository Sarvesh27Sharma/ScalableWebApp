/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.controller;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.service.ScalableWebAppService;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;
import com.github.sarvesh27sharma.v1.model.InputDataDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Controller class for Diff API
 * 
 * @author Sarvesh Sharma
 *
 */
@RestController
@RequestMapping("/v1/diff")
@Log4j2
@AllArgsConstructor
public class ScalableWebAppController {

	private final ScalableWebAppService scalableWebAppService;

	/**
	 * method for uploading left data to be diff-ed
	 * 
	 * @param id   diff id
	 * @param data data on left side to be diff-ed
	 * @return response for the api request {@link ApiResponseDTO}
	 * @throws ScalableWebApiException ScalableWebApiException
	 *                                 {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	@PostMapping("/{id}/left")
	public ResponseEntity<ApiResponseDTO> left(@NotNull @PathVariable(value = "ID", required = true) long id,
			@RequestBody InputDataDTO data) throws ScalableWebApiException {
		scalableWebAppService.saveLeftSideData(new DifferedDTO(data.getData(), null, id));
		ApiResponseDTO dto = null;
		log.info("id in left:{}", id);
		log.info("data in left:{}", data);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * method for uploading right data to be diff-ed
	 * 
	 * @param id   diff id
	 * @param data data on right side to be diff-ed
	 * @return response for the api request {@link ApiResponseDTO}
	 * @throws ScalableWebApiException ScalableWebApiException
	 *                                 {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	@PostMapping("/{id}/right")
	public ResponseEntity<ApiResponseDTO> right(@NotNull @PathVariable(value = "ID", required = true) long id,
			@RequestBody InputDataDTO data) throws ScalableWebApiException {
		scalableWebAppService.saveLeftSideData(new DifferedDTO(null, data.getData(), id));
		ApiResponseDTO dto = null;
		log.info("id in right:{}", id);
		log.info("data in right:{}", data);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * method for retrieving result of diff-ed operation
	 * 
	 * @param id diff id
	 * @return response for the api request {@link ApiResponseDTO}
	 * @throws ScalableWebApiException ScalableWebApiException
	 *                                 {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO> getDiffById(@NotNull @PathVariable(value = "ID", required = true) long id)
			throws ScalableWebApiException {
		ApiResponseDTO dto = scalableWebAppService.getById(id);
		log.info("id :{}", id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
