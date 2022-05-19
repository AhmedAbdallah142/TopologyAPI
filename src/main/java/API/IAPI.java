package API;

import com.google.gson.JsonArray;
import model.Topology;

import java.util.ArrayList;

public interface IAPI {
    boolean readJson(String FileName);
    boolean writeJson(String TopologyID);
    ArrayList<Topology> queryTopologies();
    Topology deleteTopology(String TopologyID);
    JsonArray queryDevices(String TopologyID);
    JsonArray queryDevicesWithNetListNode(String TopologyID,String NetListNodeID);
}
