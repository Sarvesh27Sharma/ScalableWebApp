/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.exception;

import com.github.sarvesh27sharma.v1.model.ErrorsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Custom exception class for ScalableWebApi
 * 
 * @author Sarvesh Sharma
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScalableWebApiException extends Exception {

	private ErrorsDTO errorsDTO;

}
