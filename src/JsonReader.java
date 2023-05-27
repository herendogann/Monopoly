import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    public static String[][] land_2D_array() {
        String[][] land_array = new String[22][3];
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("property.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray Land = (JSONArray) jsonfile.get("1");
            int r = 0;
            for (Object i : Land) {
                land_array[r][0] = (String) ((JSONObject) i).get("id");
                land_array[r][1] = (String) ((JSONObject) i).get("name");
                land_array[r][2] = (String) ((JSONObject) i).get("cost");
                r++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return land_array;
    }

    public static String[][] railroad_2D_array() {
        String[][] railroad_array = new String[4][3];
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("property.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray RailRoad = (JSONArray) jsonfile.get("2");
            int r = 0;
            for (Object i : RailRoad) {
                railroad_array[r][0] = (String) ((JSONObject) i).get("id");
                railroad_array[r][1] = (String) ((JSONObject) i).get("name");
                railroad_array[r][2] = (String) ((JSONObject) i).get("cost");
                r++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return railroad_array;
    }

    public static String[][] company_2D_array() {
        String[][] company_array = new String[2][3];
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("property.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray Company = (JSONArray) jsonfile.get("3");
            int r = 0;
            for (Object i : Company) {
                //You can reach items by using statements below:
                company_array[r][0] = (String) ((JSONObject) i).get("id");
                company_array[r][1] = (String) ((JSONObject) i).get("name");
                company_array[r][2] = (String) ((JSONObject) i).get("cost");
                r++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return company_array;
    }

    public static String[] chance_array() {
        String[] chance_array = new String[6];
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            int c = 0;
            for (Object i : chanceList) {
                chance_array[c] = ((String) ((JSONObject) i).get("item"));
                c++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } return chance_array;
    }

    public static String[] communityChest_array() {
        String[] communityChest_array = new String[11];
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            int c = 0;
            for (Object i : communityChestList) {
                communityChest_array[c] = ((String) ((JSONObject) i).get("item"));
                c++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } return communityChest_array;
    }
}