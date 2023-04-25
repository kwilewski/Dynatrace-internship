package com.kwilewski.nbpservice;


import com.kwilewski.nbpservice.controller.NbpServiceController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;


class NbpServiceApplicationTest {

	@Test
	void testExchangeRate() {
		assertEquals(new ResponseEntity<>(4.610899925231934d, HttpStatus.OK), new NbpServiceController().getExchangeRate("2023-04-20", "eur"));
		assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), new NbpServiceController().getExchangeRate("2021-11-14", "eur")); 						// holiday
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), new NbpServiceController().getExchangeRate("2021-11-15", "shdfhh"));
	}

	@Test
	void testAverageRate() {
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), new NbpServiceController().getMinMaxRate("esart", 10));
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), new NbpServiceController().getMinMaxRate("eur", 0));
	}

	@Test
	void testMajorDifference() {
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), new NbpServiceController().getMajorRateDifference("ahfd", 10));
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), new NbpServiceController().getMajorRateDifference("eur", 0));
	}


}
