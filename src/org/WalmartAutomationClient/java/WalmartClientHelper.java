package org.WalmartAutomationClient.java;

import org.apache.http.client.methods.HttpPost;

public class WalmartClientHelper {
	
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = "application/json";
	private static final String CONNECTION = "Connection";
	private static final String KEEPALIVE = "keep-alive";
	private static final String PROXYCONNECTION = "Proxy-Connection";
	private static final String ACCEPTLANGUAGE = "Accept-Language";
	private static final String LANGUAGE = "en-us";
	private static final String ACCEPTENCODING = "Accept-Encoding";
	private static final String FORMAT = "gzip, deflate";
	private static final String USERAGENT = "User-Agent";
	private static final String AGENT = "Walmart/15102416 CFNetwork/758.1.6 Darwin/15.0.0";
	private static final String CONTENTTYPE = "Content-Type";
	private static final String HOST = "Host";
	private static final String HOST_URL = "api.mobile.walmart.com";
	
	
	protected HttpPost getHTTPPostObject(String url) {
		HttpPost post = new HttpPost(url);
		post.setHeader(USERAGENT, AGENT);
		post.setHeader(HOST, HOST_URL);
		post.setHeader(ACCEPT, CONTENT_TYPE);
		post.setHeader(CONNECTION, KEEPALIVE);
		post.setHeader(ACCEPTLANGUAGE, LANGUAGE);
		post.setHeader(CONTENTTYPE, CONTENT_TYPE);
		post.setHeader(PROXYCONNECTION, KEEPALIVE);
		post.setHeader(ACCEPTENCODING, FORMAT);
		return post;
	}


}
