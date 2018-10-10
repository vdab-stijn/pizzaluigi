package be.vdab.pizzaluigi.exceptions;

public class RateClientException extends RuntimeException {

	/**
	 * Implements Serializable.
	 */
	private static final long serialVersionUID = 1383781785841819424L;

	public RateClientException() {
		super();
	}
	
	public RateClientException(final String message) {
		super(message);
	}
}
