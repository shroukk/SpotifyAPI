package utilities;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;


    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/Data.properties");
    }
    public static DataLoader getInstance(){
        if(dataLoader== null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getUserName(){
        String property = properties.getProperty("display_name");
        if (property != null)
            return property;
        else throw new RuntimeException(" Name is not specified in the Data.properties file");

    }
    public String getUserEmail(){
        String property = properties.getProperty("email");
        if (property != null)
            return property;
        else throw new RuntimeException(" Email is not specified in the Data.properties file");

    }
    public String getUserCountry(){
        String property = properties.getProperty("country");
        if (property != null)
            return property;
        else throw new RuntimeException(" Country is not specified in the Data.properties file");

    }
    public String getUserType(){
        String property = properties.getProperty("type");
        if (property != null)
            return property;
        else throw new RuntimeException(" type is not specified in the Data.properties file");

    }

    public String getPlaylistId(){
        String property = properties.getProperty("playlist_id");
        if (property != null)
            return property;
        else throw new RuntimeException(" playlist_id is not specified in the Data.properties file");

    }
    public String getArtistsIds(){
        String property = properties.getProperty("artists_ids");
        if (property != null)
            return property;
        else throw new RuntimeException(" artists_ids is not specified in the Data.properties file");

    }
    public String getFollowType(){
        String property = properties.getProperty("follow-type");
        if (property != null)
            return property;
        else throw new RuntimeException(" follow-type is not specified in the Data.properties file");

    }
}
