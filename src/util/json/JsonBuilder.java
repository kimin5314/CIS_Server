package util.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JsonBuilder {
    public static String buildJson(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static String buildJsonArray(Vector<HashMap<String, String>> maps) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (HashMap<String, String> map : maps) {
            sb.append(buildJson(map)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
