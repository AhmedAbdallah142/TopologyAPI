package file;

import com.google.gson.Gson;
import model.Topology;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this class responsible for all file operation in the project
 * @author Ahmed Abdallah
 */
public class FileOperation {

    /**
     * read a file with a given path and parse it to a JsonObject
     * @param filePath the path of the file to be read
     * @return the jsonObject gotten from the file
     * @throws IOException
     * @throws ParseException
     */
    public JSONObject readJsonFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader f = new FileReader(filePath);
        return (JSONObject) jsonParser.parse(f);
    }

    /**
     * this method write a topology to a json file
     * @param data the topology required to be written
     * @return true if topology written successfully else return false
     */
    public boolean writeJsonFile(Topology data) {
        try {
            FileWriter f = new FileWriter(data.getId()+".json");
            f.write(new Gson().toJson(data));
            f.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
