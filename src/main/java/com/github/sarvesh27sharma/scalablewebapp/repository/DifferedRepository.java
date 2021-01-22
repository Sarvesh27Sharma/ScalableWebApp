/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sarvesh27sharma.scalablewebapp.entity.DifferedEntity;

/**
 * JPA Repository interface for the {@link DifferedEntity}.
 * 
 * @author Sarvesh Sharma
 *
 */
@Repository
public interface DifferedRepository extends JpaRepository<DifferedEntity, Long> {

}
