package com.niit.BoutiqueBack.DataAccess;


import java.util.List;

import com.niit.BoutiqueBack.model.Customer;


public interface CustomerDAO {
	
	public boolean addCustomer(Customer customer);
	public boolean updateCustomer(Customer customer);
	public boolean deleteCustomer(String emailId);
	public Customer showcustomer(String emailId);
    public List<Customer> showallcustomer();
	
	

}
