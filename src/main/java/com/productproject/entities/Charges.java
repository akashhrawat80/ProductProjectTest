package com.productproject.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Charges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Double gst;
	private Double delivery;


	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getDelivery() {
		return delivery;
	}

	public void setDelivery(Double delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "Charges [gst=" + gst + ", delivery=" + delivery + "]";
	}

	public Charges() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Charges(int id, Double gst, Double delivery) {
		super();
		this.id = id;
		this.gst = gst;
		this.delivery = delivery;
	}

	@Override
	public int hashCode() {
		return Objects.hash(delivery, gst, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Charges other = (Charges) obj;
		return Double.doubleToLongBits(delivery) == Double.doubleToLongBits(other.delivery)
				&& Double.doubleToLongBits(gst) == Double.doubleToLongBits(other.gst) && id == other.id;
	}	
}
