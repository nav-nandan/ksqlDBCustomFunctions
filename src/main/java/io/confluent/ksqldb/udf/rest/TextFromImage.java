package io.confluent.ksqldb.udf.rest;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UdfDescription(name = "textfromimage", description = "extracts text from image")
public class TextFromImage {

    HttpClient client = HttpClient.newHttpClient();

    @Udf(description = "calls out to ML API and returns text extracted from image")
    public String textfromimage(
            @UdfParameter(value = "url", description = "ml api endpoint") final String url,
            @UdfParameter(value = "image", description = "base64 encoded image") final String image,
            @UdfParameter(value = "auth", description = "auth params") final String auth,
            @UdfParameter(value = "timestamp", description = "request timestamp") final String timestamp,
            @UdfParameter(value = "service", description = "ml service to use") final String service,
            @UdfParameter(value = "token", description = "amazon security token") final String token) {

        HttpResponse<String> response = null;
        String body = "{\"Image\":{\"Bytes\":\"" + image + "\"}}";

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("Authorization", auth)
                    .header("Content-Type", "application/x-amz-json-1.1")
                    .headers("X-Amz-Content-Sha256", "28291d9050db6a58df7d1d99d7b326c54adaca721a6ecaebf789ee373c7b922f")
                    .headers("X-Amz-Date", timestamp)
                    .headers("X-Amz-Target", service)
                    .headers("X-Amz-User-Agent", "aws-sdk-js/2.1111.0 promise")
                    .headers("X-Rekognition-Consumer", "console")
                    .headers("x-amz-security-token", token)
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.body();
    }
}
