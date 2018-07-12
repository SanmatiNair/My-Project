package com.niit.BoutiqueBack.DataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.BoutiqueBack.model.Category;
import com.niit.BoutiqueBack.model.Product;

@Repository("ProductDAO")
@Transactional
public class ProductDAOIMPL implements ProductDAO {

	@Autowired
	SessionFactory sf;

	@Override
	public boolean addProduct(Product product) {
		try {
			sf.getCurrentSession().saveOrUpdate(product);;
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
	}

	
	@Override
	public boolean deleteProduct(String productName) {
		try {
			sf.getCurrentSession().delete(showProduct(productName));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	@Override
	public Product showProduct(String productName) {
		try {
			Product product=(Product)sf.getCurrentSession().createQuery("from Product where productName='"+productName+"'").uniqueResult();
			return product;
		}

		catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Product> showallProduct() {
		try {

			ArrayList<Product> product = (ArrayList<Product>) sf.getCurrentSession().createQuery("from Product").list();
			System.out.println(product.isEmpty());
			return product;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

	@Override
	public List<Product> searchCategory(int catid) 
	{
		try
		{
			ArrayList<Product> product = (ArrayList<Product>) sf.getCurrentSession().createQuery("from Product where catid="+catid).list();
			return product;
		}
			catch (Exception e)
			{
				return null;
			}
		}


	@Override
	public Product getProduct(int productId) {
		Product product = sf.getCurrentSession().get(Product.class,productId );
		
		return product;
	}



	
	}

