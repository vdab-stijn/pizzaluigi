package be.vdab.pizzaluigi.restclients;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.vdab.pizzaluigi.exceptions.RateClientException;

@Qualifier("ECB")
@Component
public class ECBRateClient implements RateClient {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(ECBRateClient.class);

	private final URL url;
	
	public ECBRateClient(@Value("${ECBRateURL}") final String site) {
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
		final XMLInputFactory factory = XMLInputFactory.newInstance();
		
		try (final InputStream stream = url.openStream()) {
			final XMLStreamReader reader = factory.createXMLStreamReader(stream);
			
			try {
				while (reader.hasNext()) {
					if (reader.next() == XMLStreamConstants.START_ELEMENT
						&& "USD".equalsIgnoreCase(
								reader.getAttributeValue(null, "currency"))) {
						LOGGER.info("Acquired USD rate through ECB");
						
						return new BigDecimal(
								reader.getAttributeValue(null, "rate"));
					}
				}
				
				String error = "No USD rate found in ECB XML file !";
				LOGGER.error(error);
				throw new RateClientException(error);
			}
			finally {
				reader.close();
			}
		}
		catch (final IOException | NumberFormatException | XMLStreamException ex) {
			String error = "Couldn't read USD rate through ECB";
			LOGGER.error(error, ex);
			throw new RateClientException(error);
		}
	}
}
