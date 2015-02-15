package com.jimcorp.tests;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {

		if(emp1 == emp2) {
			return 0;
		}
		
		int nameCompare = emp1.getName().compareTo(emp2.getName());
		if(nameCompare != 0) {
			return nameCompare;
		}
		else {
			int ageCompare = emp1.getAge() - emp2.getAge();
			if(ageCompare != 0) {
				return ageCompare;
			}
			else {
				int departmentCompare = emp1.getDepartment().compareTo(emp2.getDepartment());
				return departmentCompare;
			}
		}
	}
	
}
