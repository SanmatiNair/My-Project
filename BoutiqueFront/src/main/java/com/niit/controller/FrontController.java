package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.BoutiqueBack.DataAccess.BillingAddressDAO;
import com.niit.BoutiqueBack.DataAccess.CartDAO;
import com.niit.BoutiqueBack.DataAccess.CategoryDAO;
import com.niit.BoutiqueBack.DataAccess.CustomerDAO;
import com.niit.BoutiqueBack.DataAccess.OrderDAO;
import com.niit.BoutiqueBack.DataAccess.ProductDAO;
import com.niit.BoutiqueBack.DataAccess.WishDAO;
import com.niit.BoutiqueBack.model.BillingAddress;
import com.niit.BoutiqueBack.model.Cart;
import com.niit.BoutiqueBack.model.Category;
import com.niit.BoutiqueBack.model.Customer;
import com.niit.BoutiqueBack.model.CustomerOrder;
import com.niit.BoutiqueBack.model.Product;
import com.niit.BoutiqueBack.model.UserCredentials;
import com.niit.BoutiqueBack.model.Wish;

@Controller
public class FrontController {

	@Autowired
	CustomerDAO CustomerDAO;

	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;

	@Autowired
	CartDAO cartDAO;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	private WishDAO wishDAO;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	BillingAddressDAO billingAddressDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public String index(Model M) {

		M.addAttribute("title", "home");
		M.addAttribute("userClickHome", true);
		System.out.println(categorydao.showAllCategory());
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "about")
	public String about(Model M) {

		M.addAttribute("title", "About Us");
		M.addAttribute("userClickAbout", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "contact")
	public String contact(Model M) {

		M.addAttribute("title", "Contact Us");
		M.addAttribute("userClickContact", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "sendmail")
	public String sendmail(HttpServletRequest request) {
		try {
			String recipientAddress = "blushboutiquecenter@gmail.com";
			String uname = request.getParameter("uname");
			String usubject = request.getParameter("usubject");
			String uphno = request.getParameter("uphno");
			String umessage = request.getParameter("umessage");
			String finalmessage = "Hi Admin, " + umessage + " Contact me in:" + uphno + "\n\n\n regards\n\n" + uname;
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject(usubject);
			email.setText(finalmessage);
			mailSender.send(email);
		} catch (Exception e) {

		}
		return "redirect:/contact";
	}

	@RequestMapping(value = "login")
	public String login(Model M) {

		M.addAttribute("title", "Sign In");
		M.addAttribute("userClickLogin", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "flogin")
	public String faillogin(Model M) {

		M.addAttribute("title", "Sign In");
		M.addAttribute("userClickLogin", true);
		M.addAttribute("loginerror", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "loginsucess")
	public String loginsuccess(HttpSession session, Model M)

	{
		if (session.getAttribute("pid") == null) {
			String useremail = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer c = CustomerDAO.showcustomer(useremail);
			Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
			for (GrantedAuthority permission : authority) {
				if (permission.getAuthority().equals("ROLE_USER")) {
					session.setAttribute("username", c.getName());
					session.setAttribute("useremail", c.getEmailId());
					session.setAttribute("usercartid", c.getCartid());
					session.setAttribute("Userlogin", true);
					session.setAttribute("cartsize", cartDAO.show(c.getCartid()).size());
					return "redirect:/productgrid";
				} else {
					session.setAttribute("username", "Administrator");
					session.setAttribute("Userlogin", false);
					return "redirect:/";
				}
			}
		}
		else
		{
			String useremail = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer c = CustomerDAO.showcustomer(useremail);
			session.setAttribute("username", c.getName());
			session.setAttribute("useremail", c.getEmailId());
			session.setAttribute("usercartid", c.getCartid());
			session.setAttribute("Userlogin", true);
			session.setAttribute("cartsize", cartDAO.show(c.getCartid()).size());
			int proid = Integer.parseInt(session.getAttribute("pid").toString());
			int qnty = Integer.parseInt(session.getAttribute("qid").toString());
			return "redirect:addproduct/"+proid+"?qnty="+qnty;
			
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "registration")
	public String registration(Model M) {

		M.addAttribute("title", "Sign Up");
		M.addAttribute("customer", new Customer());
		M.addAttribute("userClickRegistration", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "productgrid")
	public String productgrid(Model M) {
		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "ProductGrid");
		M.addAttribute("userClickProductGrid", true);

		return "page";

	}

	@RequestMapping(value = "ADMIN/products")
	public String product(Model M) {

		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("product", new Product());
		M.addAttribute("title", "Product");
		M.addAttribute("userClickProduct", true);
		M.addAttribute("edit", false);
		return "page";

	}

	@RequestMapping(value = "info/{productName}")
	public String productInfo(@PathVariable String productName, Model M) {

		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("product", productdao.showProduct(productName));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "Product Info");
		M.addAttribute("userClickProductInfo", true);

		return "page";

	}

	@RequestMapping(value = "prodcat/{catid}")
	public String productcat(@PathVariable int catid, Model M) {

		M.addAttribute("productlist", productdao.searchCategory(catid));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "Productcat");
		M.addAttribute("userClickProductcat", true);

		return "page";

	}

	@RequestMapping(value = "ADMIN/setproduct")
	public String setpro(@Valid @ModelAttribute("product") Product product, BindingResult result, Model M) {
		System.out.println("print me");
		if (result.hasErrors()) {
			System.out.println("inside error");
			System.out.println(result.getErrorCount());
			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("product", product);
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			return "page";
		}

		try {
			System.out.println("inside try");

			productdao.addProduct(product);
			uploadfile(product.getProductId(), product.getPimage());
			return "redirect:/ADMIN/products";
		} catch (Exception e) {
			System.out.println("inside catch" + e.getMessage());

			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("product", product);
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			return "page";
		}

	}

	void uploadfile(int productId, MultipartFile f) throws Exception {
		String path = "D:\\Eclipse\\Eclipse-Workspace\\BoutiqueFront\\src\\main\\webapp\\resources\\pimages\\";
		path = path + String.valueOf(productId + ".jpg");
		if (!f.isEmpty()) {
			byte[] b = f.getBytes();
			System.out.println(b);
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bs.write(b);
			bs.close();
			Thread.sleep(10000);
		}
	}

	@RequestMapping(value = "ADMIN/delprod/{productName}")
	public String delprod(@PathVariable String productName, Model M) {
		try {
			productdao.deleteProduct(productName);
			Product product = productdao.showProduct(productName);
			String path = "D:\\Eclipse\\Eclipse-Workspace\\BoutiqueFront\\src\\main\\webapp\\resources\\pimages\\";
			path = path + String.valueOf(product.getProductId() + ".jpg");
			Path paths = Paths.get(path);
			if (Files.exists(paths)) {
				try {
					Files.delete(paths);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return "redirect:/ADMIN/products";
		} catch (Exception e) {
			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", false);
			M.addAttribute("product", new Product());
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			M.addAttribute("edit", false);
			return "page";
		}
	}

	@RequestMapping(value = "ADMIN/editprod/{productName}")
	public String editProduct(@PathVariable String productName, Product product, Model M) {

		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("product", productdao.showProduct(productName));
		M.addAttribute("title", "Edit Product");
		M.addAttribute("userClickProduct", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "ADMIN/Category")
	public String category(Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", false);
		M.addAttribute("category", new Category());
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}

	@RequestMapping(value = "ADMIN/setcategory")
	public String setcat(@Valid @ModelAttribute("category") Category category, BindingResult result, Model M) {
		System.out.println("print me");
		if (result.hasErrors()) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", category);
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";

		} else {
			try {
				categorydao.addCategory(category);
				return "redirect:/ADMIN/Category";
			} catch (Exception e) {
				M.addAttribute("categorylist", categorydao.showAllCategory());
				M.addAttribute("status", "true");
				M.addAttribute("edit", false);
				M.addAttribute("category", new Category());
				M.addAttribute("title", "Category");
				M.addAttribute("userClickCategory", true);
				return "page";
			}
		}

	}

	@RequestMapping(value = "ADMIN/delcat")
	public String delcat(@RequestParam(name = "cname") String Categoryname, Model M) {
		try {
			categorydao.deleteCategory(Categoryname);
			return "redirect:/ADMIN/Category";
		} catch (Exception e) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", new Category());
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";
		}
	}

	@RequestMapping(value = "ADMIN/editcat")
	public String showcategory(@RequestParam(name = "cname") String Categoryname, Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("category", categorydao.showCategory(Categoryname));
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}

	@RequestMapping(value = "setcustomer")
	public String setcustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			model.addAttribute("edit", false);

			return "page";
		}

		try {
			CustomerDAO.addCustomer(customer);
			return "redirect:/home";
		} catch (Exception e) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			return "page";
		}

	}

	@RequestMapping(value = "addproduct/{id}")
	public String addcart(@PathVariable int id, @RequestParam(value = "qnty") int qnty, Model M, HttpSession session) {
		
	if(session.getAttribute("usercartid")!= null) {
	
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Product product = productdao.getProduct(id);
		if (product.getQuantity() >= qnty) {

			for (Cart cartItem : cartlist) {
				if (cartItem.getPid() == id) {
					cartItem.setQty(qnty);
					cartItem.setTotal(qnty * product.getPrice());
					cartDAO.add(cartItem);
					return "redirect:/cart/viewcart";
				}
			}

			Cart cart = new Cart();
			cart.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
			cart.setPid(product.getProductId());
			cart.setPname(product.getProductName());
			cart.setQty(qnty);
			cart.setPprice(product.getPrice());
			cart.setTotal(product.getPrice());
			cartDAO.add(cart);

			return "redirect:/cart/viewcart";

		} else {
			M.addAttribute("msg", true);
			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("product", productdao.getProduct(id));
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("title", "Product Info");
			M.addAttribute("userClickProductInfo", true);
			return "page";

		}
	}
	else {
		session.setAttribute("pid",id);
		session.setAttribute("qid", qnty);
		return "redirect:/login";
	}
	}

	@RequestMapping(value = "cart/viewcart")
	public String viewcart(Model M, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		M.addAttribute("title", "Cart");
		M.addAttribute("cartlist", cartlist);
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickCart", true);
		session.setAttribute("cartsize", cartlist.size());
		return "page";

	}

	@RequestMapping(value = "deletecart/{id}")
	public String deletecart(@PathVariable int id, Model M, HttpSession session) {
		cartDAO.delete(id);
		return "redirect:/cart/viewcart";

	}

	@RequestMapping(value = "Address")
	public String Address(Model M, HttpSession session) {

		M.addAttribute("title", "Address");
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickCheckout", true);
		M.addAttribute("address", new BillingAddress());
		M.addAttribute("addresslist",
				billingAddressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "page";

	}

	@RequestMapping(value = "setaddress")
	public String setCategory(@Valid @ModelAttribute("address") BillingAddress address, BindingResult result, Model M,
			HttpSession session) {

		if (result.hasErrors()) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckout", true);
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					billingAddressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));

			return "page";
		}

