package gov.dsb.web.action.document.doccategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.struts2.TreeActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.tree.TreeBranch;
import gov.dsb.web.ui.tree.TreeNode;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

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
            Collection<DocCategory> mp = service.findByQuery("from DocCategory document where document.parent is null order by orderno");
            for (DocCategory doccategory : mp) {
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
        } else if (id.startsWith("doc-category")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));
            Collection<DocCategory> mp = service.findByQuery("from DocCategory document where document.parent.id=? order by orderno", parentid);
            for (DocCategory doccategory : mp) {
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

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }

}
