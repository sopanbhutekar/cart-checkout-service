package org.cartcheckoutservice.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.cartcheckoutservice.object.Cart;

public class CartCollection {
	//carts will store cart object by cartId
	private Map<UUID, Cart> carts;	
	//usercarts will store cartId by userId
	private Map<UUID, UUID> userCarts;
	
	public CartCollection() {
		super();
		carts= new HashMap<UUID, Cart>();
		userCarts= new HashMap<UUID, UUID>();
	}

	public Cart getCart(UUID cartId) {
		if(carts.size()!=0) {
			Iterator<Entry<UUID, Cart>> it= carts.entrySet().iterator();
			while(it.hasNext()) {
				Entry<UUID, Cart> cart=it.next();
				if(cart.getKey()==cartId) {
					return cart.getValue();
				}
			}
		}
		return null;
	}
	
	public boolean addCart(UUID cartId, Cart cart) {
		return carts.put(cartId,cart)!=null;
	}
	
	public UUID getCartId(UUID userId) {
		Iterator<Entry<UUID,UUID>> it=userCarts.entrySet().iterator();
		while(it.hasNext()) {
			Entry<UUID,UUID> user=it.next();
			if(user.getKey()==userId) {
				return user.getValue();
			}
		}
		return null;
	}
}
