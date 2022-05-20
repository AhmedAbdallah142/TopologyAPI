package fileTest;

import com.google.gson.JsonArray;
import file.FileOperation;
import model.Topology;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileOpTest {

    FileOperation f = new FileOperation();

    @Test
    public void readFile() throws IOException, ParseException {
        String expected = "{\"components\":[{\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"},\"id\":\"res1\",\"type\":\"resistor\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000}},{\"netlist\":{\"gate\":\"vin\",\"source\":\"vss\",\"drain\":\"n1\"},\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"id\":\"m1\",\"type\":\"nmos\"}],\"id\":\"top1\"}";
        assertEquals("read right json file", expected, f.readJsonFile("topology.json").toJSONString());
        assertThrows(IOException.class, () -> f.readJsonFile("test1.json").toJSONString());
        assertThrows(ParseException.class, () -> f.readJsonFile("test.json").toJSONString());
    }

    @Test
    public void writeFile() {
        Topology t = new Topology("writeTest", new JSONArray());
        assertTrue("write right json file", f.writeJsonFile(t));
    }
}
