package com.github.sarvesh27sharma.scalablewebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class for Differed object
 *
 * @author Sarvesh Sharma
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class DifferedDTO {

    private String left;
    private String right;
    private long id;
}
