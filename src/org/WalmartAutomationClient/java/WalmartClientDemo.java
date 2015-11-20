package org.WalmartAutomationClient.java;

public class WalmartClientDemo {
	
	public static void main(String[] args) {
		WalmartClient democlient = new WalmartClient();
		democlient.login();
		String result = democlient.getSearchResults("Socks");
		democlient.addItemToCart(result, 1);
		democlient.cartItemCount();
		democlient.validateOfferIdInCart();
	}

}
