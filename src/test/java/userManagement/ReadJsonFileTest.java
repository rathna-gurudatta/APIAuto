package userManagement;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;

public class ReadJsonFileTest {

    @Test
    public void testJson1() throws IOException, ParseException {
        JSONArray arr = JsonReader.getJsonArray("getUsers");
        //System.out.println(arr);
        for (int i = 0; i < arr.size(); i++) {
            Object item = arr.get(i);
            System.out.println(item);
        }


    }

}
