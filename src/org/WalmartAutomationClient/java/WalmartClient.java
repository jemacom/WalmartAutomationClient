package org.WalmartAutomationClient.java;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WalmartClient {

	//Endpoints
	private static final String HOST_URL = "api.mobile.walmart.com";
	private static final String LOGIN_URL = "https://api.mobile.walmart.com/mauth/v2/getToken";
	private static final String ADD_ITEM_TO_CART = "https://api.mobile.walmart.com/cart/items";
	private static final String CART_ITEM_COUNT_URL = "https://api.mobile.walmart.com/cart/items/count";
	private static final String VALIDATE_CART_URL = "https://www.walmart.com/api/cart/:CRT?shipMethodDefaultRule=SHIP_RULE_1";
	private static final String SEARCH_URL_START = "https://mobile.walmart.com/m/j?service=Browse&method=searchByDepartmentFiltered&p1=";
	private static final String SEARCH_URL_END = "&p2=0&p3=All&p4=RELEVANCE&p5=0&p6=50&p7=[]&e=1&version=2";
	
	//Static String Params
	private static final String HOST = "Host";
	private static final String CREDENTIALS = "{\"email\":\"kandarp1691@gmail.com\",\"password\":\"India123\"}";
	private static final String COOKIE = "Cookie";
	private static final String COOKIE_DATA = "CID=891b0b70-a199-4c99-aa83-0fb54dff827a; CRT=26201003-7ac0-4081-bd65-99b24201a995; DL=94066%2Cempty%2Cempty%2Cuser%2C94066%2CUSA%2CCA; SP=n; SSLB=0; WMR=p1-1|p2-1|p3-1|p4-0; auth=C811lyPFLbIXzCuAwZCnX18EG90SmeEuQrmlCtz6RAnkLasIrwTUDCYPD20UlgoJ1MeY1J0npRkvVE3zHVpjLG1cruadLRTRrTrEmJGpQMoKMYZfribPz%2B6gRO7bFwS10warA%2Fd2LnO0lVIcNP3NLTOmfIcKpVG31eEqj2nwts9f%2F38vILVFR2OqcGsw4Laf%2FVNtt0zBAOXIhxM0aTaRgZsEEh5kzSHxDBTXqiSdxkrPgadsybJ%2FQ7vqoo9665nqU4bxSkxRUF8F7G2RpY0rG%2BhOqsjjLKhBSy%2FrLQnzBqI%3D; com.wm.customer=vz7.07e8ba1999bb8279e7c2af7ed249a099d%7E%7EKandarp%7E%7EGandhi%7E%7EU%7E%7Etrue%7E%7EI; exp=0%2B1447822744%2B%2B0%2B; hasCID=1; hasCRT=1; type=REGISTERED";
	private static final String OFFER_ID = "offerId";
	private static final String ITEM = "item";
	private static final String ITEMS = "items";
	private static final String ITEMCOUNT = "itemCount";
	private static final String NAME = "name";
	private static final String CART = "cart";

	
	/**
	 * @return 200 http response code indicating that login was successful.
	 */
	public Integer login() {
		HttpClient client = HttpClientBuilder.create().build();
		WalmartClientHelper helper = new WalmartClientHelper();
		HttpPost post = helper.getHTTPPostObject(LOGIN_URL);
		Integer responseCode= null;

		try {
			post.setEntity(new StringEntity(CREDENTIALS));
			HttpResponse response = client.execute(post);
			responseCode = response.getStatusLine().getStatusCode(); 

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return responseCode;
	}
	
	/**
	 * @param query to search
	 * @return Search results for given Query String
	 */
	public String getSearchResults(String query) {
		if (query == null) {
			return null;
		}
		HttpGet get = new HttpGet(SEARCH_URL_START  + query + SEARCH_URL_END);
		HttpClient client = HttpClientBuilder.create().build();
		String offerId = null;

		try {
			HttpResponse response = client.execute(get);
			String resp = EntityUtils.toString(response.getEntity());
			JSONObject jsonMap = new JSONObject(resp);
			Object object = jsonMap.get(ITEM);
			JSONArray obj1 = new JSONArray(object.toString());
			Object name = obj1.getJSONObject(0).get(NAME);
			JSONObject allItem = obj1.getJSONObject(0);
			offerId = allItem.getString(OFFER_ID).toString();
			System.out.println("\nSearch resulted the following items " + name + " with offerId " + offerId);
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return offerId;
	}


	/**
	 * @param offerId
	 * @param quantity
	 * @return total items in cart
	 */
	public Integer addItemToCart(String offerId, int quantity) {
		if (offerId == null || quantity == 0)
			return null;
		HttpClient client1 = HttpClientBuilder.create().build();
		WalmartClientHelper helper = new WalmartClientHelper();
		HttpPost post = helper.getHTTPPostObject(ADD_ITEM_TO_CART);
		post.setHeader(COOKIE, COOKIE_DATA);
		int itemCount = 0;

		try {
			post.setEntity(new StringEntity("{\"offerId\":\"" + offerId + "\",\"quantity\":" + quantity + "}"));
			HttpResponse postResponse = client1.execute(post);
			JSONObject count = new JSONObject(EntityUtils.toString(postResponse.getEntity()));
			itemCount = count.getInt(ITEMCOUNT);
			System.out.println("\nTotal items in cart after adding " + offerId + " is "+ itemCount);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return itemCount;
	}

	
	/**
	 * @return Number of items in the cart
	 */
	public int cartItemCount() {
		HttpGet get = new HttpGet(CART_ITEM_COUNT_URL);
		HttpClient client = HttpClientBuilder.create().build();
		get.setHeader(HOST, HOST_URL);
		get.setHeader(COOKIE, COOKIE_DATA);
		int cartItemCount = 0;

		try {
			HttpResponse response = client.execute(get);
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			cartItemCount = json.getInt(ITEMCOUNT);
			System.out.println("\nChecking number of items in cart");
			System.out.println("\nNumber of items in cart is " + cartItemCount);
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return cartItemCount;
	}

	/**
	 * @returns OfferID of Item in cart
	 */
	public String validateOfferIdInCart() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(VALIDATE_CART_URL);
		get.setHeader(COOKIE, COOKIE_DATA);
		String offerIdInCart = null;
		HttpResponse response;
		try {
			response = client.execute(get);
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			Integer count = (Integer) json.getJSONObject(CART).get(ITEMCOUNT);
			JSONObject obj = (JSONObject) json.getJSONArray(ITEMS).get(0);
			offerIdInCart = obj.get(OFFER_ID).toString();
			if (count == 1)
				System.out.println("\nItem " + offerIdInCart + " present in cart with quantity " + count);
			else
				System.out.println("\nMore than one item present");
			if(count == 0)
				System.out.println("\nNo item in cart");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return offerIdInCart;
	}

}