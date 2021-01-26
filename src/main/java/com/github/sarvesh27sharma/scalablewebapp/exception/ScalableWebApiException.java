package com.github.sarvesh27sharma.scalablewebapp.exception;

import com.github.sarvesh27sharma.v1.model.ErrorsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class for ScalableWebApi
 *
 * @author Sarvesh Sharma
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScalableWebApiException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private transient ErrorsDTO errorsDTO;
    private HttpStatus httpStatus;

}
