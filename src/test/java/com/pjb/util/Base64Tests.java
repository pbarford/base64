package com.pjb.util;

import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class Base64Tests extends TestCase {

	public void test1() {
		try {
			String val = "hello there hows it going";
			String encoded = Base64Util.encode(val);
			String decoded = Base64Util.decode(encoded);
			assertEquals(val, decoded);
		} catch (UnsupportedEncodingException e) {
			fail();
		}
	}
	
	public void test2() {
		try {
			String val = "Hello 汉 my name is 洒";
			String encoded = Base64Util.encode(val);
			String decoded = Base64Util.decode(encoded);
			assertEquals(val, decoded);
		} catch (UnsupportedEncodingException e) {
			fail();
		}
	}
}
