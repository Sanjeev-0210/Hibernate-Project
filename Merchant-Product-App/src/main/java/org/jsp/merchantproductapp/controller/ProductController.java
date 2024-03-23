package org.jsp.merchantproductapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.merchantproductapp.dao.ProductDao;
import org.jsp.merchantproductapp.dto.Product;

public class ProductController {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Add Product");
		System.out.println("2.Update product");
		System.out.println("3.Find Products By Merchant Id");
		System.out.println("4.Find Products By Brand");
		System.out.println("5.Find Products By Category");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter Merchant Id to add product");
			int merchant_id = sc.nextInt();
			System.out.println("Enter the name,brand,category,description,cost and image_url to add Product");
			Product product = new Product();
			product.setName(sc.next());
			product.setBrand(sc.next());
			product.setCategory(sc.next());
			product.setDescription(sc.next());
			product.setCost(sc.nextDouble());
			product.setImage_url(sc.next());
			product = productDao.saveProduct(product, merchant_id);
			if (product != null)
				System.out.println("Product added with Id:" + product.getId());
			else
				System.err.println("cannot add product as Merchant Id is Invalid");
			break;
		}
		case 2: {
			System.out.println("Enter the id,name,brand,category,description,cost and image_url to add Product");
			Product product = new Product();
			product.setId(sc.nextInt());
			product.setName(sc.next());
			product.setBrand(sc.next());
			product.setCategory(sc.next());
			product.setDescription(sc.next());
			product.setCost(sc.nextDouble());
			product.setImage_url(sc.next());
			product = productDao.updateProduct(product);
			if (product != null)
				System.out.println("Product  with Id:" + product.getId() + " updated");
			else
				System.err.println("cannot add product as Id is Invalid");
			break;
		}
		case 3: {
			System.out.println("Enter the Merchant Id to display products");
			int merchant_id = sc.nextInt();
			List<Product> products = productDao.findProductsByMerchantId(merchant_id);
			if (products.isEmpty())
				System.err.println("No Products found for entered Merchant Id");
			else {
				for (Product product : products)
					System.out.println(product);
			}
			break;
		}
		case 4: {
			System.out.println("Enter the brand to display products");
			String brand = sc.next();
			List<Product> products = productDao.findByBrand(brand);
			if (products.isEmpty())
				System.err.println("No Products found for entered brand");
			else {
				for (Product product : products)
					System.out.println(product);
			}
			break;
		}
		case 5: {
			System.out.println("Enter the Category to display products");
			String category = sc.next();
			List<Product> products = productDao.findByCategory(category);
			if (products.isEmpty())
				System.err.println("No Products found for entered Category");
			else {
				for (Product product : products)
					System.out.println(product);
			}
			break;
		}
		default: {
			sc.close();
			System.err.println("Invalid Choice");
		}
		}
	}
}