package com.lab2.my_lab2;

public class Department {
	int dept_id;
	String dept_name,dept_description;
	College college_instance;
	
	public Department(College college_instance) {
		this.college_instance=college_instance;
	}
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_description() {
		return dept_description;
	}
	public void setDept_description(String dept_description) {
		this.dept_description = dept_description;
	}
	public College getCollege_instance() {
		return college_instance;
	}
	public void setCollege_instance(College college_instance) {
		this.college_instance = college_instance;
	}

}
