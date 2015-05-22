/**
 * Created by shubham.tyagi on 08/05/15.
 */
import org.apache.maven.plugins.annotations.Parameter;

public class Template {
    @Parameter(required = true)
    String templateName;

    @Parameter(required = true)
    String templateLocation;

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setTemplateLocation(String templateLocation) {
        this.templateLocation = templateLocation;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getTemplateLocation() {
        return templateLocation;
    }
}