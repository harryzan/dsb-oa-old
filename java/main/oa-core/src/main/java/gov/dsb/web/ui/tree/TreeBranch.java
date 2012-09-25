package gov.dsb.web.ui.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-15
 * Time: 21:26:30
 * To change this template use File | Settings | File Templates.
 */
public class TreeBranch {

    private List<TreeNode> treeNodes;

    public TreeBranch() {
        this.treeNodes = new ArrayList<TreeNode>();
    }

    public void addTreeNode(TreeNode treeNode) {
        treeNodes.add(treeNode);
    }

    /**
     * 将全部节点转化为json格式[{attribute1:"aa",attribute2:"bb"},...]
     *
     * @return
     */
    public String toJsonString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (TreeNode treeNode : treeNodes) {
            sb.append(treeNode.toJsonString());
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
//        TreeNode treeNode = new TreeNode();
//        treeNode.setData("ABC");
//        treeNode.setIcons("/sfds/");
//        treeNode.setState("open");
//        Map map = new HashMap();
//        map.put("id","12");
//        map.put("rel","folder");
//        treeNode.setAttributes(map);
//
//        TreeBranch treeBranch = new TreeBranch();
//        treeBranch.addTreeNode(treeNode);
//        treeBranch.addTreeNode(treeNode);
//        treeBranch.addTreeNode(treeNode);
//        System.out.println("+treeNode.toString() = " +treeBranch.toJsonString());
    }
}
