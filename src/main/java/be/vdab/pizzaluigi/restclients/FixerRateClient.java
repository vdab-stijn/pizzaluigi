package be.vdab.pizzaluigi.restclients;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.vdab.pizzaluigi.exceptions.RateClientException;

@Qualifier("Fixer")
@Component
public class FixerRateClient implements RateClient {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(FixerRateClient.class);
	
	private final URL url;
	
	public FixerRateClient(@Value("${fixerRateURL}") final String site) {
		try {
			url = new URL(site);
		}
		catch (final MalformedURLException mue) {
			final String error = "Fixer URL is wrong (" + site + ")";
			LOGGER.error(error, mue);
			
			throw new RateClientException(error);
		}
	}
	
	@Override
	public BigDecimal getDollarRate() {
		try (final Scanner scanner = new Scanner(url.openStream())) {
			final String line = scanner.nextLine();
			
			int beginRate = line.indexOf("USD") + 5;
			int accolade = line.indexOf('}', beginRate);
			
			return new BigDecimal(line.substring(beginRate,  accolade));
		}
		catch (final IOException | NumberFormatException ex) {
			final String error = "Couldn't obtain the rates through fixer.io";
			LOGGER.error(error, ex);
			
			throw new RateClientException(error);
		}
	}

}
