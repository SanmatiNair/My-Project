package com.niit.BoutiqueBack.DataAccess;

import java.util.List;

import com.niit.BoutiqueBack.model.Cart;

public interface CartDAO

{

	boolean add(Cart cart);
	boolean delete(int id);
	List<Cart> show(int cartId);
	
}
	