/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.entity.DifferedEntity;

/**
 * Mapping interface to map data between DifferedEntity & DifferedDTO
 * 
 * @author Sarvesh Sharma
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ScalableWebAppMapper {
	/**
	 * method to map a DifferedDTO object to DifferedEntity class
	 * 
	 * @param dto input DifferedDTO {@link DifferedDTO} object
	 * @return corresponding DifferedEntity {@link DifferedEntity} object
	 */
	DifferedEntity mapDifferedDTOToEntity(DifferedDTO dto);

	/**
	 * method to map a DifferedEntity object to DifferedDTO class
	 * 
	 * @param dto input DifferedEntity {@link DifferedEntity} object
	 * @return corresponding DifferedDTO {@link DifferedDTO} object
	 */
	DifferedDTO mapDifferedEntityToDTO(DifferedEntity entity);
}
