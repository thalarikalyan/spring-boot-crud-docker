package com.kalyan.errorcodes;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ErrorCode {
	public static final String ERROR_CODE = "NO_RECORD_404";
	public static final String UPDATE_ERROR_CODE = "NO_RECORD_500";
	public static final String UNABLE_TO_UPDATE_RECORD = "Unable To Update the Record";

}
