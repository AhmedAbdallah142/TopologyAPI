package API;

import file.FileOperation;
import model.Topology;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.*;


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
            JSONObject j = file.readJsonFile(FileName);
            Topology t = new Topology(String.valueOf(j.get("id")), (JSONArray) j.get("components"));
            topologies.put(t.getId(),t);
        } catch (IOException | ParseException e) {
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
        if(topologies.containsKey(TopologyID))
            return file.writeJsonFile(topologies.get(TopologyID));
        else
            return false;
    }

    /**
     * This method get all topologies exist in memory
     * @return Arraylist of topologies exist in memory.
     */
    @Override
    public Collection<Topology> queryTopologies() {
        return topologies.values();
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
    public JSONArray queryDevices(String TopologyID) {
        if (topologies.containsKey(TopologyID))
            return topologies.get(TopologyID).getComponents();
        else
            return null;
    }

    /**
     * get all devices connected to a given netList node in a specific given topology.
     * @param TopologyID the id of the given topology.
     * @param NetListNodeID the id on the given netList
     * @return JsonArray of the devices.
     */
    @Override
    public JSONArray queryDevicesWithNetListNode(String TopologyID, String NetListNodeID) {
        if (!topologies.containsKey(TopologyID))
            return null;
        JSONArray result = new JSONArray();
        for (Object j : topologies.get(TopologyID).getComponents()){
            JSONObject netList = (JSONObject) ((JSONObject) j).get("netlist");
            Collection<String> values = netList.values();
            for (String k : values){
                if (String.valueOf(k).equalsIgnoreCase(NetListNodeID)){
                    result.add(j);
                    break;
                }
            }
        }
        return result;
    }
}
