package gov.dsb.web.config;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-8-3
 * Time: 10:19:49
 * To change this template use File | Settings | File Templates.
 */
public class WebappConfig {

    private static Timestamp starttime = new Timestamp(System.currentTimeMillis()); 

    private String documentPath;

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    private String defaultPicturePath;

    public String getDefaultPicturePath() {
        return defaultPicturePath;
    }

    public void setDefaultPicturePath(String defaultPicturePath) {
        this.defaultPicturePath = defaultPicturePath;
    }

    public Timestamp getStarttime() {
        return starttime;
    }
}
