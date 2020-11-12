public class Node {

    public Node Rnext;
    public Node Lnext;
    public int data;
    //public Node fatherNode;     先懒得实现了

    public Node(){
        this.data = 0;
    }

    public Node(int Data){
        this.data = Data;
    }

    public Node(int Data, Node rNext, Node lNext){
        this.data = Data;
        this.Rnext = rNext;
        this.Lnext = lNext;
        //this.fatherNode = null;
    }
}
