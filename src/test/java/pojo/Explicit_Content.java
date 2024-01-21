package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Explicit_Content {

    @JsonProperty("filter_enabled")
    boolean filter_enabled;

    @JsonProperty("filter_locked")
    boolean filter_locked;
}
