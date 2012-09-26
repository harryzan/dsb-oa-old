package gov.dsb.web.action.system.sysuser;

import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.dao.SysUserPrivilegeDao;
import gov.dsb.core.domain.SysPrivilege;
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
 */
@ParentPackage("default")
@Results({@Result(name = TreeActionSupport.TREEDATA, location = "/WEB-INF/pages/common/treeData.jsp")})
public class SysPrivilegeTreeAction extends TreeActionSupport {

    @Autowired
    private SysPrivilegeDao service;

    @Autowired
    private SysUserDao sysUserEntityService;

    @Autowired
    private SysUserPrivilegeDao sysUserPrivilegeEntityService;

    private Long sysuserid;

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

    public Long getSysuserid() {
        return sysuserid;
    }

    public void setSysuserid(Long sysuserid) {
        this.sysuserid = sysuserid;
    }

    public void prepare() throws Exception {
    }

    public String treedata() throws Exception {
        String[] imageUrls = imageUrl.split(",");

        TreeBranch treeBranch = new TreeBranch();


        Collection<SysPrivilege> userPrivileges = sysUserEntityService.getUserprivileges(sysuserid);
        Collection<SysPrivilege> rolePrivileges = sysUserEntityService.getRolePrivileges(sysuserid);

        if (id.equals("root")) {
            List<SysPrivilege> sysprivileges = service.findByQuery("from SysPrivilege where parent is null order  by orderno");

            for (SysPrivilege sysprivilege : sysprivileges) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(sysprivilege.getName());
                treeNode.setLeaf(sysprivilege.getLeaf());

                boolean flaguser = containPrivilege(userPrivileges, sysprivilege.getId());
                boolean flagrole = containPrivilege(rolePrivileges, sysprivilege.getId());

                if (flaguser && flagrole) {
                    if (sysUserPrivilegeEntityService.isForbid(sysuserid, sysprivilege.getId())) {
                        treeNode.setIcon(imageUrls[6]);
                    }
                    else {
                        treeNode.setIcon(imageUrls[5]);
                    }
                }
                else if (!flaguser && flagrole) {
                    treeNode.setIcon(imageUrls[1]);
                }
                else if (flaguser && !flagrole) {
                    if (sysUserPrivilegeEntityService.isForbid(sysuserid, sysprivilege.getId())) {
                        treeNode.setIcon(imageUrls[4]);
                    }
                    else {
                        treeNode.setIcon(imageUrls[3]);
                    }
                }
                else {
                    treeNode.setIcon(imageUrls[2]);
                }

                treeNode.setId("sys-privilege|<id>" + sysprivilege.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }

        } else if (id.startsWith("sys-privilege")) {
            Long parentid = Long.valueOf(StringHelp.getElementValue(id, "id"));

            List<SysPrivilege> sysprivileges = service.findByQuery("from SysPrivilege where parent.id=? order by orderno", parentid);

            for (SysPrivilege sysprivilege : sysprivileges) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(sysprivilege.getName());
                treeNode.setLeaf(sysprivilege.getLeaf());

                boolean flaguser = containPrivilege(userPrivileges, sysprivilege.getId());
                boolean flagrole = containPrivilege(rolePrivileges, sysprivilege.getId());
                if (flaguser && flagrole) {
                    if (sysUserPrivilegeEntityService.isForbid(sysuserid, sysprivilege.getId())) {
                        treeNode.setIcon(imageUrls[6]);
                    }
                    else {
                        treeNode.setIcon(imageUrls[5]);
                    }
                }
                else if (!flaguser && flagrole) {
                    treeNode.setIcon(imageUrls[1]);
                }
                else if (flaguser && !flagrole) {
                    if (sysUserPrivilegeEntityService.isForbid(sysuserid, sysprivilege.getId())) {
                        treeNode.setIcon(imageUrls[4]);
                    }
                    else {
                        treeNode.setIcon(imageUrls[3]);
                    }
                }
                else {
                    treeNode.setIcon(imageUrls[2]);
                }
                
                treeNode.setId("sys-privilege|<id>" + sysprivilege.getId() + "</id>");

                treeBranch.addTreeNode(treeNode);
            }
          
        }

        treeData = treeBranch.toJsonString();
        return TREEDATA;
    }

    private boolean containPrivilege(Collection<SysPrivilege> privileges, Long privilegeid){
       for(SysPrivilege p : privileges){
           if(p.getId().equals(privilegeid)){
               return true;
           }
       }


        return false;
    }

}