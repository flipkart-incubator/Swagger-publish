import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.sonatype.inject.Parameters;
import com.core.config.Configuration;

import java.util.List;

/**
 * Created by shubham.tyagi on 08/05/15.
 */


@Mojo( name = "publish", defaultPhase = LifecyclePhase.COMPILE, configurator = "include-project-dependencies",
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class ApiDocumentMojo extends AbstractMojo {
    @Parameter(required = true)
    private Confluence confluence;

    @Parameter(required = false)
    private Template template;

    @Parameter(required = true)
    private String locations;

    @Parameter(required = true)
    private String apiVersion;

    @Parameter(required = true)
    private String apiName;

    @Parameters
    private List<SecurityDefinition> securityDefinitions;

    public void setConfluence(Confluence confluence) { this.confluence = confluence; }

    public Confluence getConfluence() { return confluence; }

    public void setTemplate(Template template) { this.template = template; }

    public Template getTemplate() { return template; }

    public void setLocations(String locations) { this.locations = locations; }

    public String getLocations() { return locations; }

    public void setApiVersion(String apiVersion) { this.apiVersion = apiVersion; }

    public String getApiVersion() { return apiVersion; }

    public void setApiName(String apiName) { this.apiName = apiName; }

    public String getApiName() { return apiName; }

    public void setSecurityDefinitions(List<SecurityDefinition> securityDefinitions) { this.securityDefinitions = securityDefinitions; }

    public List<SecurityDefinition> getSecurityDefinitions() { return securityDefinitions; }
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if(this.getTemplate() == null) {
            Template template1 = new Template();
            template1.setTemplateLocation("null");
            template1.setTemplateName("null");
            this.setTemplate(template1);
            System.out.println("hello here");
        }
        Configuration configuration = new Configuration(this.getLocations(), this.getApiVersion(), this.getApiName(), this.getTemplate().getTemplateLocation(), this.getTemplate().getTemplateName(), this.getConfluence().getSpaceKey(), this.getConfluence().getPageId(), this.getConfluence().getBASE_URL(), this.getConfluence().getUserName(), this.getConfluence().getPassword(), null);
        try {
            configuration.generateDoc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}