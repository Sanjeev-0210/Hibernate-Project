package org.jsp.merchantproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.Product;

//@SuppressWarnings("unchecked")
public class ProductDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();

	public Product saveProduct(Product product, int merchant_id) {
		Merchant merchant = manager.find(Merchant.class, merchant_id);
		EntityTransaction transaction = manager.getTransaction();
		if (merchant != null) {
			product.setMerchant(merchant);// Assigning merchant to product
//			List<Product> products = merchant.getProducts();
//			products.add(product);
//			merchant.setProducts(products);
			merchant.getProducts().add(product);
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

	public Product updateProduct(Product product) {
		Product dbProduct = manager.find(Product.class, product.getId());
		EntityTransaction transaction = manager.getTransaction();
		if (dbProduct != null) {
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setName(product.getName());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			dbProduct.setImage_url(product.getImage_url());
			transaction.begin();
			transaction.commit();
			return dbProduct;
		}
		return null;
	}

	public List<Product> findProductsByMerchantId(int merchant_id) {
		Query q = manager.createQuery("select m.products from Merchant m where m.id=?1");
		q.setParameter(1, merchant_id);
		return q.getResultList();
	}

	public List<Product> findByCategory(String category) {
		Query q = manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

	public List<Product> findByBrand(String brand) {
		Query q = manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
}

