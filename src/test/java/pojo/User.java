package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @JsonProperty("country")
    String country;

    @JsonProperty("display_name")
    String display_name;

    @JsonProperty("email")
    String email;

    @JsonProperty("explicit_content")
    Explicit_Content explicit_content;

    @JsonProperty("external_urls")
    External_Urls external_urls;

    @JsonProperty("followers")
    Followers followers;

    @JsonProperty("href")
    String href;
    @JsonProperty("id")
    String id;

    @JsonProperty("images")
    List<Object> images;

    @JsonProperty("product")
    String product;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;



}
