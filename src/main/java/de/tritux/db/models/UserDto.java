package de.tritux.db.models;


import lombok.Data;

@Data
public class UserDto {
	
	private Integer id;
	private String nom;
	private String mail;
	private String role;
}
