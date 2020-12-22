package com.tts.UsersAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	
	@Size(max = 20)
	private String firstName;
	@Size(min = 2)
	private String lastName;
	@Size(min= 4, max = 20)
	private String state;
}
	
	
	
	