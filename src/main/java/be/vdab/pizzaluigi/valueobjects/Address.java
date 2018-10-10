package be.vdab.pizzaluigi.valueobjects;

public class Address {

	public String street;
	public String number;
	public int postalCode;
	public String communality;
	
	public Address(
			final String street,
			final String number,
			final int postalCode,
			final String communality) {
		setStreet(street);
		setNumber(number);
		setPostalCode(postalCode);
		setCommunality(communality);
	}
	
	public final void setStreet(final String street) {
		this.street = street;
	}
	public final String getStreet() {
		return street;
	}
	
	public final void setNumber(final String number) {
		this.number = number;
	}
	public final String getNumber() {
		return number;
	}
	
	public final void setPostalCode(final int postalCode) {
		this.postalCode = postalCode;
	}
	public final int getPostalCode() {
		return postalCode;
	}
	
	public final void setCommunality(final String communality) {
		this.communality = communality;
	}
	public final String getCommunality() {
		return communality;
	}
}
