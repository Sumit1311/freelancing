package org.test.javapractise;

public class InterfaceExample {

	public static void main(String[] args) {
		ExampleInterface test= new ImplementInterface();
		test.sum();
		ExampleInterface test1= new ImplementInterface2();
		test1.sum();
	}

}


interface ExampleInterface{
	int a=10;
	int b=10;
	void sum();
}

class ImplementInterface implements ExampleInterface{

	public void sum() {
		System.out.println(a+b);
	}
}

class ImplementInterface2 implements ExampleInterface{

	public void sum() {
		System.out.println(10+20);
	}
}
 