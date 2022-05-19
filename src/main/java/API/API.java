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

public class API implements IAPI{
    Map<String,Topology> topologies;

    public API(){
        topologies = new HashMap<>();
    }

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

    @Override
    public boolean writeJson(String TopologyID) {
        FileOperation file = new FileOperation();
        return file.writeJsonFile(topologies.get(TopologyID));
    }

    @Override
    public ArrayList<Topology> queryTopologies() {
        return (ArrayList<Topology>) topologies.values();
    }

    @Override
    public Topology deleteTopology(String TopologyID) {
        return topologies.remove(TopologyID);
    }

    @Override
    public JsonArray queryDevices(String TopologyID) {
        return topologies.get(TopologyID).getComponents();
    }

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
