package com.github.sarvesh27sharma.scalablewebapp.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Sarvesh Sharma
 */
@AllArgsConstructor
@Getter
public enum ErrorKeys {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal Server Error"),
    LEFT_SIDE_DATA_DOESNOT_EXISTS("LEFT_SIDE_DATA_DOESNOT_EXIST", "Left side data does not exists for given ID"),
    RIGHT_SIDE_DATA_DOESNOT_EXISTS("RIGHT_SIDE_DATA_DOESNOT_EXIST", "Right side data does not exists for given ID"),
    ID_DOESNOT_EXISTS("ID_DOESNOT_EXIST", "ID does not exists");

    private final String code;
    private final String message;
}
