/**
 * Created by shubham.tyagi on 08/05/15.
 */
import org.apache.maven.plugins.annotations.Parameter;

public class Confluence {
    @Parameter(required = true)
    String spaceKey;

    @Parameter(required = true)
    String userName;

    @Parameter(required = true)
    String password;

    @Parameter(required = true)
    String BASE_URL;

    @Parameter(required = true)
    String pageId;

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getPageId() {
        return pageId;
    }
}
