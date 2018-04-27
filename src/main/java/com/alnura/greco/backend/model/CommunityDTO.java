package com.alnura.greco.backend.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
//Both constructors are needed if I want also a default constructor. 
@NoArgsConstructor  
@AllArgsConstructor
@ApiModel(value="Community", description="Community model")
public class CommunityDTO {
	@ApiModelProperty(value = "Community id", example="0", position=0)
	private int id;
	@ApiModelProperty(value = "Available now?", example="true", position=1)
	private boolean available;

	@ApiModelProperty(value = "Name", example="Wall Street, 5", position=2)
	private String name;

	@ApiModelProperty(value = "Notes", example="This is a littles description about my community.", position=3)
	private String notes;

	@ApiModelProperty(value = "Zipcode", example="28047", position=4)
	private String zipcode;
	
	@ApiModelProperty(value = "Country name", example="SPAIN", position=5)
	private String country;

}
