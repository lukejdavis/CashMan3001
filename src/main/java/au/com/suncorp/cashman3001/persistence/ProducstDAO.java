package au.com.suncorp.cashman3001.persistence;

import java.util.ArrayList;
import java.util.List;

import au.com.suncorp.cashman3001.model.Product;

public class ProducstDAO {
	
	private static List<Product> products = new ArrayList<Product>();

	public void init() {
		Product p = new Product(1,"Phone");
		products.add(p);
		p = new Product(2,"Battery");
		products.add(p);
		p = new Product(3,"Charger");
		products.add(p);
		p = new Product(4,"Headphones");
		products.add(p);
	}
	
	public List<Product> getProducts() {
		if (products==null) products = new ArrayList<Product>();
		if (products.size()==0) init();
		return products;
	}
	
	public void addProduct(Product p) {
		if (products==null) products = new ArrayList<Product>();
		if (products.size()==0) init();
		for (Product pExisting : products) {
			if (pExisting.getId()==p.getId()) {
				pExisting.setName(p.getName());
				return;
			}
		}
		products.add(p);
	}
	
	public void removeProduct(int id) {
		if (products==null) products = new ArrayList<Product>();
		if (products.size()==0) init();
		for (Product pExisting : products) {
			if (pExisting.getId()==id) {
				products.remove(pExisting);
				return;
			}
		}
	}

	public Product getProduct(int id) {
		if (products==null) products = new ArrayList<Product>();
		if (products.size()==0) init();
		for (Product pExisting : products) {
			if (pExisting.getId()==id) {
				return pExisting;
			}
		}
		return new Product();
	}

	
}
