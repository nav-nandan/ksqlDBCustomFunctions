package io.confluent.ksql.udf;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import io.confluent.ksqldb.udf.date.FormatDateString;

public class FormatDateStringTest {

	@Test
	public void test() throws ParseException {
		FormatDateString fds = new FormatDateString();
		
		assertTrue("2021 01 01".equals(fds.formatDateString("20211", "yyyyDDD", "yyyy MM dd")));
		assertTrue("2021 01 01".equals(fds.formatDateString("202101", "yyyyDDD", "yyyy MM dd")));
		assertTrue("2021 01 01".equals(fds.formatDateString("2021001", "yyyyDDD", "yyyy MM dd")));
		assertTrue("2021 01 10".equals(fds.formatDateString("202110", "yyyyDDD", "yyyy MM dd")));
		assertTrue("2021 04 10".equals(fds.formatDateString("2021100", "yyyyDDD", "yyyy MM dd")));
	}
}
