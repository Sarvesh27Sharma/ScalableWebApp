/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.service;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;

/**
 * Service interface for Diff API
 * 
 * @author Sarvesh Sharma
 *
 */
public interface ScalableWebAppService {
	/**
	 * Saves the data on the left side.
	 * 
	 * @param differedDTO {@link DifferedDTO} input for diff-ed request
	 * @return returns true if the data is saved successfully and false if the data
	 *         is already present for left side
	 * @throws ScalableWebApiException {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	boolean saveLeftSideData(DifferedDTO differedDTO) throws ScalableWebApiException;

	/**
	 * Save the data on the right side.
	 * 
	 * @param differedDTO {@link DifferedDTO} input for diff-ed request
	 * @return returns true if the data is saved successfully and false if the data
	 *         is already present for left side
	 * @throws ScalableWebApiException {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	boolean saveRightSideData(DifferedDTO differedDTO) throws ScalableWebApiException;

	/**
	 * Retrieve DifferedDTO {@link DifferedDTO} for requested ID.
	 * 
	 * @param id for diff-ed request
	 * @return DifferedDTO {@link DifferedDTO} for given ID.
	 * @throws ScalableWebApiException {@link ScalableWebApiException} in case of
	 *                                 any exceptions
	 */
	ApiResponseDTO getById(Long id) throws ScalableWebApiException;

}
