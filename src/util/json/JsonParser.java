package util.json;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    public static HashMap<String, String> parseJson(String json) throws IllegalArgumentException {
        HashMap<String, String> map = new HashMap<>();

        json = json.trim();
        if (json.startsWith("{")) {
            json = json.substring(1);
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 1);
        }

        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Invalid JSON string");
            }

            String key = stripQuotes(keyValue[0].trim());
            String value = stripQuotes(keyValue[1].trim());

            map.put(key, value);
        }

        return map;
    }

    public static HashMap<String, String>[] parseJsonArray(String jsonArray) throws IllegalArgumentException {
        jsonArray = jsonArray.trim();
        if (jsonArray.startsWith("[")) {
            jsonArray = jsonArray.substring(1);
        }
        if (jsonArray.endsWith("]")) {
            jsonArray = jsonArray.substring(0, jsonArray.length() - 1);
        }

        String[] jsons = jsonArray.split("},");
        HashMap<String, String>[] maps = new HashMap[jsons.length];
        for (int i = 0; i < jsons.length; i++) {
            maps[i] = parseJson(jsons[i]);
        }
        return maps;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    public static Map<String, String> parseRequestToMap(HttpServletRequest req) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        String json = requestBody.toString();
        return parseJson(json);
    }
}

