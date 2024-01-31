package com.lab1.my_lab1;

public class Customer {
	
	 String name;
	 String address;
	 Ticket ticket_instance;
	 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Ticket getTicket_instance() {
		return ticket_instance;
	}
	
	public void setTicket_instance(Ticket ticket_instance) {
		this.ticket_instance = ticket_instance;
	}

}
