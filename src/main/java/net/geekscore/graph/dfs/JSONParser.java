package net.geekscore.graph.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {

    public static void main(String[] args) {
        // {"key01"="value01", key02"="value02"}
        System.out.println(parse("{\"key01\":\"value01\",\"key02\":\"value02\"}"));

        // {"key01"={"subkey11"="value11"}}
        System.out.println(parse("{\"key01\":{\"subkey11\":\"value11\"}}"));

        // {"key01"={"subkey12"="value12", "subkey11"="value11"}}
        System.out.println(parse("{\"key01\":{\"subkey11\":\"value11\",\"subkey12\":\"value12\"}}"));

        // {"key01"={"subkey11"={"subkey22"="value22"}}}
        System.out.println(parse("{\"key01\":{\"subkey11\":{\"subkey22\":\"value22\"}}}"));
    }

    private static final Map<String, Object> parse(String json) {

        if(json == null || json.length() == 0) return Collections.emptyMap();

        Map<String, Object> map = new HashMap<>();

        // new key starts with '{'
        int openBraceIdx = json.indexOf('{', 0);
        // key and value delimited by ':'
        int colonIdx = json.indexOf(':', openBraceIdx+1);

        while (colonIdx != -1 && colonIdx < json.length()) {

            final String key = json.substring(openBraceIdx+1, colonIdx);

            openBraceIdx = json.indexOf('{', colonIdx+1);
            if(openBraceIdx != -1 && openBraceIdx < json.length()) {
                map.put(key, parse(json.substring(openBraceIdx)));
                return map;
            }

            // value ends with ',' or '}'
            int valueIdx = json.indexOf(',', colonIdx+1);
            if(valueIdx == -1) {
                valueIdx = json.indexOf('}', colonIdx+1);
            }
            final String value = json.substring(colonIdx+1, valueIdx);
            map.put(key, value);
            openBraceIdx = valueIdx;
            colonIdx = json.indexOf(':', openBraceIdx+1);
        }

        return map;
    }
}
