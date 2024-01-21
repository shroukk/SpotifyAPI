package pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Followers {
    @JsonProperty("href")
    private String href = null;

    @JsonProperty("total")
    private int total;
}
