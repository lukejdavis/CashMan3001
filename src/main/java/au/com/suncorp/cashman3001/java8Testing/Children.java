package au.com.suncorp.cashman3001.java8Testing;

import java.util.List;

public class Children {
	private List<Customer> parents;
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	public List<Customer> getParents() {
		return parents;
	}
	public void setParents(List<Customer> parents) {
		this.parents = parents;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
