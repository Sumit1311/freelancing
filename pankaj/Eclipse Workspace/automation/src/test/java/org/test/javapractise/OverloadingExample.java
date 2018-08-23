package org.test.javapractise;

public class OverloadingExample {

	
	public void display(int i)
	{
		System.out.println("This is integer " + i);
	}
	
	public void display(Double i)
	{
		System.out.println("This is Double " + i);
	}
	
	public void display(String i)
	{
		System.out.println("This is String " + i);
	}
	
	public static void main(String[] args) {
		OverloadingExample object = new OverloadingExample();
		object.display(10);
		object.display(10.2);
		object.display("Hello World!");
	}

}
