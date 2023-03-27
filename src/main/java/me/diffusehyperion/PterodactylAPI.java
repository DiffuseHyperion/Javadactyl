package me.diffusehyperion;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PterodactylAPI {
    private final String apiKey;
    private final String host;

    public PterodactylAPI(String apiKey, String host) {
        this.apiKey = apiKey;
        if (!host.endsWith("/")) {
            this.host = host + "/";
        } else {
            this.host = host;
        }
    }

    //make private in the future
    public List<Pair<String, String>> getParameters() {
        try {
            return toParameters("Accept", "application/json", "Authorization", "Bearer " + apiKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<Integer, String> makeRequest(String targetURL, String method, List<Pair<String, String>> urlParameters) {
        try {
            URL url = new URL(targetURL);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();

            uc.setRequestMethod(method);
            for (Pair<String, String> parameter : urlParameters) {
                uc.setRequestProperty(parameter.getValue1(), parameter.getValue2());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            return new Pair<>(uc.getResponseCode(), in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // could have added a nullable variable but meh
    public Pair<Integer, String> makeOutputRequest(String targetURL, String method, List<Pair<String, String>> urlParameters, String output) {
        try {
            URL url = new URL(targetURL);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();

            uc.setRequestMethod(method);
            for (Pair<String, String> parameter : urlParameters) {
                uc.setRequestProperty(parameter.getValue1(), parameter.getValue2());
            }
            OutputStream os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(output);
            osw.flush();
            osw.close();
            os.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();
            in.close();
            uc.disconnect();
            return new Pair<>(uc.getResponseCode(), line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<Integer, JSONObject> handleRequest(Pair<Integer, String> request) {
        JSONObject object;
        try {
            object = (JSONObject) new JSONParser().parse(request.getValue2());
        } catch (ParseException e) {
            System.out.println("Javadactyl encountered a malformed JSON! This is a problem with Pterodactyl, please report it to them!");
            throw new RuntimeException(e);
        }

        return new Pair<>(request.getValue1(), object);
    }

    public List<Pair<String, String>> toParameters(String... parameters) throws Exception {
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
