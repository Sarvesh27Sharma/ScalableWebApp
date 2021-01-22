/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DB entity for Differed object
 * 
 * @author Sarvesh Sharma
 *
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DifferedEntity implements Serializable {

	private static final long serialVersionUID = 1l;

	private String left;
	private String right;
	@Id
	private long id;
}
