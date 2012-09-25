package gov.dsb.web.ui.tree;

import gov.dsb.core.utils.StringHelp;

/**
 * User: tcg
 * Date: 2009-3-15
 * Time: 21:02:24
 */
public class TreeNode {

    private String id;

    //节点默认为叶子节点
    private boolean isLeaf = true;

    //节点文本
    private String text;

    //节点图标
    private String icon;

    private String qtip;

    public TreeNode() {
    }

    public TreeNode(String id, boolean leaf, String text) {
        this.id = id;
        this.isLeaf = leaf;
        this.text = text;
    }

    public TreeNode(String id, boolean leaf, String text, String icon) {
        this(id, leaf, text);
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    /**
     * 将tree的单个节点转化为json格式
     *
     * @return   .
     */
    public String toJsonString() {
        StringBuffer rel = new StringBuffer();
        rel.append("{");
        if (!StringHelp.isEmpty(text)) {
            rel.append("text:").append("'").append(text).append("',");
        }
        if (!StringHelp.isEmpty(text)) {
            rel.append("id:").append("'").append(id).append("',");
        }
        rel.append("leaf:").append(isLeaf).append(",");
        if (!StringHelp.isEmpty(icon)) {
            rel.append("icon:").append("'").append(icon).append("',");
        }

        if(!StringHelp.isEmpty(qtip)){
            rel.append("qtip:").append("'").append(qtip).append("'").append(",");
        }
        //去掉","
        rel.delete(rel.length() - 1, rel.length());
        rel.append("}");
        return rel.toString();
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.setText("ABC");
        treeNode.setIcon("/sfds/");
        System.out.println("+treeNode.toString() = " + treeNode.toJsonString());
    }
}
