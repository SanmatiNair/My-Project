package com.niit.BoutiqueBack.DataAccess;

import java.util.List;

import com.niit.BoutiqueBack.model.CustomerOrder;

public interface OrderDAO {

	boolean insert(CustomerOrder order) ;
		


	List<CustomerOrder>viewAllOrder(int cartId);

	List<CustomerOrder>viewreceipt(String orderid);
}
