package de.tritux.db.models;

import java.util.Date;

import lombok.Data;


@Data
public class EmploiDto {
    
	
	
	private Integer id;

	private String titre;
	 
	private Date dateDePublication;

    private String image;

}
