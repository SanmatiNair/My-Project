package com.niit.BoutiqueBack;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.niit.BoutiqueBack.DataAccess.CategoryDAO;
import com.niit.BoutiqueBack.model.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class TestCategoryDAO {
	@Autowired
	CategoryDAO categorydao;
	 Category category = new Category();
	
	

	@Before
	public void setUp() throws Exception {
		category.setCategoryName("Jeans");
		category.setCategoryDescription("Jeans Description");
	}

	@After
	public void tearDown() {
		categorydao.deleteCategory("Jeans");
	}

	@Test
	public void test() 
	{
		categorydao.addCategory(category);
		category=categorydao.showCategory("Jeans");
		category.setCategoryDescription("error");
		categorydao.addCategory(category);
		assertSame(category.getCategoryDescription(), categorydao.showCategory("Jeans").getCategoryDescription());
		List<Category> list = categorydao.showAllCategory();
		assertTrue("success", list.size()>0);
	}

}
