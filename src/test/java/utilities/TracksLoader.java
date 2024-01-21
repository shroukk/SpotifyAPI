package utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TracksLoader {

    public static String[]  getTrackItem(){

        try {
            String jsonContent = getTracksObject();

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Get the "tracks" array
            JSONArray tracksArray = jsonObject.getJSONArray("tracks");
            String[] allTracks = new String[tracksArray.length()];

            // Iterate through the array and extract each track URI
            for (int i = 0; i < tracksArray.length(); i++) {
                JSONObject trackObject = tracksArray.getJSONObject(i);
                String trackUri = trackObject.getString("uri");
                allTracks[i] =  trackUri;
            }
            return  allTracks;

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to load properties from tracks file ");
        }
    }

    public static String getTracksObject(){
        return JsonFileReader.jsonLoader("src/test/resources/tracks.json");

    }

}
