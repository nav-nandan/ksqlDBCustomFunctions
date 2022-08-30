package io.confluent.ksql.udf;

import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class RESTHandlerTest {

	@Test
	public void test() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			String url = "http://localhost:5005/test";
			String params = "?param1=12345";
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + params)).header("Content-Type", "application/json").GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			assertNotNull(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
