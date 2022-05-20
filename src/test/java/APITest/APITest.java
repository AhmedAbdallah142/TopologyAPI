package APITest;

import API.API;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class APITest {
    API api;
    @Before
    public void set(){
        api = new API();
    }

    @Test
    public void testReadJson(){
        assertTrue(api.readJson("topology.json"));
        assertFalse(api.readJson("test.json"));
        assertFalse(api.readJson("test1.json"));
    }
    @Test
    public void testWriteJson(){
        api.readJson("topology.json");
        assertTrue(api.writeJson("top1"));
        assertFalse(api.writeJson("H1"));
    }

    @Test
    public void testQueryTopologies(){
        assertEquals(0,api.queryTopologies().size());
        api.readJson("topology.json");
        assertEquals(1,api.queryTopologies().size());
        assertEquals("top1",api.queryTopologies().iterator().next().getId());
    }

    @Test
    public void testDeleteTopology(){
        assertEquals(0,api.queryTopologies().size());
        api.readJson("topology.json");
        assertEquals(1,api.queryTopologies().size());
        assertEquals("top1",api.deleteTopology("top1").getId());
        assertEquals(0,api.queryTopologies().size());
        assertNull(api.deleteTopology("top1"));
    }
    @Test
    public void testQueryDevices(){
        assertNull(api.queryDevices("top1"));
        api.readJson("topology.json");
        String expected = "[{\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"},\"id\":\"res1\",\"type\":\"resistor\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000}},{\"netlist\":{\"gate\":\"vin\",\"source\":\"vss\",\"drain\":\"n1\"},\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"id\":\"m1\",\"type\":\"nmos\"}]";
        assertEquals(expected,api.queryDevices("top1").toJSONString());
        assertNull(api.queryDevices("HI"));
    }
    @Test
    public void testQueryDevicesWithNetList(){
        assertNull(api.queryDevicesWithNetListNode("top1","vdd"));
        api.readJson("topology.json");
        String expected = "[{\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"},\"id\":\"res1\",\"type\":\"resistor\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000}}]";
        assertEquals(0,api.queryDevicesWithNetListNode("top1","HI").size());
        assertEquals(1,api.queryDevicesWithNetListNode("top1","vdd").size());
        assertEquals(expected,api.queryDevicesWithNetListNode("top1","vdd").toJSONString());
        expected = "[{\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"},\"id\":\"res1\",\"type\":\"resistor\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000}},{\"netlist\":{\"gate\":\"vin\",\"source\":\"vss\",\"drain\":\"n1\"},\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"id\":\"m1\",\"type\":\"nmos\"}]";
        assertEquals(2,api.queryDevicesWithNetListNode("top1","n1").size());
        assertEquals(expected,api.queryDevicesWithNetListNode("top1","n1").toJSONString());
        assertNull(api.queryDevicesWithNetListNode("H1","vdd"));
    }
}
