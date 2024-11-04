package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiInteractor {

    private static final String API_URL = "https://api.duckduckgo.com/?q=%s&format=json";

    public JSONArray getRelatedTopics(String query) {
        String apiUrl = String.format(API_URL, query);
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.optJSONArray("RelatedTopics");
            } else {
                throw new AssertionError("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            throw new AssertionError("An error occurred while connecting to the server or retrieving the response: " + e.getMessage());
        }
    }

    public void printIconUrls(JSONArray relatedTopics) {
        if (relatedTopics == null) {
            throw new AssertionError("RelatedTopics should not be null.");
        }

        for (int i = 0; i < relatedTopics.length(); i++) {
            JSONObject topic = relatedTopics.optJSONObject(i);
            if (topic != null) {
                JSONObject icon = topic.optJSONObject("Icon");
                if (icon != null) {
                    String iconUrl = icon.optString("URL", null);
                    if (iconUrl != null && !iconUrl.isEmpty()) {
                        System.out.println("Icon URL: " + "https://duckduckgo.com" + iconUrl);
                    } else {
                        System.out.println("Icon URL is empty for topic: " + topic.optString("Text", "Unknown"));
                    }
                } else {
                    System.out.println("Icon structure is null for topic: " + topic.optString("Text", "Unknown"));
                }
            }
        }
    }
}
