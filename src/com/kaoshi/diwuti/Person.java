package com.kaoshi.diwuti;

public class Person {
	
	private String id;
	
	private int x;
	
	private int y;
	
	private int z;

	
	
	
	public Person(String id, int x, int y, int z) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	

}
