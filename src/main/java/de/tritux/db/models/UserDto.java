package de.tritux.db.models;

import java.util.List;

import de.tritux.db.entities.Skills;
import lombok.Data;

@Data
public class UserDto {
	
	private Integer id;
	private String nom;
	private String mail;
//	private List<Skills> skills;
}
