package org.cartcheckoutservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.cartcheckoutservice.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/view/{cartId}", method=RequestMethod.GET)
	public String getCart(@PathVariable("cartId") String cartId) {
		return "cart return"+cartId;
	}
	
	@RequestMapping(value="/atc/{productId}", method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addItem(@PathVariable(value="productId", required=true)String productId, 
			@RequestHeader(value="userType", required=true)String userType,
			@RequestHeader(value="userId", required=false)String userId,
			@RequestHeader(value="cartId", required=false)String cartId)
	{
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String url="http://localhost:8643/product/"+productId;
	      ResponseEntity<Product> resp=restTemplate.exchange(url,HttpMethod.GET,entity, Product.class);
		  System.out.println("statusCode: "+resp.getStatusCodeValue()+" body: "+resp.getBody()+" Headers: "+resp.getHeaders());
		  if(resp.getStatusCodeValue()>299) {
			  return ResponseEntity.notFound().build();
		  }
		  
		  return ResponseEntity.ok("Added Item in Cart");
	}
	
	
	
}
