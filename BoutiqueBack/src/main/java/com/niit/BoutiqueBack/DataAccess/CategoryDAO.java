package com.niit.BoutiqueBack.DataAccess;

	import java.util.List;

import com.niit.BoutiqueBack.model.Category;


	
	public interface CategoryDAO {
		

	
		public boolean addCategory(Category category);
		public List<Category> showAllCategory();
		public boolean deleteCategory(String categoryName);
		public Category showCategory(String categoryName);
		
		

	}