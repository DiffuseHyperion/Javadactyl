package me.diffusehyperion;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PterodactylAPI {
    private final String apiKey;
    private final String host;

    public PterodactylAPI(String apiKey, String host) {
        this.apiKey = apiKey;
        if (!host.startsWith("http://") && !host.startsWith("https://")) {
            throw new IllegalArgumentException("Host must start with http:// or https://");
        }
        if (!host.endsWith("/")) {
            System.out.println("WARNING: Host does not end with /, adding it for you");
            this.host = host + "/";
        } else {
            this.host = host;
        }
    }

    public List<Pair<String, String>> getParameters() {
        try {
            return toParameters("Accept", "application/json", "Authorization", "Bearer " + apiKey, "Content-Type", "application/json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<Integer, String> makeRequest(String targetURL, String method, List<Pair<String, String>> urlParameters, @Nullable String body) {
        HttpRequest request;
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(targetURL));
        if (Objects.nonNull(body)) {
                    requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(body));
        } else {
                    requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
        }
        for (Pair<String, String> params : urlParameters) {
            requestBuilder.header(params.getValue1(), params.getValue2());
        }
        request = requestBuilder.build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Pair<>(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<Integer, JSONObject> handleRequest(Pair<Integer, String> request) {
        JSONObject object;
        if (request.getValue2().isEmpty()) {
            object = null;
        } else {
            try {
                object = (JSONObject) new JSONParser().parse(request.getValue2());
            } catch (ParseException e) {
                System.out.println("Javadactyl encountered a malformed JSON! This is a problem with Pterodactyl, please report it to them!");
                throw new RuntimeException(e);
            }
        }

        return new Pair<>(request.getValue1(), object);
    }

    private List<Pair<String, String>> toParameters(String... parameters) throws Exception {
        if ((parameters.length % 2) == 1) {
            throw new Exception("Missing value for key " + parameters[parameters.length - 1]);
        }

        List<Pair<String, String>> parametersList = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if ((i % 2) == 0) {
                parametersList.add(new Pair<>(parameters[i], parameters[i+1]));
            }
        }

        return parametersList;
    }

    public String getHost() {
        return host;
    }

    public String getApiKey() {
        return apiKey;
    }
}
