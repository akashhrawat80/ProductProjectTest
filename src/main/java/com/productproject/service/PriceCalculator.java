package com.productproject.service;

import com.productproject.entities.Product;

public class PriceCalculator {

	private PriceCalculator() {
	}

	public static Product getPopulatedProduct(Product product) {
		return populateProduct(product);
	}

//	public static Product populateProduct(Product product) {
//		double discountedPrice = getDiscountedPrice(product);
//		setDiscount(product);
//		
//		setGstPrice(product, discountedPrice);
//		double afterGstPrice = getAfterGstPrice(product);
//		setFinalPriceIncludingDelivery(product, afterGstPrice);
//		return product;
//	}

	public static Product populateProduct(Product product) {
		//double discountedPrice = getDiscountedPrice(product);
		setDiscount(product);
		
		setGstPrice(product);
		double afterGstPrice = getAfterGstPrice(product);
		setFinalPriceIncludingDelivery(product, afterGstPrice);
		return product;
	}
	private static double getAfterGstPrice(Product product) {
		return product.getBasePrice() - product.getDiscount() + product.getCharges().getGst();
	}

	private static void setDiscount(Product product) {
		product.setDiscount(product.getDiscount() * product.getBasePrice());

	}

	private static void setFinalPriceIncludingDelivery(Product product, double afterGstPrice) {
		product.setFinalPrice(afterGstPrice + product.getCharges().getDelivery());
	}

//	private static void setGstPrice(Product product, double discountedPrice) {
//		product.getCharges().setGst(product.getCharges().getGst() * discountedPrice);
//	}
	private static void setGstPrice(Product product) {
		product.getCharges().setGst(product.getCharges().getGst() * product.getBasePrice());
	}

	private static double getDiscountedPrice(Product product) {
		return product.getBasePrice() - (product.getDiscount() * product.getBasePrice());
	}

}
