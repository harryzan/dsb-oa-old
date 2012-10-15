package gov.dsb.web.service;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.DocDocumentAttachDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.DocDocumentAttach;
import gov.dsb.web.config.WebappConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-8-3
 * Time: 14:54:44
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class DocumentManager {

    public static String[] PIC_ALLOWED_EXTS = new String[]{"jpg", "jpeg", "gif", "bmp", "png"};

    @Autowired
    private WebappConfig webappConfig;

    @Autowired
    private DocCategoryDao docCategoryEntityService;

    @Autowired
    private DocDocumentAttachDao docDocumentAttachEntityService;

    public String getDocPath(Long docCategoryid) throws IOException {
        DocCategory docCategory = docCategoryEntityService.get(docCategoryid);
        return getDocPath(docCategory);
    }

    public String getDocPath(DocCategory docCategory) throws IOException {
        String absolutePath = webappConfig.getDocumentPath() + File.separator + docCategory.getPath();
        File dir = new File(absolutePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return absolutePath;
    }

    public String getAttachPath(Long docAttachid) throws IOException {
        DocDocumentAttach documentAttach = docDocumentAttachEntityService.get(docAttachid);
        return getAttachPath(documentAttach);
    }

    public String getAttachPath(DocDocumentAttach docDocumentAttach) throws IOException {
        DocCategory docCategory = docDocumentAttach.getDocdocument().getDoccategory();
        String docpath = getDocPath(docCategory);
        return docpath + File.separator + docDocumentAttach.getSavefilename();
    }

    public String getDefaultPicPath() {
        return webappConfig.getDefaultPicturePath();
    }

    public static void main(String[] args) throws IOException {
        String path = "e:\\test\\test1\\testa";
        File dir = new File(path);
        dir.mkdirs();
    }
}
