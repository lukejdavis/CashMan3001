package au.com.suncorp.cashman3001.controller;

import java.util.Arrays;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.suncorp.cashman3001.model.Product;
import au.com.suncorp.cashman3001.persistence.ProducstDAO;

@RestController
public class ProductsController {
	   @Autowired
	   RestTemplate restTemplate;

	   
	   //Publishing API
		@RequestMapping("/products")
		public String products() {
			JSONArray ret = new JSONArray();
			ProducstDAO dao = new ProducstDAO();
			ret = new JSONArray(dao.getProducts());
			return ret.toString();
	    }
	   
		@RequestMapping(value="/products",method=RequestMethod.POST)
		Product newProduct(@RequestBody Product newProduct) {
			ProducstDAO dao = new ProducstDAO();
			dao.addProduct(newProduct);
			return newProduct;
		}

		@RequestMapping(value="/products/{id}",method=RequestMethod.PUT)
		Product updateProductProduce(@PathVariable("id") int id, @RequestBody Product newProduct) {
			ProducstDAO dao = new ProducstDAO();
			dao.addProduct(newProduct);
			return newProduct;
		}
		@RequestMapping(value="/products/{id}",method=RequestMethod.DELETE)
		int deleteProductProduce(@PathVariable("id") int id) {
			ProducstDAO dao = new ProducstDAO();
			dao.removeProduct(id);
			return id;
		}
		@RequestMapping(value="/products/{id}",method=RequestMethod.GET)
		Product getProduct(@PathVariable("id") int id) {
			ProducstDAO dao = new ProducstDAO();
			Product product = dao.getProduct(id);
			return product;
		}
		
		//Consuming API
	   
		   @RequestMapping(value = "/template/products")
		   public String getProductList() {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<String> entity = new HttpEntity<String>(headers);
		      
		      return restTemplate.exchange(
		         "http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
		   }
		   
		   @RequestMapping(value = "/template/products", method = RequestMethod.POST)
		   public String createProducts(@RequestBody Product product) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
		      
		      return restTemplate.exchange(
		         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
		   }
		   
		   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.PUT)
		   public String updateProductConsume(@PathVariable("id") int id, @RequestBody Product product) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
		      
		      return restTemplate.exchange(
		         "http://localhost:8080/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
		   }
		   
		   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.DELETE)
		   public String deleteProductConsume(@PathVariable("id") int id) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
		      
		      return restTemplate.exchange(
		         "http://localhost:8080/products/"+id, HttpMethod.DELETE, entity, String.class).getBody();
		   }	 
		   
		   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.GET)
		   public String getProductConsume(@PathVariable("id") int id) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
		      
		      return restTemplate.exchange(
		         "http://localhost:8080/products/"+id, HttpMethod.GET, entity, String.class).getBody();
		   }	   
		   
}
