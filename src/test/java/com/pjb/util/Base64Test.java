package com.pjb.util;

import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class Base64Test extends TestCase {

	public void test1() {
		try {
			String val = "Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.";
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
