import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by shubham.tyagi on 08/05/15.
 */
public class SecurityDefinition {
    @Parameter
    private String name;
    @Parameter
    private String type;
    @Parameter
    private String json;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public String getJson() { return json; }

}
