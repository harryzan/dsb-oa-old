package gov.dsb.web.action.document.docclientcategory;

import gov.dsb.core.dao.DocClientCategoryDao;
import gov.dsb.core.dao.DocRelateCategoryDao;
import gov.dsb.core.domain.DocClientCategory;
import gov.dsb.core.domain.DocRelateCategory;
import gov.dsb.core.struts2.TreeActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.tree.TreeBranch;
import gov.dsb.web.ui.tree.TreeNode;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

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
public class DocClientCategoryTreeAction extends TreeActionSupport {

    @Autowired
    private DocClientCategoryDao service;

    @Autowired
    private DocRelateCategoryDao docRelateCategoryDao;

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
            List<DocClientCategory> docclientcategorys = service.findByQuery("from DocClientCategory document where document.parent is null order by document.orderno");
            for (DocClientCategory docclientcategory : docclientcategorys) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(docclientcategory.getName());
                if (!docclientcategory.getLeaf() || (docclientcategory.getDocrelatecategories() != null && docclientcategory.getDocrelatecategories().size() > 0)) {
                    treeNode.setLeaf(false);
                } else {
                    treeNode.setLeaf(true);
                }
                treeNode.setIcon(imageUrls[1]);
                treeNode.setId("doc-client-category|<id>" + docclientcategory.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }

        } else if (id.startsWith("doc-client-category")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));

            List<DocClientCategory> list = service.findByQuery("from DocClientCategory document where document.parent.id=? order by document.orderno", parentid);

            for (DocClientCategory docclientcategory : list) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(docclientcategory.getName());
                if (!docclientcategory.getLeaf() || (docclientcategory.getDocrelatecategories() != null && docclientcategory.getDocrelatecategories().size() > 0)) {
                    treeNode.setLeaf(false);
                } else {
                    treeNode.setLeaf(true);
                }
                treeNode.setIcon(imageUrls[1]);
                treeNode.setId("doc-client-category|<id>" + docclientcategory.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }

            List<DocRelateCategory> rlist = docRelateCategoryDao.findByQuery("from DocRelateCategory document where document.parent is null and document.docclientcategory.id=? order by document.orderno", parentid);

            for (DocRelateCategory docrelatecategory : rlist) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(docrelatecategory.getName());
                treeNode.setIcon(imageUrls[2]);
                treeNode.setLeaf(docrelatecategory.getLeaf());
                treeNode.setId("doc-relate-category|<id>" + docrelatecategory.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        } else if (id.startsWith("doc-relate-category")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));
            List<DocRelateCategory> list = docRelateCategoryDao.findByQuery("from DocRelateCategory document where document.parent.id=? order by document.orderno", parentid);

            for (DocRelateCategory docrelatecategory : list) {
                TreeNode treeNode = new TreeNode();
                treeNode.setIcon(imageUrls[2]);
                treeNode.setText(docrelatecategory.getName());
                treeNode.setLeaf(docrelatecategory.getLeaf());
                treeNode.setId("doc-relate-category|<id>" + docrelatecategory.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        }

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }
}