package com.reset.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResetPassword {
	private String name;
	private String oldPassword;
	private String newPassword;
}
