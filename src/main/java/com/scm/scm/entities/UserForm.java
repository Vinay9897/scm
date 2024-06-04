package com.scm.scm.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

	@NotNull
	@Size(min = 2, max = 20)
	private String userName;
	@Email(message = "must not be empty")
	@NotBlank
	private String email;

	@Size(min = 2, max = 10)
	private String password;
	@Size(min = 2, max = 100)
	private String about;
	@Size(min = 8, max = 12)
	private String phoneNumber;

}
