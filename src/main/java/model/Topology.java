package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Topology {
    String id;
    JsonArray components;

    public Topology(String id,JsonArray components){
        this.id = id;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonArray getComponents() {
        return components;
    }

    public void setComponents(JsonArray components) {
        this.components = components;
    }
}