		try {

			address.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
			billingAddressDAO.add(address);
			return "redirect:/Address";
		} catch (Exception e) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckout", true);
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					billingAddressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
			return "page";
		}

	}

	@RequestMapping(value = "editadd/{id}")
	public String editAddress(@PathVariable int id, Product product, Model M, HttpSession session) {
		M.addAttribute("title", "Address");
		M.addAttribute("userClickCheckout", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("address", billingAddressDAO.show(id));
		M.addAttribute("addresslist",
				billingAddressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "page";
	}

	@RequestMapping(value = "deladd/{id}")
	public String deladd(@PathVariable int id, Model model) {
		billingAddressDAO.delete(id);
		return "redirect:/Address";
	}

	@RequestMapping(value = "invoice/{aid}")
	public String invoice(@PathVariable int aid, Model model, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Long uuid = UUID.randomUUID().getMostSignificantBits();
		String id = "OD" + uuid.toString().replace('-', '2');
		Iterator<Cart> cartiterator = cartlist.listIterator();
		while (cartiterator.hasNext()) {
			Cart cart = cartiterator.next();
			Product product = productdao.getProduct(cart.getPid());
			product.setQuantity(product.getQuantity() - cart.getQty());
			productdao.addProduct(product);

			CustomerOrder c = new CustomerOrder();
			c.setCartId(cart.getCartId());
			c.setOrderId(id);
			c.setAddid(aid);
			c.setDate(new Date());
			c.setPid(cart.getPid());
			c.setPname(cart.getPname());
			c.setQty(cart.getQty());
			c.setSubtotal(cart.getTotal());
			orderDAO.insert(c);
			cartDAO.delete(cart.getId());

		}

		try {
			String recipientAddress = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer customer = CustomerDAO.showcustomer(recipientAddress);
			String uname = customer.getName();
			String usubject = "Order Confirmation";
			String finalmessage = "Hi" + uname + ":,\n\n Your order is confirmed.\n\n Your order number is" + id
					+ "\n\n\n regards\n\n Admin";
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject(usubject);
			email.setText(finalmessage);
			mailSender.send(email);
		} catch (Exception e) {

		}

		return "redirect:/viewbill/" + id + "/" + aid;
	}

	@RequestMapping(value = "viewbill/{oid}/{aid}")
	public String viewbill(Model M, HttpSession session, @PathVariable String oid, @PathVariable int aid) {
		List<CustomerOrder> custorder = orderDAO.viewreceipt(oid);
		M.addAttribute("title", "Order");
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickInvoice", true);
		M.addAttribute("baddress", billingAddressDAO.show(aid));
		M.addAttribute("orderid", oid);
		M.addAttribute("orderdetail", custorder);
		session.setAttribute("cartsize",
				cartDAO.show(Integer.parseInt(session.getAttribute("usercartid").toString())).size());
		return "page";

	}

	@RequestMapping(value = "viewallorders")
	public String viewallorders(Model M, HttpSession session) {

		int cartid = Integer.parseInt(session.getAttribute("usercartid").toString());
		M.addAttribute("title", "My Orders");
		M.addAttribute("orderlist", orderDAO.viewAllOrder(cartid));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickAllOrder", true);
		return "page";
	}

	@RequestMapping(value = "cart/changepassword")
	public String changepassword(Model model) {

		model.addAttribute("title", "Change Password");

		model.addAttribute("catlist", categorydao.showAllCategory());
		model.addAttribute("userClickChangePassword", true);
		model.addAttribute("usercred", new UserCredentials());
		model.addAttribute("msg", false);
		return "page";

	}

	@RequestMapping(value = "cart/updatepassword")
	public String updatepassword(@ModelAttribute("usercred") UserCredentials uc, Model model,
			HttpServletRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		UserCredentials olduc = CustomerDAO.showcred(email);

		String oldpassword = request.getParameter("oldpass");

		if (oldpassword.equals(olduc.getPassword())) {
			olduc.setPassword(uc.getPassword());
			CustomerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Change Password");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg", true);

		} else {
			model.addAttribute("title", "Change Password");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg1", true);
		}

		return "page";

	}

	@RequestMapping(value = "/resetpassword")
	public String resetpassword(Model model, HttpServletRequest request) {
		String email = request.getParameter("j_username");

		UserCredentials olduc = CustomerDAO.showcred(email);
		if (olduc != null) {
			String s = UUID.randomUUID().toString().substring(0, 8);
			System.out.println(s);
			String finalmessage = "Hi,\n\n your new password is \n\n " + s + " \n\n regards\n\n Admin";
			SimpleMailMessage pemail = new SimpleMailMessage();
			pemail.setTo(email);
			pemail.setSubject("Your new password for computer mart");
			pemail.setText(finalmessage);
			mailSender.send(pemail);
			olduc.setPassword(s);
			CustomerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Sign In");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", false);

		} else {
			model.addAttribute("title", "Sign In");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", true);

		}

		return "page";

	}

	@RequestMapping(value = "cart/addwish/{id}")
	public String addwish(@PathVariable int id, Model model, HttpSession session) {
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Product product = productdao.getProduct(id);
		for (Wish wish : wishlist) {
			if (wish.getPid() == id) {
				wishDAO.add(wish);
				return "redirect:/cart/viewwish";
			}
		}

		Wish wish = new Wish();
		wish.setPid(id);
		wish.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
		wish.setPname(product.getProductName());
		wish.setPprice(product.getPrice());

		wishDAO.add(wish);
		return "redirect:/cart/viewwish";

	}

	@RequestMapping(value = "cart/viewwish")
	public String viewcart1(Model model, HttpSession session) {
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		model.addAttribute("title", "Wishlist");
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("catlist", categorydao.showAllCategory());
		model.addAttribute("userClickWish", true);
		model.addAttribute("msg", false);
		return "page";

	}

	@RequestMapping(value = "deletewish/{id}")
	public String delcart(@PathVariable int id, Model model) {
		wishDAO.delete(id);
		return "redirect:/cart/viewwish";
	}

}
