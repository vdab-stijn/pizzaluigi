package be.vdab.pizzaluigi.entities;

import java.math.BigDecimal;

public class Pizza {

	private long id;
	private String name;
	private BigDecimal price;
	private boolean spicy;
	
	public Pizza() {
		this(0, "", BigDecimal.ZERO, false);
	}
	
	public Pizza(
			final String name,
			final BigDecimal price,
			final boolean spicy) {
		this(0, name, price, spicy);
	}
	
	public Pizza(
			final long id,
			final String name,
			final BigDecimal price,
			final boolean spicy) {
		setId(id);
		setName(name);
		setPrice(price);
		setSpicy(spicy);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isSpicy() {
		return spicy;
	}

	public void setSpicy(boolean spicy) {
		this.spicy = spicy;
	}
}
