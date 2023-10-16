package com.epiuse.mtaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)

public class UserDTO {
	private String personIdExternal;
	private String firstName;
	private String lastName;
}