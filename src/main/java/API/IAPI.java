package API;

import com.google.gson.JsonArray;
import model.Topology;

import java.util.ArrayList;


/**
 * This API provides the functionality to access, manage and store device topologies.
 * @author Ahmed Abdallah
 */
public interface IAPI {
    /**
     * This method read a json file
     * and store the topology exist in the memory
     * @param FileName the topology json file name.
     * @return true if the process succeeded,false if any problem occurred.
     */
    boolean readJson(String FileName);
    /**
     * This method write a topology json file
     * the file named with the topology id
     * the topology should be existed in memory
     * @param TopologyID the topology id of the required topology to write to a file.
     * @return true if the process succeeded,false if any problem occurred.
     */
    boolean writeJson(String TopologyID);

    /**
     * This method get all topologies exist in memory
     * @return Arraylist of topologies exist in memory.
     */
    ArrayList<Topology> queryTopologies();
    /**
     * This method delete a topology from the memory
     * @param TopologyID the id of the topology required to be deleted.
     * @return the deleted topology.
     */
    Topology deleteTopology(String TopologyID);
    /**
     * get all devices connected with a specific topology
     * @param TopologyID the id of the topology ot get devices connected to it.
     * @return JsonArray of the devices.
     */
    JsonArray queryDevices(String TopologyID);

    /**
     * get all devices connected to a given netList node in a specific given topology.
     * @param TopologyID the id of the given topology.
     * @param NetListNodeID the id on the given netList
     * @return JsonArray of the devices.
     */
    JsonArray queryDevicesWithNetListNode(String TopologyID,String NetListNodeID);
}
