package io.confluent.ksqldb.udf.string;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "replaceasciichar", description = "replaces specified character (ascii) in string with desired character (ascii)")
public class ReplaceAsciiChar {

  @Udf(description = "replaces specified character (ascii) in string with desired character (ascii)")
  public String replaceasciichar(
    @UdfParameter(value = "V1", description = "original string") final String v1,
    @UdfParameter(value = "V2", description = "ascii of character to replace") final int v2,
    @UdfParameter(value = "V3", description = "ascii of desired character") final int v3) {
	  char[] charArray = v1.toCharArray();
	  char matchChar = (char) v2;
	  char replaceChar = (char) v3;
	  for (int i=0; i<charArray.length; i++) {
		  if(charArray[i] == matchChar) {
			  charArray[i] = replaceChar;
		  }
	  }
	  return new String(charArray);
  }
}
