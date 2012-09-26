package gov.dsb.web.action.system.sysrole;

import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysRoleDao;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysRole;
import gov.dsb.core.struts2.TreeActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.tree.TreeBranch;
import gov.dsb.web.ui.tree.TreeNode;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

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
public class SysPrivilegeTreeAction extends TreeActionSupport {

    @Autowired
    private SysPrivilegeDao service;

    @Autowired
    private SysRoleDao sysRoleEntityService;

    private Long sysroleid;

    private Long sysprivilegeid;

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

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }

    public Long getSysprivilegeid() {
        return sysprivilegeid;
    }

    public void setSysprivilegeid(Long sysprivilegeid) {
        this.sysprivilegeid = sysprivilegeid;
    }

    public void prepare() throws Exception {
    }

    public String treedata() throws Exception {

        Map<Long, SysPrivilege> map = new HashMap<Long, SysPrivilege>();
        if(sysroleid != null){
            SysRole sysrole = sysRoleEntityService.get(sysroleid);
            for(SysPrivilege sysprivilege : sysrole.getSysprivilegeroles()){
                map.put(sysprivilege.getId(), sysprivilege);
            }
        }

        String[] imageUrls = imageUrl.split(",");

        TreeBranch treeBranch = new TreeBranch();

        if (id.equals("root")) {
            List<SysPrivilege> sysprivileges = service.findByQuery("from SysPrivilege where parent is null order by orderno");

            for (SysPrivilege sysprivilege : sysprivileges) {
                TreeNode treeNode = new TreeNode();
                treeNode.setText(sysprivilege.getName());
                treeNode.setLeaf(sysprivilege.getLeaf());
                if(map.containsKey(sysprivilege.getId())){
                     treeNode.setIcon(imageUrls[1]);
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
                if(map.containsKey(sysprivilege.getId())){
                     treeNode.setIcon(imageUrls[1]);
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

    /**
     * 赋予权限
     * @return    TREEDATA
     * @throws Exception   .
     */
    public String frockprivilege() throws Exception {

        return treedata();
    }

    public String disfrockprivilege() throws Exception {
        return treedata();
    }

    public String forbidprivilege() throws Exception {
        return treedata();
    }


}