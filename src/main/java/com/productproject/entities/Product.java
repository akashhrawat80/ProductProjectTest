package com.productproject.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Enumerated
	private Type type;
	@ManyToOne(targetEntity = Category.class)
	private Category category;
	private double basePrice;
	private double discount;
	@OneToOne()
	private Charges charges;
	private double finalPrice;

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Charges getCharges() {
		return charges;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	public enum Type {
		LAPTOP, WASHING_MACHINE, T_SHIRT, SOFA_SET
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", category=" + category + ", basePrice="
				+ basePrice + ", discount=" + discount + ", charges=" + charges + ", finalPrice=" + finalPrice + "]";
	}

	public Product() {

	}

	public Product(Long id, String name, Type type, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double price) {
		this.basePrice = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(basePrice, category, charges, discount, finalPrice, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Double.doubleToLongBits(basePrice) == Double.doubleToLongBits(other.basePrice)
				&& Objects.equals(category, other.category) && Objects.equals(charges, other.charges)
				&& Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Double.doubleToLongBits(finalPrice) == Double.doubleToLongBits(other.finalPrice)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && type == other.type;
	}

}
