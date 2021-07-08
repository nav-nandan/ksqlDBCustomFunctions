package io.confluent.ksqldb.udf.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "formatdatestring", description = "converts date string from source format to target")
public class FormatDateString {

  @Udf(description = "converts date string from source format to target")
  public String formatDateString (
    @UdfParameter(value = "original", description = "original date string") final String original,
    @UdfParameter(value = "source", description = "source format") final String source,
    @UdfParameter(value = "target", description = "target format") final String target) {
	  try {
		  SimpleDateFormat targetFormat = new SimpleDateFormat(target);
		  Date input = new SimpleDateFormat(source).parse(original);
		  return targetFormat.format(input);
	  } catch (Exception e) {
		return null;
	}
  }
}
