package com.jimcorp.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee implements Comparable<Employee>{

	private String name;
	private int age;
	private String department;
	
	public Employee(String name, int age, String department) {
		this.setName(name);
		this.setAge(age);
		this.setDepartment(department);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public int compareTo(Employee emp2) {
		EmployeeComparator comp = new EmployeeComparator();

		return comp.compare(this, emp2);
	}
	
	
	@Override
	public String toString() {
		return String.format("<%s, %s, %s>", getName(), getAge(), getDepartment());
	}
	
	
	public static void main(String[] args) {
		Employee emp1 = new Employee("John Smith", 45, "Information Technology");
		Employee emp2 = new Employee("John Smith", 45, "Information Panda");
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp1);
		list.add(emp2);
		
		System.out.println("Unsorted list:");
		printList(list);
		Collections.sort(list);
		System.out.println("Sorted list:");
		printList(list);
	}
	
	public static <T> void printList(List<T> list) {
		for(T elem : list) {
			System.out.print(elem + " ");
		}
		System.out.println();
	}
}
