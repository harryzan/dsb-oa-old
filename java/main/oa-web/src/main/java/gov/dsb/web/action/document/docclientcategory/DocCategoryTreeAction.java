package gov.dsb.web.action.document.docclientcategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.DocClientCategoryDao;
import gov.dsb.core.dao.DocRelateCategoryDao;
import gov.dsb.core.domain.DocCategory;
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-20
 * Time: 8:47:11
 * To change this template use File | Settings | File Templates.
 */
@ParentPackage("default")
@Results({@Result(name = TreeActionSupport.TREEDATA, location = "/WEB-INF/pages/common/treeData.jsp")})
public class DocCategoryTreeAction extends TreeActionSupport {

    @Autowired
    private DocCategoryDao service;

    @Autowired
    private DocClientCategoryDao docClientCategoryDao;

    @Autowired
    private DocRelateCategoryDao docRelateCategoryDao;

    private Long docclientcategoryid;

    private Long docrelatecategoryid;

    private String id;

    private String imageUrl;

    private String treeData;

    public Long getDocclientcategoryid() {
        return docclientcategoryid;
    }

    public void setDocclientcategoryid(Long docclientcategoryid) {
        this.docclientcategoryid = docclientcategoryid;
    }

    public Long getDocrelatecategoryid() {
        return docrelatecategoryid;
    }

    public void setDocrelatecategoryid(Long docrelatecategoryid) {
        this.docrelatecategoryid = docrelatecategoryid;
    }

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

        Map<Long, DocCategory> map = new HashMap<Long, DocCategory>();
        if (docclientcategoryid != null) {
            DocClientCategory docclient = docClientCategoryDao.get(docclientcategoryid);
            Collection<DocRelateCategory> list = docclient.getDocrelatecategories();
            for (DocRelateCategory d : list) {
                DocCategory doc = d.getDoccategory();
                if (doc != null) {
                    map.put(doc.getId(), doc);
                }
            }
        } else if (docrelatecategoryid != null) {
            DocRelateCategory docrelate = docRelateCategoryDao.get(docrelatecategoryid);
            if (docrelate != null) {
                DocClientCategory docclient = docrelate.getDocclientcategory();
                Collection<DocRelateCategory> list = docclient.getDocrelatecategories();
                for (DocRelateCategory d : list) {
                    DocCategory doc = d.getDoccategory();
                    if (doc != null) {
                        map.put(doc.getId(), doc);
                    }
                }
            }
        }

        TreeBranch treeBranch = new TreeBranch();

        if (id.equals("root")) {
            List<DocCategory> doccategorys = service.findByQuery("from DocCategory document where document.parent is null order by document.orderno");

            for (DocCategory doccategory : doccategorys) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(doccategory.getName());
                treeNode.setLeaf(doccategory.getLeaf());
                if (map.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[2]);
                    treeNode.setId("doc-category2|<id>" + doccategory.getId() + "</id>");
                } else {
                    treeNode.setIcon(imageUrls[1]);
                    treeNode.setId("doc-category1|<id>" + doccategory.getId() + "</id>");
                }

                treeBranch.addTreeNode(treeNode);
            }

        } else if (id.startsWith("doc-category")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));
            List<DocCategory> list = service.findByQuery("from DocCategory document where document.parent.id=? order by document.orderno", parentid);

            for (DocCategory doccategory : list) {
                TreeNode treeNode = new TreeNode();
                if (map.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[2]);
                    treeNode.setId("doc-category2|<id>" + doccategory.getId() + "</id>");
                } else {
                    treeNode.setIcon(imageUrls[1]);
                    treeNode.setId("doc-category1|<id>" + doccategory.getId() + "</id>");
                }
                treeNode.setText(doccategory.getName());
                treeNode.setLeaf(doccategory.getLeaf());

                treeBranch.addTreeNode(treeNode);
            }
        }

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }
}