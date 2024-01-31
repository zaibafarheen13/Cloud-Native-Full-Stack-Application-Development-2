package com.lab5.my_lab5;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {
	
	@NotNull(message="id is required")
	@Min(1)
	@Max(100)
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Min(1)
	@NotNull(message = "Price is required")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}