package utilities;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;


    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/configuration.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader== null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String property = properties.getProperty("client_id");
        if (property != null)
            return property;
        else throw new RuntimeException(" clientId is not specified in the configuration.properties file");

    }

    public String getClientSecret(){
        String property = properties.getProperty("client_secret");
        if (property != null)
            return property;
        else throw new RuntimeException(" clientSecret is not specified in the configuration.properties file");

    }

    public String getGrantType(){
        String property = properties.getProperty("grant_type");
        if (property != null)
            return property;
        else throw new RuntimeException(" grantType is not specified in the configuration.properties file");

    }

    public String getRefreshToken(){
        String property = properties.getProperty("refresh_token");
        if (property != null)
            return property;
        else throw new RuntimeException(" refreshToken is not specified in the configuration.properties file");

    }

    public String getUserId(){
        String property = properties.getProperty("user_id");
        if (property != null)
            return property;
        else throw new RuntimeException(" userId is not specified in the configuration.properties file");

    }
}
