package org.TestWalmartClientAutomation.jUnit;

import static org.junit.Assert.*;

import org.WalmartAutomationClient.java.WalmartClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWalmartClient {
	WalmartClient testClient = new WalmartClient();
	private final static String TESTOFFERID = "BD3D95B609474DE589E2CA67E8FFE7FC";

	@Test
	public void test1_testLogin() {
		Integer expected = 200;
		Integer actualResponseCode = testClient.login();
		assertEquals(expected, actualResponseCode);
	}

	@Test
	public void test2_testGetSearchResults() {
		String expected = TESTOFFERID;
		String actual = testClient.getSearchResults("Socks");
		assertEquals(expected, actual);
	}

	@Test
	public void test3_testAddItemToCart() {
		Integer expected = 1;
		Integer actual = testClient.addItemToCart(TESTOFFERID, 1);
		assertEquals(expected, actual);
	}

	@Test
	public void test4_testCartItemCount() {
		int expected = 1;
		int actual = testClient.cartItemCount();
		assertEquals(expected, actual);
	}

	@Test
	public void test5_testValidateOfferIdInCart() {
		String expected = TESTOFFERID;
		String actual = testClient.validateOfferIdInCart();
		assertEquals(expected, actual);
	}

	@Test
	public void test6_testGetSearchResults_whenSearchQueryIsNull() {
		String expected = null;
		String actual = testClient.getSearchResults(null);
		assertEquals(expected, actual);
	}

	@Test
	public void test7_testAddItemToCart_whenNoOfferIdSpecificied() {
		Integer expected = 0;
		Integer actual = testClient.addItemToCart(null, 1);
		assertNotEquals(expected, actual);
	}

	@Test
	public void test8_testCartItemCount_whenFailedToAddItem() {
		int expected = 1;
		int actual = testClient.cartItemCount();
		assertEquals(expected, actual);
	}

	@Test
	public void test9_testValidateOfferIdInCart_whenWrongOfferID() {
		String wrongOfferId = "BD3D95B609474DE589E2CA67E8FF443";
		String actual = testClient.validateOfferIdInCart();
		assertNotEquals(wrongOfferId, actual);
	}

}
