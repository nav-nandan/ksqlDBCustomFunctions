package io.confluent.ksql.udf;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReplaceCharTest {

	@Test
	public void test() {
		assertTrue("PAGE-28".equals("Page_28".replaceAll("Page_", "PAGE-")));
		assertTrue("CR==><==".equals("CR==>\r<==".replaceAll("[\r\n]", "")));
	}
}
