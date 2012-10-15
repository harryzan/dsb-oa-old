package gov.dsb.web.action.document.docdocument;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.TreeActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.security.UserSessionService;
import gov.dsb.web.ui.tree.TreeBranch;
import gov.dsb.web.ui.tree.TreeNode;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-13
 * Time: 16:10:01
 * To change this template use File | Settings | File Templates.
 */
@ParentPackage("default")
@Results({@Result(name = TreeActionSupport.TREEDATA, location = "/WEB-INF/pages/common/treeData.jsp")})
public class DocCategoryTreeAction extends TreeActionSupport {
    @Autowired
    private DocCategoryDao service;

    @Autowired
    private UserSessionService userSessionService;

    private String id;

    private String imageUrl;

    private String treeData;

    public String getId() {
        return id;
    }

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
            Collection<DocCategory> mp = service.findByQuery("from DocCategory document where (document.issystem=? or document.issystem is null) and document.parent is null order by document.orderno", false);
            for (DocCategory doccategory : mp) {
                if (isPermitted(doccategory)) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setText(doccategory.getName());

                    if (doccategory.getChildren() != null && doccategory.getChildren().size() > 0) {
                        treeNode.setLeaf(false);
                    } else {
                        treeNode.setLeaf(true);
                    }

                    treeNode.setIcon(imageUrls[1]);
                    treeNode.setId("doc-category|<id>" + doccategory.getId() + "</id>");
                    treeBranch.addTreeNode(treeNode);
                }
            }
        } else if (id.startsWith("doc-category")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));
            Collection<DocCategory> mp = service.findByQuery("from DocCategory document where (document.issystem=? or document.issystem is null) and document.parent.id=? order by document.orderno", false, parentid);
            for (DocCategory doccategory : mp) {
                if (isPermitted(doccategory)) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setText(doccategory.getName());

                    if (doccategory.getChildren() != null && doccategory.getChildren().size() > 0) {
                        treeNode.setLeaf(false);
                    } else {
                        treeNode.setLeaf(true);
                    }

                    treeNode.setIcon(imageUrls[1]);
                    treeNode.setId("doc-category|<id>" + doccategory.getId() + "</id>");
                    treeBranch.addTreeNode(treeNode);
                }
            }
        }

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }


    private boolean isPermitted(DocCategory docCategory) {
        SysUser currentUser = userSessionService.getCurrentSysUser();
        Set<SysUser> editPermittedUser = docCategory.getEditPermittedUser();
        Set<SysUser> viewPermittedUser = docCategory.getViewPermittedUser();

        if ((editPermittedUser == null || editPermittedUser.size() ==0)
                && (viewPermittedUser == null || viewPermittedUser.size() == 0))
            return true;
        for (SysUser sysUser : viewPermittedUser) {
            if (sysUser.getId().equals(currentUser.getId()))
                return true;
        }
        for (SysUser sysUser : editPermittedUser) {
            if (sysUser.getId().equals(currentUser.getId()))
                return true;
        }
        return false;
    }
}
