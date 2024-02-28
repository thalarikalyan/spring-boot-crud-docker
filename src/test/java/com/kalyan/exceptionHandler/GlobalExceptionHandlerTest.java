package com.kalyan.exceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kalyan.error.Error;
import com.kalyan.exception.NoContactFoundException;
import com.kalyan.exception.UnableToUpdateRecord;

@SpringBootTest
public class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler exceptionHandler;

	@Mock
	private NoContactFoundException contactFoundException;

	@Mock
	private UnableToUpdateRecord unableToUpdateRecord;

	@Test
	public void handleNoDataFoundException() {
		NoContactFoundException contactFoundException = new NoContactFoundException("Error");

		ResponseEntity<Error> handleNoDataFoundException = exceptionHandler
				.handleNoDataFoundException(contactFoundException);
		assertEquals(HttpStatus.BAD_REQUEST, handleNoDataFoundException.getStatusCode());

	}

	@Test
	public void handleUnableToUpdateRecord() {
		UnableToUpdateRecord unableToUpdateRecord = new UnableToUpdateRecord("Error");
		ResponseEntity<Error> handleNoDataFoundException = exceptionHandler
				.handleUnableToUpdateRecord(unableToUpdateRecord);
		assertEquals(HttpStatus.BAD_REQUEST, handleNoDataFoundException.getStatusCode());

	}

}
