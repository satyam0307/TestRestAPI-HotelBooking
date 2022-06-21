package com.spring.HotelBooking.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
		name="User" , uniqueConstraints= {@UniqueConstraint(columnNames= {"email"})}
		)

public class User {
		
	
	@Id
	@GeneratedValue(
			strategy=GenerationType.IDENTITY
			)
	private Long userid;
	@Email
	private String email;
	@Column(name="fname", nullable=false)
		private String fname;
	
	@Column(name="lname", nullable=false)
		private String lname;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Booking> booking =new HashSet<>();
	

}
