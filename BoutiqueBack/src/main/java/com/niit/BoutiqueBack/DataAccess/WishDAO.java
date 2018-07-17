package com.niit.BoutiqueBack.DataAccess;

import java.util.List;

import com.niit.BoutiqueBack.model.Wish;

public interface WishDAO {
	
	boolean add(Wish wish);
	boolean delete(int id);
	Wish get(int id);
	List<Wish> show(int cartId);


}
