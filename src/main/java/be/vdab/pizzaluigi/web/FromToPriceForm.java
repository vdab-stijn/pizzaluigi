package be.vdab.pizzaluigi.web;

import java.math.BigDecimal;

public class FromToPriceForm {

	private BigDecimal from;
	private BigDecimal to;
	
	public final void setFrom(BigDecimal from) {
		this.from = from;
	}
	public final BigDecimal getFrom() {
		return from;
	}
	public final void setTo(BigDecimal to) {
		this.to = to;
	}
	public final BigDecimal getTo() {
		return to;
	}
}
