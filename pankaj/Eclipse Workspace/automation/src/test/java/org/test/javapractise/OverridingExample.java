package org.test.javapractise;

public class OverridingExample {

	public static void main(String[] args) {
		ParentClass object = new ParentClass();
		object.display();
		ParentClass object1 = new DrivedClass();
		object1.display();
		DrivedClass object2= new DrivedClass();
		object2.display();
	}
	
}

class ParentClass{
	public void display()
	{
		System.out.println("Parent Class Method");
	}
}


class DrivedClass extends ParentClass{
	public void display()
	{
		System.out.println("Drived Class Method");
	}
}