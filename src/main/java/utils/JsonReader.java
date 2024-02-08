package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String getTestData(String input) throws IOException, ParseException {
        return (String)getJsonData().get(input);
    }
    public static JSONObject getJsonData() throws IOException, ParseException {
        File filename = new File("resources/TestData/TestData.json");
        String data = FileUtils.readFileToString(filename, "UTF-8");
        Object obj = new JSONParser().parse(data);
        JSONObject jsonObj = (JSONObject)obj;
        return jsonObj;
    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException {
        JSONObject obj = getJsonData();
        JSONArray arr = (JSONArray)obj.get(key);
        return arr;
    }

    public static Object getJsonArrayData(String key, int index) throws IOException, ParseException {
        JSONArray arr = getJsonArray(key);
        return arr.get(index);
    }
}
