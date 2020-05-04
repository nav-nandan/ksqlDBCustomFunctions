package io.confluent.ksqldb.udf.string;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "replacechar", description = "replaces specified characters in string with desired characters")
public class ReplaceChar {

  @Udf(description = "replaces specified characters in string with desired characters")
  public String replacechar(
    @UdfParameter(value = "V1", description = "original string") final String v1,
    @UdfParameter(value = "V2", description = "characters to replace") final String v2,
    @UdfParameter(value = "V3", description = "desired characters") final String v3) {
	  return v1.replaceAll(v2, v3);
  }
}
