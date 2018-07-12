package com.niit.BoutiqueBack.DataAccess;

import java.util.List;

import com.niit.BoutiqueBack.model.BillingAddress;

public interface BillingAddressDAO {
	
	
	public boolean add(BillingAddress billingAddress);
	public boolean delete(int id);
	BillingAddress show(int id);
	List<BillingAddress>list(int cartId);
	

	
	
	
}
