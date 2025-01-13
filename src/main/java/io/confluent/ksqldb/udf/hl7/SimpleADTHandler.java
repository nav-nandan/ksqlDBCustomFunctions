package io.confluent.ksqldb.udf.hl7;

import org.json.JSONObject;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "hl7handler", description = "parses hl7 messages and extracts required info")
public class SimpleADTHandler {

  @Udf(description = "parses hl7 messages and extracts patient info")
  public String hl7handler(
    @UdfParameter(value = "V1", description = "original string") final String v1) {
	  
	  String[] message_header = v1.split("\n")[3].split("\\|");
	  String[] patient_demographics = v1.split("\n")[5].split("\\|");
	  
	  String sending_facility = message_header[3];
	  String receiving_facility = message_header[5];
	  String message_dt = message_header[6];
	  String message_type = message_header[8];
	  String message_id = message_header[9];
	  
	  String mrn = patient_demographics[2].split("\\^")[0];
	  String last_name = patient_demographics[5].split("\\^")[0];
	  String first_name = patient_demographics[5].split("\\^")[1];
	  String title = patient_demographics[5].split("\\^")[4];
	  String dob = patient_demographics[7];
	  String gender = patient_demographics[8];
	  String ethnicity_race = patient_demographics[10];

	  JSONObject hl7message = new JSONObject();

	  JSONObject messageHeader = new JSONObject();
	  messageHeader.put("sending_facility", sending_facility);
	  messageHeader.put("receiving_facility", receiving_facility);
	  messageHeader.put("message_dt", message_dt);
	  messageHeader.put("message_type", message_type);
	  messageHeader.put("message_id", message_id);

	  JSONObject patientDemographics = new JSONObject();
	  patientDemographics.put("mrn", mrn);
	  patientDemographics.put("last_name", last_name);
	  patientDemographics.put("first_name", first_name);
	  patientDemographics.put("title", title);
	  patientDemographics.put("dob", dob);
	  patientDemographics.put("gender", gender);
	  patientDemographics.put("ethnicity_race", ethnicity_race);

	  hl7message.put("message_header", messageHeader);
	  hl7message.put("patient_demographics", patientDemographics);
	  
	  return hl7message.toString();
  }
}
