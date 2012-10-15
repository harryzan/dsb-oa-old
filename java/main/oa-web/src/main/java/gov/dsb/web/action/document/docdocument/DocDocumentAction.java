package gov.dsb.web.action.document.docdocument;

import gov.dsb.core.dao.*;
import gov.dsb.core.domain.*;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.core.utils.FileHelp;
import gov.dsb.web.security.UserSessionService;
import gov.dsb.web.service.DocumentManager;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-14
 * Time: 16:03:34
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "doc-document-grid", type = "chain")})
public class DocDocumentAction extends CRUDActionSupport<DocDocument> {

    @Autowired
    private DocumentManager documentManager;

    @Autowired
    private DocDocumentDao service;

    @Autowired
    private DocCategoryDao docCategoryDao;

    @Autowired
    private SysCodeListDao sysCodeListDao;

    @Autowired
    private SysCodeDao sysCodeDao;

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private DocDocumentAttachDao docDocumentAttachDao;

    @Autowired
    private UserSessionService userSessionService;

    protected Long id;

    private Long doccategoryid;

    private Long formatid;

    private Long propertyid;

    private Long deptid;

    private String modelname;

    private Collection<SysCodeList> properties;

    private Collection<SysCodeList> formats;

    private Collection<DocDocumentAttach> attachs;

    //////////
    private File[] upload;//self defined upload files

    //struts2 file upload value
    private String[] uploadFileName;

    private String[] uploadContentType;
    //end struts2 file upload value

    //upload files content and description

    private String[] uploadattachcontents;

    private String[] uploadattachdescriptions;
    /////////////

    //在更新的时候将以前的附件进行修改后上传到服务的属性

    private Long[] attachids;

    private String[] existattachcontents;

    private String[] existattachdescriptions;
    /////////

    private String gridParam;

    public String getGridParam() {
        return gridParam;
    }

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoccategoryid() {
        return doccategoryid;
    }

