package org.test.javapractise;

public class SuperExample {

	public static void main(String[] args) {
		ParentClass1 object1 = new DrivedClass1();
		object1.display();
	}
	
}

class ParentClass1{
	public void display()
	{
		System.out.println("Parent Class Method");
	}
}


class DrivedClass1 extends ParentClass1{
	public void display()
	{
		super.display();
		System.out.println("Drived Class Method");
	}
}