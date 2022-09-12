package io.confluent.ksqldb.udf.rest;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UdfDescription(name = "resthandler", description = "calls out to a REST API with the specified method and returns response")
public class RESTHandler {

    HttpClient client = HttpClient.newHttpClient();

    @Udf(description = "calls out to a REST API with the specified method and returns response")
    public String resthandler(
            @UdfParameter(value = "method", description = "supports GET method") final String method,
            @UdfParameter(value = "url", description = "request url") final String url,
            @UdfParameter(value = "params", description = "request params as ?param1=<value>&param2=<value>") final String params,
            @UdfParameter(value = "authToken", description = "auth token with type e.g. abcdef123456") final String authToken) {

        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + params))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.body();
    }
}
