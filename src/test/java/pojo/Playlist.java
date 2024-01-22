package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Getter
@Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {
    @JsonProperty("name")
    String name;

    @JsonProperty("public")
    Boolean _public;

    @JsonProperty("collaborative")
    Boolean collaborative;

    @JsonProperty("description")
    String description;

    @JsonProperty("followers")
    Followers followers;

    @JsonProperty("external_urls")
    External_Urls external_urls;

    @JsonProperty("href")
    String href;

    @JsonProperty("id")
    String id;

    @JsonProperty("images")
    List<Object> images;

    @JsonProperty("owner")
    Owner owner;

    @JsonProperty("snapshot_id")
    String snapshot_id;

    @JsonProperty("tracks")
    Tracks tracks;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;

    @JsonProperty("primary_color")
    Object primaryColor;
}
