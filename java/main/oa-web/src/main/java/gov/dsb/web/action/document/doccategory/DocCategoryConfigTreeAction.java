package gov.dsb.web.action.document.doccategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.SysDeptDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.SysDept;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysUser;
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
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-13
 * Time: 16:10:01
 * To change this template use File | Settings | File Templates.
 */
@ParentPackage("default")
@Results({@Result(name = TreeActionSupport.TREEDATA, location = "/WEB-INF/pages/common/treeData.jsp")})
public class DocCategoryConfigTreeAction extends TreeActionSupport {
    @Autowired
    private DocCategoryDao service;

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private SysPrivilegeDao sysPrivilegeDao;

    @Autowired
    private SysUserDao sysUserDao;

    private String id;

    private String imageUrl;

    private String treeData;

    ///////////////////

    private Long deptid;

    private Long userid;

    private Long privilegeid;

    private Long type;

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

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
        type = 1L;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
        type = 2L;
    }

    public Long getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Long privilegeid) {
        this.privilegeid = privilegeid;
        type = 3L;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public void prepare() throws Exception {
    }

    public String treedata() throws Exception {
        String[] imageUrls = imageUrl.split(",");

        TreeBranch treeBranch = new TreeBranch();

        Map<Long, DocCategory> mapview = new HashMap<Long, DocCategory>();
        Map<Long, DocCategory> mapedit = new HashMap<Long, DocCategory>();
        if (deptid != null) {
            SysDept sysdept = sysDeptDao.get(deptid);
            for (DocCategory d : sysdept.getViewDocPermitted()) {
                mapview.put(d.getId(), d);
            }
            for (DocCategory d : sysdept.getEditDocPermitted()) {
                mapedit.put(d.getId(), d);
            }
        } else if (userid != null) {
            SysUser sysuser = sysUserDao.get(userid);
            for (DocCategory d : sysuser.getEditDocPermitted()) {
                mapedit.put(d.getId(), d);
            }
            for (DocCategory d : sysuser.getViewDocPermitted()) {
                mapview.put(d.getId(), d);
            }
        } else if (privilegeid != null) {
            SysPrivilege sysprivilege = sysPrivilegeDao.get(privilegeid);
            for (DocCategory d : sysprivilege.getEditDocPermitted()) {
                mapedit.put(d.getId(), d);
            }
            for (DocCategory d : sysprivilege.getViewDocPermitted()) {
                mapview.put(d.getId(), d);
            }
        }

        if (id.equals("root")) {
            Collection<DocCategory> mp = service.findByQuery("from DocCategory document where document.parent is null order by document.orderno");
            for (DocCategory doccategory : mp) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(doccategory.getName());

                if (doccategory.getChildren() != null && doccategory.getChildren().size() > 0) {
                    treeNode.setLeaf(false);
                } else {
                    treeNode.setLeaf(true);
                }


                if (mapview.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[0]);
                } else if (mapedit.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[1]);
                } else {
                    treeNode.setIcon(imageUrls[2]);
                }

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

                if (mapview.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[0]);
                } else if (mapedit.containsKey(doccategory.getId())) {
                    treeNode.setIcon(imageUrls[1]);
                } else {
                    treeNode.setIcon(imageUrls[2]);
                }

                treeNode.setId("doc-category|<id>" + doccategory.getId() + "</id>");
                treeBranch.addTreeNode(treeNode);
            }
        }

        treeData = treeBranch.toJsonString();

        return TREEDATA;
    }

}
