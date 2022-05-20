package model;

import com.google.gson.JsonArray;


/**
 * the topology object
 * this class is to encapsulate topology data in one object
 * @author Ahmed Abdallah
 */
public class Topology {
    // the id of the topology
    String id;

    // an array of devices connected to this topology
    JsonArray components;

    /**
     * construct the topology id and devices connected
     * @param id the topology id
     * @param components devices connected with the topology
     */
    public Topology(String id,JsonArray components){
        this.id = id;
        this.components = components;
    }

    /**
     * get the id of the topology
     * @return topology id
     */
    public String getId() {
        return id;
    }

    /**
     * set the id with a new one
     * @param id new topology id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the devices connected to this topology
     * @return a JsonArray of the devices
     */
    public JsonArray getComponents() {
        return components;
    }

    /**
     * set the devices connected to this topology with a new one
     * @param components new JsonArray of connected devices
     */
    public void setComponents(JsonArray components) {
        this.components = components;
    }
}
