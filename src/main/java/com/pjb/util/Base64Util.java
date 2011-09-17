package com.pjb.util;

import java.io.UnsupportedEncodingException;

public class Base64Util {

	private static String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static int[] ints = new int[128];
	private static final int splitLinesAt = 76;

	static {
		for (int x = 0; x < base64.length() - 2; x++) {
			ints[base64.charAt(x)] = x;
		}
	}
	
	public static byte[] zeroPad(int length, byte[] bytes) {
		byte[] padded = new byte[length];
		System.arraycopy(bytes, 0, padded, 0, bytes.length);
		return padded;
	}
	
	public static String splitLines(String string) {		 
        String lines = "";
        for (int i = 0; i < string.length(); i += splitLinesAt) {
            lines += string.substring(i, Math.min(string.length(), i + splitLinesAt));
            lines += "\r\n";
        }
        return lines;

    }

	public static String encode(String value) throws UnsupportedEncodingException {
		byte[] sarr = value.getBytes("UTF-8");
		int paddingCount = (3 - (sarr.length % 3)) % 3;
		sarr = zeroPad(sarr.length + paddingCount, sarr);
		String encoded = "";
		for(int i=0; i < sarr.length; i += 3) {
			
			int j = ((sarr[i] & 0xff) << 16) +
	                ((sarr[i + 1] & 0xff) << 8) + 
	                (sarr[i + 2] & 0xff);
			
		
			encoded = encoded + base64.charAt((j >> 18) & 0x3f) +
					base64.charAt((j >> 12) & 0x3f) +
	                base64.charAt((j >> 6) & 0x3f) +
	                base64.charAt(j & 0x3f);
			
		}
		
		return splitLines(encoded.substring(0, encoded.length() -
		            paddingCount) + "==".substring(0, paddingCount));
	}

	public static String decode(String value) throws UnsupportedEncodingException {
		value = value.replaceAll("\r\n", "");
		int delta = value.endsWith( "==" ) ? 2 : value.endsWith( "=" ) ? 1 : 0;
        byte[] buffer = new byte[value.length()*3/4 - delta];
		
		int index=0;
		for(int i=0; i < value.length(); i += 4) {
			
			int c0 = ints[value.charAt(i)];
			int c1 = ints[value.charAt(i+1)];
			
			buffer[index++] = (byte) (((c0 << 2) | (c1 >> 4))  & 0xFF); 
			if(index >= buffer.length)
				break;
			
			
			int c2 = ints[value.charAt(i+2)];
			buffer[index++] = (byte) (((c1 << 4) | (c2 >> 2))  & 0xFF);
			if(index >= buffer.length)
				break;
			
			int c3 = ints[value.charAt(i+3)];
			buffer[index++] = (byte) (((c2 << 6) | (c3))  & 0xFF);
			
		}
		return new String(buffer, "UTF-8");
	}

}
