package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import be.vdab.pizzaluigi.restclients.RateClient;

@RunWith (MockitoJUnitRunner.class)
public class EuroServiceTest {

	@Mock
	private RateClient dummyRateClient;
	private EuroService euroService;
	
	@Before
	public void before() {
		when(dummyRateClient.getDollarRate()).thenReturn(BigDecimal.valueOf(1.5));
		euroService = new DefaultEuroService(new RateClient[] {dummyRateClient});
	}
	
	@Test
	public void toDollar() {
		assertEquals(0, BigDecimal.valueOf(3).compareTo(
				euroService.toDollar(BigDecimal.valueOf(2))));
		verify(dummyRateClient).getDollarRate();
	}
}
