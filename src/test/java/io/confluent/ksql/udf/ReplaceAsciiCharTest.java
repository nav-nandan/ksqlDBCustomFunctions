package io.confluent.ksql.udf;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReplaceAsciiCharTest {

	@Test
	public void test() {
		char[] charArray = "CR==>\r<==".toCharArray();
		char matchChar = (char) 13;
		char replaceChar = (char) 127;
		for (int i=0; i<charArray.length; i++) {
			if(charArray[i] == matchChar) {
				charArray[i] = replaceChar;
			}
		}
		String result = new String(charArray);
		assertTrue("CR==><==".equals(result));
	}
}
