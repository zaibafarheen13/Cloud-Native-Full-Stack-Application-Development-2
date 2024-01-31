package com.lab1.my_lab1;

public class Ticket {
	
	int seat_number;
    int price;
    int ticket_number;
    String ticket_type;
    
	public int getSeat_number() {
		return seat_number;
	}
	
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getTicket_number() {
		return ticket_number;
	}
	
	public void setTicket_number(int ticket_number) {
		this.ticket_number = ticket_number;
	}
	
	public String getTicket_type() {
		return ticket_type;
	}
	
	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}

}
