package gov.dsb.web.action.system.sysrole;

import gov.dsb.core.dao.SysRoleDao;
import gov.dsb.core.domain.SysRole;
import gov.dsb.core.struts2.TreeActionSupport;
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
public class SysRoleTreeAction extends TreeActionSupport {

    @Autowired
    private SysRoleDao service;

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
            List<SysRole> sysroles = service.findByQuery("from SysRole");

            for (SysRole sysrole : sysroles) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(sysrole.getName());
                treeNode.setLeaf(true);
                treeNode.setIcon(imageUrls[1]);
                treeNode.setId("sys-role|<id>" + sysrole.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
        }
        
        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }


}