package gov.dsb.web.action.system.syscode;

import gov.dsb.core.dao.SysCodeDao;
import gov.dsb.core.dao.SysCodeListDao;
import gov.dsb.core.domain.SysCode;
import gov.dsb.core.domain.SysCodeList;
import gov.dsb.core.struts2.TreeActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.tree.TreeBranch;
import gov.dsb.web.ui.tree.TreeNode;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-20
 * Time: 8:47:11
 * To change this template use File | Settings | File Templates.
 */
@ParentPackage("default")
@Results({@Result(name = TreeActionSupport.TREEDATA, location = "/WEB-INF/pages/common/treeData.jsp")})
public class SysCodeTreeAction extends TreeActionSupport {

    @Autowired
    private SysCodeDao service;

    @Autowired
    private SysCodeListDao sysCodeListEntityService;

    private String id;

    private String imageUrl;

    private String treeData;

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTreeData() {
        return treeData;
    }

    public void prepare() throws Exception {
    }

    public String treedata() throws Exception {
        String[] imageUrls = imageUrl.split(",");

        TreeBranch treeBranch = new TreeBranch();

        if (id.equals("root")) {
            List<SysCode> syscodes = service.findByQuery("from SysCode where parent is null order by orderno");
            for (SysCode syscode : syscodes) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(syscode.getName());
                Collection<SysCodeList> list = syscode.getSyscodelists();//
                // sysCodeListEntityService.findByProperty("syscode.id",syscode.getId());
                treeNode.setLeaf(true);
                if(list != null && list.size() > 0) {
                    treeNode.setLeaf(false);
                }
                treeNode.setIcon(imageUrls[0]);
                treeNode.setId("syscode|<id>" + syscode.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        }else if (id.startsWith("syscodelist")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));

            List<SysCodeList> syscodelists = sysCodeListEntityService.findByQuery("from SysCodeList where parent.id=? order by orderno", parentid);//
            // .findByProperty("parent.id", parentid);
            for (SysCodeList syscodelist : syscodelists) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(syscodelist.getListname());
                treeNode.setLeaf(syscodelist.getLeaf());
                treeNode.setIcon(imageUrls[1]);
                treeNode.setId("syscodelist|<id>" + syscodelist.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        } else if (id.startsWith("syscode")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));

            List<SysCodeList> syscodelists = sysCodeListEntityService.findByQuery("from SysCodeList where syscode.id=? order by orderno", parentid);//
            //  .findByProperty("syscode.id", parentid);
            for (SysCodeList syscodelist : syscodelists) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(syscodelist.getListname());
                treeNode.setLeaf(syscodelist.getLeaf());
                treeNode.setIcon(imageUrls[1]);
                treeNode.setId("syscodelist|<id>" + syscodelist.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        }

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }
}