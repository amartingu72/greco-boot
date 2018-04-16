package com.alnura.greco.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel(value="User", description="User model")
public class UserDTO {
	@ApiModelProperty(value = "User id", example="0", position=0)
	private int id;
	@ApiModelProperty(value = "User nickname", example="peter22", position=1)
	private String nickname;
	@ApiModelProperty(value = "User email", example="peter22@gmail.com", position=2)
	private String email;
	@ApiModelProperty(value = "Personal information", example="Phone number: 611111111", position=3)
	private String mydata;
	@ApiModelProperty(value = "Are adds welcomed?", position=4)
	private boolean adds;
}
