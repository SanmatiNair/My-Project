package com.niit.BoutiqueBack.DataAccess;

import java.util.List;

import com.niit.BoutiqueBack.model.Product;


public interface ProductDAO {
	
	 public boolean addProduct(Product product);
	
	 public boolean deleteProduct(String productName);
	 public Product showProduct(String productName);
	 public List<Product> showallProduct();
	 public List<Product> searchCategory(int catid);

	public Product getProduct(int id);

}
