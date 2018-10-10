package be.vdab.pizzaluigi.valueobjects;

public class Person {
	
	private String firstName;
	private String lastName;
	private int numberOfChildren;
	private boolean married;
	
	private Address address;
	
	public Person(
			final String firstName,
			final String lastName,
			final int numberOfChildren,
			final boolean married,
			final Address address) {
		setFirstName(firstName);
		setLastName(lastName);
		setNumberOfChildren(numberOfChildren);
		setMarried(married);
		setAddress(address);
	}
	
	public final void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public final String getFirstName() {
		return firstName;
	}
	
	public final void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	public final String getLastName() {
		return lastName;
	}
	
	public final void setNumberOfChildren(final int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	
	public final int getNumberOfChildren() {
		return numberOfChildren;
	}
	
	public final void setMarried(final boolean married) {
		this.married = married;
	}
	
	public final boolean isMarried() {
		return married;
	}
	
	public final void setAddress(final Address address) {
		this.address = address;
	}
	
	public final Address getAddress() {
		return address;
	}
	
	public final String getName() {
		return getFirstName() + " " + getLastName();
	}
}
