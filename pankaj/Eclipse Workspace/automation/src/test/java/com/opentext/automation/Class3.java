package com.opentext.automation;

public class Class3 implements Test2, Test3{

	
	public Test2 print() {
		System.out.println("T");
		return new Class2();
	}

	public void display() {
		System.out.println("D");
		
	}

}

class Class2 extends Class3
{
	@Override
	public Test2 print() {
		System.out.println("H");
		return this;
	}
}
