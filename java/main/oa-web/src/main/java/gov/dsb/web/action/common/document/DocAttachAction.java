package gov.dsb.web.action.common.document;

import gov.dsb.core.dao.DocDocumentAttachDao;
import gov.dsb.core.domain.DocDocumentAttach;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.core.utils.FileHelp;
import gov.dsb.core.utils.MimeType;
import gov.dsb.web.service.DocumentManager;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static gov.dsb.web.service.DocumentManager.PIC_ALLOWED_EXTS;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-8-6
 * Time: 11:07:13
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
public class DocAttachAction extends CRUDActionSupport<DocDocumentAttach> {

    @Autowired
    private DocDocumentAttachDao service;

    @Autowired
    private DocumentManager documentManager;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String save() throws Exception {
        service.save(entity);
        return null;
    }

    public String delete() throws Exception {
        service.delete(id);
        return null;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            } else {
                entity = new DocDocumentAttach();
            }
        }
    }

    public DocDocumentAttach getModel() {
        return entity;
    }

    public String displayPic() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        String attachPath = documentManager.getAttachPath(entity.getId());
        String fileExt = FileHelp.getFileExt(attachPath);

        ServletOutputStream outputStream = response.getOutputStream();

        if (FileHelp.exsist(attachPath) && ArrayUtils.contains(PIC_ALLOWED_EXTS, fileExt.toLowerCase())) {
            response.setContentType("image/" + fileExt);
            InputStream inputStream = new FileInputStream(attachPath);
            IOUtils.copy(inputStream, outputStream);
        } else {
            response.setContentType("image/gif");
            attachPath = request.getSession().getServletContext().getRealPath("") + File.separator +
                    documentManager.getDefaultPicPath();
            InputStream inputStream = new FileInputStream(attachPath);
            IOUtils.copy(inputStream, outputStream);
        }

        return null;
    }

    public String download() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        String attachPath = documentManager.getAttachPath(entity.getId());
        String fileExt = FileHelp.getFileExt(attachPath);

        PrintWriter writer = response.getWriter();
        File file = new File(attachPath);
        if (!file.exists()) {
            writer.print("<script language='JavaScript'>alert('");
            writer.print(new String("对不起，本文件不存在!".getBytes(), "ISO8859-1"));
            writer.println("');</script>");
        } else {
            response.reset();
            response.setHeader("Server", "pdampmis");
            //告诉客户端允许断点续传多线程连接下载
            //响应的格式是: Accept-Ranges: bytes
            //response.setHeader("Accept-Ranges", "bytes");            //old code

            // OK download file name
            response.setHeader("Content-disposition", "attachment; filename=" + new String(entity.getFilename().getBytes("GBK"), "ISO-8859-1"));

            MimeType mimeType = MimeType.guessFromName(file);
            if (null != mimeType) {
                response.setContentType(mimeType.getMimeType());
            } else {
                response.setContentType("APPLICATION/OCTET-STREAM");
            }
            FileInputStream fileinputStream = new FileInputStream(file);
            int i;
            while ((i = fileinputStream.read()) != -1) {
                writer.write(i);
            }
        }

        return null;
    }
}