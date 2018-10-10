package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import be.vdab.pizzaluigi.exceptions.RateClientException;
import be.vdab.pizzaluigi.restclients.RateClient;

@Service
public class DefaultEuroService implements EuroService {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(DefaultEuroService.class);

	private final RateClient[] rateClients;
	
//	public DefaultEuroService(final RateClient rateClient) {
//		this(new RateClient[] { rateClient });
//	}
	
	public DefaultEuroService(final RateClient[] rateClients) {
		this.rateClients = rateClients;
	}
	
	/* (non-Javadoc)
	 * @see be.vdab.pizzaluigi.services.EuroService#toDollar(java.math.BigDecimal)
	 */
	@Override
	public final BigDecimal toDollar(final BigDecimal euro) {
		for (final RateClient rateClient : rateClients) {
			try {
				return euro.multiply(rateClient.getDollarRate())
						.setScale(2, RoundingMode.HALF_UP);
			}
			catch (final RateClientException rce) {
				LOGGER.error("Couldn't read the USD rate", rce);
			}
		}
		
		LOGGER.error("Couldn't obtain the USD rate from any known source !");
		
		return null;
	}
}
