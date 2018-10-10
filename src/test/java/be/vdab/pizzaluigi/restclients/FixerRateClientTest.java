package be.vdab.pizzaluigi.restclients;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(FixerRateClient.class)
@PropertySource("application.properties")
public class FixerRateClientTest {
	
	@Autowired
	private FixerRateClient client;
	
	@Test
	public final void rateMustBePositive() {
		assertTrue(client.getDollarRate().compareTo(BigDecimal.ZERO) > 0);
	}
}
