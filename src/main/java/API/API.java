package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import file.FileOperation;
import model.Topology;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * This API provides the functionality to access, manage and store device topologies.
 * @author Ahmed Abdallah
 */
public class API implements IAPI{

    // map to save topologies in memory using topology id
    Map<String,Topology> topologies;

    /**
     * construct the topologies map
     */
    public API(){
        topologies = new HashMap<>();
    }

    /**
     * This method read a json file
     * and store the topology exist in the memory
     * @param FileName the topology json file name.
     * @return true if the process succeeded,false if any problem occurred.
     */
    @Override
    public boolean readJson(String FileName) {
        FileOperation file = new FileOperation();
        try {
            JsonObject j = file.readJsonFile(FileName);
            Topology t = new Topology(String.valueOf(j.get("id")),j.getAsJsonArray("components"));
            topologies.put(t.getId(),t);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method write a topology json file
     * the file named with the topology id
     * the topology should be existed in memory
     * @param TopologyID the topology id of the required topology to write to a file.
     * @return true if the process succeeded,false if any problem occurred.
     */
    @Override
    public boolean writeJson(String TopologyID) {
        FileOperation file = new FileOperation();
        return file.writeJsonFile(topologies.get(TopologyID));
    }

    /**
     * This method get all topologies exist in memory
     * @return Arraylist of topologies exist in memory.
     */
    @Override
    public ArrayList<Topology> queryTopologies() {
        return (ArrayList<Topology>) topologies.values();
    }

    /**
     * This method delete a topology from the memory
     * @param TopologyID the id of the topology required to be deleted.
     * @return the deleted topology.
     */
    @Override
    public Topology deleteTopology(String TopologyID) {
        return topologies.remove(TopologyID);
    }

    /**
     * get all devices connected with a specific topology
     * @param TopologyID the id of the topology ot get devices connected to it.
     * @return JsonArray of the devices.
     */
    @Override
    public JsonArray queryDevices(String TopologyID) {
        return topologies.get(TopologyID).getComponents();
    }

    /**
     * get all devices connected to a given netList node in a specific given topology.
     * @param TopologyID the id of the given topology.
     * @param NetListNodeID the id on the given netList
     * @return JsonArray of the devices.
     */
    @Override
    public JsonArray queryDevicesWithNetListNode(String TopologyID, String NetListNodeID) {
        JsonArray result = new JsonArray();
        for (JsonElement j : topologies.get(TopologyID).getComponents()){
            JsonObject netList =  j.getAsJsonObject().getAsJsonObject("netlist");
            Set<String> keys = netList.keySet();
            for (String k : keys){
                if (String.valueOf(netList.get(k)).equalsIgnoreCase(NetListNodeID)){
                    result.add(j);
                    break;
                }
            }
        }
        return result;
    }
}
