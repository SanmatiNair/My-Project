package com.niit.BoutiqueBack.DataAccess;


import java.util.List;

import com.niit.BoutiqueBack.model.Customer;
import com.niit.BoutiqueBack.model.UserCredentials;


public interface CustomerDAO {
	
	public boolean addCustomer(Customer customer);
	public boolean updateCustomer(Customer customer);
	public boolean deleteCustomer(String emailId);
	public Customer showcustomer(String emailId);
    public List<Customer> showallcustomer();
    public UserCredentials showcred(String email);
	public boolean saveorupdatepassword(UserCredentials uc);
	
	

}
