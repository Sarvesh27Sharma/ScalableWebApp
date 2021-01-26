package com.github.sarvesh27sharma.scalablewebapp.service;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;

/**
 * Service interface for Diff API
 *
 * @author Sarvesh Sharma
 */
public interface ScalableWebAppService {
    /**
     * Saves the data against the unique ID.
     *
     * @param differedDTO {@link DifferedDTO} input for diff-ed request
     * @return returns true if the data is saved successfully and false if the data
     * is already present for left side
     */
    boolean saveLeftSideData(DifferedDTO differedDTO);

    /**
     * Saves the data against the unique ID.
     *
     * @param differedDTO {@link DifferedDTO} input for diff-ed request
     * @return returns true if the data is saved successfully and false if the data
     * is already present for left side
     */
    boolean saveRightSideData(DifferedDTO differedDTO);

    /**
     * Retrieve DifferedDTO {@link DifferedDTO} for requested ID.
     * <p>
     * Method perform following operations: 1. Retreive the details from database 2.
     * Validate the request and throw error response in case of invalid request 3.
     * Calcluate diff for successful validated request
     *
     * @param id for diff-ed request
     * @return DifferedDTO {@link DifferedDTO} for given ID.
     * @throws ScalableWebApiException {@link ScalableWebApiException} in case of
     *                                 any exceptions
     */
    ApiResponseDTO getById(Long id) throws ScalableWebApiException;

}
