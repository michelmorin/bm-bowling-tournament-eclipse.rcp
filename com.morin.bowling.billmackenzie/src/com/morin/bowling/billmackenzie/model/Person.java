package com.morin.bowling.billmackenzie.model;

public class Person {
	String name;
	String gender;
	String age;
	public Person(String name, String gender, String age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
