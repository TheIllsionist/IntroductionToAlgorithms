package Chapter12_BinarySearchTree;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    private final int key;  //关键字(一旦初始化之后即不可修改)
    private BinarySearchTree leftChild;      //左子树
    private BinarySearchTree rightChild;     //右子树
    private BinarySearchTree parent;         //父结点
    private Map<Object,Object> satellites;   //卫星数据

    /**
     * 构造函数,初始化结点的关键字,不添加卫星数据
     * @param key &nbsp 结点关键字
     */
    public BinarySearchTree(int key){
        this.key = key;
        this.satellites = new HashMap<>();
    }

    /**
     * 构造函数,初始化结点关键字,同时添加卫星数据
     * @param key &nbsp 结点关键字
     * @param satellites &nbsp 卫星数据
     * @throws Exception
     */
    public BinarySearchTree(int key,Map<Object,Object> satellites){
        this.key = key;
        this.satellites = satellites;
    }

    public int getKey() {
        return key;
    }
    public BinarySearchTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinarySearchTree leftChild) throws Exception{
        if(leftChild != null && leftChild.key > key){  //不符合二叉搜索树的性质
            throw new InvalidKeyException("不合法的左子节点,关键字大于其父结点");
        }
        this.leftChild = leftChild;
        leftChild.setParent(this);
    }

    public BinarySearchTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinarySearchTree rightChild) throws Exception {
        if(rightChild != null && rightChild.key < key){  //不符合二叉搜索树的性质
            throw new InvalidKeyException("不合法的右子结点,关键字小于其父结点");
        }
        this.rightChild = rightChild;
        rightChild.setParent(this);
    }

    public BinarySearchTree getParent() {
        return parent;
    }

    /**
     * 设置父结点的方法被封装成私有方法,并将设置父节点的操作在设置某个结点的左子结点和右子结点时同时进行
     * @param parent
     */
    private void setParent(BinarySearchTree parent) {
        //二叉搜索树性质约束条件的检查放在对child的设置中,在此处没有性质约束条件检查
        this.parent = parent;
    }

    public Map<Object, Object> getSatellites() {
        return satellites;
    }

    public void setSatellites(Map<Object, Object> satellites) {
        this.satellites = satellites;
    }

    public void addSatellite(Object key,Object value){
        this.satellites.put(key,value);
    }

    public void removeSatellite(Object key){
        this.satellites.remove(key);
    }

    /**
     * 非递归中序遍历二叉搜索树,按序输出以当前对象为根的子树的所有关键字
     */
    public void inOrderTreeWork(){
        BinarySearchTree cur = this;
        Stack<BinarySearchTree> stack = new Stack<>();
        while(true){
            while(cur != null){
                stack.push(cur);
                cur = cur.leftChild;
            }
            while(!stack.isEmpty() && stack.peek().rightChild == null){
                System.out.print(stack.pop().key + "  ");
            }
            if(stack.isEmpty()){
                break;
            }
            System.out.print(stack.peek().key + "  ");
            cur = stack.pop().rightChild;
        }
    }

    /**
     * 非递归先序遍历二叉搜索树
     */
    public void preOrderTreeWork(){
        BinarySearchTree cur = this;
        Stack<BinarySearchTree> stack = new Stack<>();
        stack.push(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            System.out.print(cur.key + "  ");
            if(cur.rightChild != null){
                stack.push(cur.rightChild);
            }
            if(cur.leftChild != null){
                stack.push(cur.leftChild);
            }
        }
    }

    /**
     * 非递归后序遍历二叉搜索树
     */
    public void postOrderTreeWord(){
        BinarySearchTree h = this;  //刚刚弹出的结点
        BinarySearchTree cur = null; //当前栈顶结点
        Stack<BinarySearchTree> stack = new Stack<>();
        stack.push(this);
        do{
            cur = stack.peek();
            if(cur.leftChild != null && h != cur.leftChild && h != cur.rightChild){ //还没有处理过当前栈顶结点的左子树
                stack.push(cur.leftChild);
            }else if(cur.rightChild != null && h != cur.rightChild){  //还没有处理过当前栈顶结点的右子树
                stack.push(cur.rightChild);
            }else {  //当前栈顶结点的左右子树都已处理过
                System.out.print(stack.peek().key + "  ");
                h = stack.pop(); //弹出栈顶结点,h等于刚刚弹出的结点
            }
        }while (!stack.isEmpty());
    }

    /**
     * 根据关键字key搜索二叉搜索树结点
     * 如果树中存在关键字为key的结点,则返回以该结点为根的子树,如果没有则返回null
     * @param key &nbsp 给定的关键字
     * @return
     */
    public BinarySearchTree search(int key){
        BinarySearchTree res = this;
        while(res != null && res.key != key){
            if(res.key < key){
                res = res.leftChild;
            }
            res = res.rightChild;
        }
        return res;
    }

    /**
     * 查找一个二叉搜索树中具有的关键字最小的结点,返回以该结点为根的子树
     * @param treeNode &nbsp 需要查找其最小关键字结点的二叉搜索树
     * @return
     */
    public static BinarySearchTree minimum(BinarySearchTree treeNode){
        while(treeNode.leftChild != null){
            treeNode = treeNode.leftChild;
        }
        return treeNode;
    }

    /**
     * 查找一个二叉搜索树中具有的关键字最大的结点,返回以该结点为根的子树
     * @param treeNode &nbsp 需要查找其最大关键字结点的二叉搜索树
     * @return
     */
    public static BinarySearchTree maximum(BinarySearchTree treeNode){
        while(treeNode.rightChild != null){
            treeNode = treeNode.rightChild;
        }
        return treeNode;
    }

    /**
     * 查找一个二叉搜索树中某个结点在中序遍历序列中的后继结点
     * 如果该结点存在后继结点,则返回以该后继结点为根的子树,如果该结点没有后继结点则返回NULL
     * @param treeNode
     * @return
     */
    public static BinarySearchTree successor(BinarySearchTree treeNode){
        if(treeNode.rightChild != null){  //该结点有右子树
            return minimum(treeNode.rightChild);  //返回右子树中关键字最小的结点
        }else{    //该结点没有右子树
            BinarySearchTree par = treeNode.parent;
            //往上走,找到上面最近的满足接下来条件的结点:该结点是treeNode的祖先,且该结点的左孩子是treeNode或该结点的左孩子也是treeNode的祖先
            while(par != null && treeNode == par.rightChild){
                treeNode = par;
                par = treeNode.parent;
            }
            return par;
        }
    }

    /**
     * 查找一个二叉搜索树中某个结点在中序遍历序列中的前驱结点
     * 如果该结点存在前驱结点,则返回以该前驱结点为根的子树,如果该结点没有前驱结点则返回NULL
     * @param treeNode
     * @return
     */
    public static BinarySearchTree predecessor(BinarySearchTree treeNode){
        if(treeNode.leftChild != null){  //该结点有左子树
            return maximum(treeNode.leftChild);  //返回左子树中关键字最大的结点
        }else{
            BinarySearchTree par = treeNode.parent;
            //往上走,找到上面最近的满足接下来条件的结点:该结点是treeNode的祖先,且该结点的右孩子是treeNode或该结点的右孩子也是treeNode的祖先
            while(par != null && treeNode == par.leftChild){
                treeNode = par;
                par = treeNode.parent;
            }
            return par;
        }
    }

}
