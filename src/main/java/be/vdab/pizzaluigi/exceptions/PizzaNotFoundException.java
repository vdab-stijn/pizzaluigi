package be.vdab.pizzaluigi.exceptions;

public class PizzaNotFoundException extends RuntimeException {

	/**
	 * Implements Serializable.
	 */
	private static final long serialVersionUID = 1383781785841819424L;

	public PizzaNotFoundException() {
		super();
	}
	
	public PizzaNotFoundException(final String message) {
		super(message);
	}
}