    public void setDoccategoryid(Long doccategoryid) {
        this.doccategoryid = doccategoryid;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public Collection<SysCodeList> getProperties() {
        return properties;
    }

    public void setProperties(Collection<SysCodeList> properties) {
        this.properties = properties;
    }

    public Collection<SysCodeList> getFormats() {
        return formats;
    }

    public void setFormats(Collection<SysCodeList> formats) {
        this.formats = formats;
    }

    public Long getFormatid() {
        return formatid;
    }

    public void setFormatid(Long formatid) {
        this.formatid = formatid;
    }

    public Long getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(Long propertyid) {
        this.propertyid = propertyid;
    }

    ///////////

    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String[] getUploadattachcontents() {
        return uploadattachcontents;
    }

    public void setUploadattachcontents(String[] uploadattachcontents) {
        this.uploadattachcontents = uploadattachcontents;
    }

    public String[] getUploadattachdescriptions() {
        return uploadattachdescriptions;
    }

    public void setUploadattachdescriptions(String[] uploadattachdescriptions) {
        this.uploadattachdescriptions = uploadattachdescriptions;
    }

    public Collection<DocDocumentAttach> getAttachs() {
        return attachs;
    }

    public void setAttachs(Collection<DocDocumentAttach> attachs) {
        this.attachs = attachs;
    }

    public Long[] getAttachids() {
        return attachids;
    }

    public void setAttachids(Long[] attachids) {
        this.attachids = attachids;
    }

    public String[] getExistattachcontents() {
        return existattachcontents;
    }

    public void setExistattachcontents(String[] existattachcontents) {
        this.existattachcontents = existattachcontents;
    }

    public String[] getExistattachdescriptions() {
        return existattachdescriptions;
    }

    public void setExistattachdescriptions(String[] existattachdescriptions) {
        this.existattachdescriptions = existattachdescriptions;
    }

    ///////////////

    public String save() throws Exception {
        Collection<DocDocumentAttach> docattachs = null;
        if (upload != null && upload.length > 0) {
            docattachs = fileOperation();
        }
        Map<Long, DocDocumentAttach> map = new HashMap<Long, DocDocumentAttach>(); //获取对应文档的所有附件
        if (entity.getDocdocumentattaches() != null && entity.getDocdocumentattaches().size() > 0) {
            for (DocDocumentAttach d : entity.getDocdocumentattaches()) {
                map.put(d.getId(), d);
            }
        }
        if (attachids != null && attachids.length > 0) {
            for (int i = 0; i < attachids.length; i++) {
                Long attachid = attachids[i];
                DocDocumentAttach attach = docDocumentAttachDao.get(attachid);
                attach.setContent(existattachcontents[i]);
                attach.setDescription(existattachdescriptions[i]);
                attach.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                docDocumentAttachDao.save(attach);
                map.remove(attachid);//从map中删除已经更新的附件
            }
        }
        for (DocDocumentAttach d : map.values()) {
            docDocumentAttachDao.delete(d.getId()); //map中剩余的都是要要删除的附件
        }


        if (doccategoryid != null) {
            DocCategory doccategory = docCategoryDao.get(doccategoryid);
            entity.setDoccategory(doccategory);

            if (formatid != null) {
                SysCodeList format = sysCodeListDao.get(formatid);
                entity.setFormat(format);
            }
            if (propertyid != null) {
                SysCodeList property = sysCodeListDao.get(propertyid);
                entity.setProperty(property);
            }
            if (deptid != null) {
                SysDept dept = sysDeptDao.get(deptid);
                entity.setSysdept(dept);
            }

            SysUser user = userSessionService.getCurrentSysUser();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            if (id == null) {
                entity.setCreateuser(user);
                entity.setCreatetime(time);
            }
            entity.setUpdateuser(user);
            entity.setUpdatetime(time);

            service.save(entity);
            if (docattachs != null && docattachs.size() > 0) {
                for (DocDocumentAttach docattach : docattachs) {
                    if (docattach != null) {
                        docattach.setDocdocument(entity);
                        docDocumentAttachDao.save(docattach);
                    }
                }
            }
        }
        return RELOAD;
    }

    private Collection<DocDocumentAttach> fileOperation() throws IOException {
        Collection<DocDocumentAttach> result = new ArrayList<DocDocumentAttach>();
        for (int i = 0; i < upload.length; i++) {
            try {
                DocDocumentAttach temp = new DocDocumentAttach();
                String savefilename =
                        FileHelp.getFileWithoutExt(uploadFileName[i]) + "_" + System.currentTimeMillis() + "." +
                                FileHelp.getFileExt(uploadFileName[i]);
                temp.setSavefilename(savefilename);
                temp.setFilename(uploadFileName[i]);
                temp.setContent(uploadattachcontents[i]);
                temp.setDescription(uploadattachdescriptions[i]);
                temp.setCreatetime(new Timestamp(System.currentTimeMillis()));
                temp.setFilesize(1F * upload[i].length());
                temp.setUpdatetime(new Timestamp(System.currentTimeMillis()));

                String realpath = documentManager.getDocPath(doccategoryid) + File.separator + savefilename;
                File dest = new File(realpath);
                if (!dest.exists()) {
                    dest.createNewFile();
                } else {
                    dest.delete();
                    dest.createNewFile();
                }

                InputStream in = new FileInputStream(upload[i]);
                OutputStream out = new FileOutputStream(dest);
                IOUtils.copy(in, out);

                docDocumentAttachDao.save(temp);
                result.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public String delete() throws Exception {
        service.delete(id);
        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
                attachs = entity.getDocdocumentattaches();
            } else {
                entity = new DocDocument();
            }
        }

        formats = sysCodeDao.findCodeList("format");
        properties = sysCodeDao.findCodeList("property");
    }

    public DocDocument getModel() {
        return entity;
    }

}
