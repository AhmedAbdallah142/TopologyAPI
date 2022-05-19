package file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Topology;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
    public JsonObject readJsonFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader f = new FileReader(filePath);
        return (JsonObject) jsonParser.parse(f);
    }

    public boolean writeJsonFile(Topology data) {
        try {
            FileWriter f = new FileWriter(String.valueOf(data.getId()));
            f.write(new Gson().toJson(data));
            f.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
