/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "differed_entity")
public class DifferedEntity implements Serializable {

	private static final long serialVersionUID = 1l;

	@Column(name = "left_side_data")
	private String left;
	@Column(name = "right_side_data")
	private String right;
	@Column(name = "diff_id")
	@Id
	private long id;
}
