package utilities;

public class ArtistsIdsLoader {

    public static String getArtistsIds(){
        return JsonFileReader.jsonLoader("src/test/resources/ArtistsIds.json");
    }


}
