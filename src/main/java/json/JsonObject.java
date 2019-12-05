package json;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */

import json.JsonPair;

import java.io.*;
import java.lang.*;
import java.util.*;

public class JsonObject extends Json {

    private ArrayList<JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<>();
        for (JsonPair pair : jsonPairs){
            this.jsonPairs.add(pair);
        }
    }

    @Override
    public String toJson() {
        String json = "{";
        JsonPair pair;
        for (int i = 0; i < this.jsonPairs.size(); i++){
            pair = this.jsonPairs.get(i);

            json += "'" + pair.key + "': '" + pair.value;

            if (i != this.jsonPairs.size() - 1) {
                json += ", ";
            }
        }
        return json + "}";
    }

    public void add(JsonPair jsonPair) {
        for (int i = 0; i < this.jsonPairs.size(); i++){
            if (this.jsonPairs.get(i).key == jsonPair.key){
                this.jsonPairs.set(i, jsonPair);
                return;
            }
        }

        this.jsonPairs.add(jsonPair);


    }

    public Json find(String name) {
        for (int i = 0; i < this.jsonPairs.size(); i++){
            if (this.jsonPairs.get(i).key == name){
                return this.jsonPairs.get(i).value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject res = new JsonObject();
        for (String name : names) {
            Json value = find(name);
            if (value != null) {
                res.add(new JsonPair(name, value));
            }
        }
        return res;
    }
}
