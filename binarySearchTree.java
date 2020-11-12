import java.util.ArrayList;
import java.util.Scanner;

public class binarySearchTree {

    //public int size;
    private ArrayList<Node> nodeArrayList;

    private binarySearchTree(int headData){
        Node head = new Node();
        this.nodeArrayList = new ArrayList<>();
        nodeArrayList.add(head);
        head.data = headData;
    }

    //改下对arrayList长度的操作
    //先实现下指针 拿指针来完善删功能吧
    //指针操作省了很多空间复杂度

    //测试完成
    private void addNode(int num, int index){ //操不知道怎么比第一个 arrayList的index忘了是咋样的了 啊貌似问题不大
        if(nodeArrayList.get(index).data != 0) {
            if (num < nodeArrayList.get(index).data) {
                if (nodeArrayList.size() > index * 2 + 1) {
                    addNode(num, index * 2 + 1);
                }
                else {
                    for(int i = index; i < index * 2 + 1; i++){
                        Node tempNode = new Node();
                        nodeArrayList.add(tempNode);
                    }
                    nodeArrayList.get(index * 2 + 1).data = num;
                    //这里先直接接上指针吧 后期再做离散的
                    nodeArrayList.get(index).Lnext = nodeArrayList.get(index * 2 + 1 );  //不懒了先实现指针吧
                }
            }
            if (num > nodeArrayList.get(index).data) {
                if (nodeArrayList.size() > index * 2 + 2) {
                    addNode(num, index * 2 + 2);
                }
                else {
                    for(int i = index; i < index * 2 + 2; i++){
                        Node tempNode = new Node();
                        nodeArrayList.add(tempNode);
                    }
                    nodeArrayList.get(index * 2 + 2).data = num;
                    nodeArrayList.get(index).Rnext = nodeArrayList.get(index * 2 + 2);
                }
            }
        }
        else{
            nodeArrayList.get(index).data = num;
        }
    }

    //测试完成
    private void pointerAddition(Node newNode, Node pointer){
        if(pointer.Lnext != null && pointer.Rnext != null) {
            if (newNode.data < pointer.data) {
                pointerAddition(newNode, pointer.Lnext);
            }
            if (newNode.data > pointer.data) {
                pointerAddition(newNode, pointer.Rnext);
            }
        }
        else{
            nodeArrayList.add(newNode);
            if (newNode.data < pointer.data){
                pointer.Lnext = newNode;
            }
            else{
                pointer.Rnext = newNode;
            }
        }
    }

    //（还没测完 测不动了呜呜
    private void deleteNode(int target){   //也是不知道怎么处理第一和二个唉 问题不大指针实现了
        int index = search(target, 0);
        if(nodeArrayList.get(index).Rnext == null && nodeArrayList.get(index).Lnext == null){
            nodeArrayList.remove(index);
        }
        else if(nodeArrayList.get(index).Rnext != null && nodeArrayList.get(index).Lnext == null){
            nodeArrayList.remove(index);
            nodeArrayList.set(index,nodeArrayList.get(index*2+1));
            if(index % 2 == 0){
                nodeArrayList.get((index-1)/2).Rnext = nodeArrayList.get(index);
            }
            else{
                nodeArrayList.get((index-1)/2).Lnext = nodeArrayList.get(index);
            }
        }
        else if(nodeArrayList.get(index).Rnext != null && nodeArrayList.get(index).Lnext != null){
            Node pointer = nodeArrayList.get(index*2+1);
            //int temp = 0;
            while(pointer.Lnext != null || pointer.Rnext != null){
                pointer = (pointer.Rnext != null) ? pointer.Rnext : pointer.Lnext;
            }
            nodeArrayList.remove(index);
            nodeArrayList.set(index,pointer);
            if(index % 2 == 0){
                nodeArrayList.get((index-1)/2).Rnext = nodeArrayList.get(index);
            }
            else{
                nodeArrayList.get((index-1)/2).Lnext = nodeArrayList.get(index);
            }
        }
    }

    //测试完成
    private int pointerSearch(int target, Node targetedNode){
        if(target == targetedNode.data){
            return nodeArrayList.indexOf(targetedNode);
        }
        if(target > targetedNode.data){
            return pointerSearch(target, targetedNode.Rnext);
        }
        if(target < targetedNode.data){
            return pointerSearch(target, targetedNode.Lnext);
        }
        return -1;
    }

    //测试完成
    private int search(int target,int index){
        if(index >= nodeArrayList.size()){
            System.out.print("貌似找不到 哼唧");
            return -1;
        }
        if(nodeArrayList.get(index).data == 0){
            System.out.print("貌似找不到 哼唧");
            return -1;
        }
        if(nodeArrayList.get(index).data == target){
            return index;
        }
        index = (target > nodeArrayList.get(index).data) ? index * 2 + 2 : index * 2 + 1;
        return search(target, index);
    }

    //怎么完全遍历整个二叉树啊呜呜
    private void printTree(Node pointerNode){
//        if(pointerNode.Rnext == null && pointerNode.Lnext == null){
////            System.out.print(pointerNode.data + " ");
////            return;
////        }
////        if(pointerNode.Rnext != null && pointerNode.Lnext == null){
////            System.out.print(pointerNode.data+" "+pointerNode.Rnext.data);
////            return;
////        }
////        if(pointerNode.Rnext == null && pointerNode.Lnext != null){
////            System.out.print(pointerNode.data+" "+pointerNode.Lnext.data);
////            return;
////        }
////        System.out.print(pointerNode.data+" "+pointerNode.Lnext.data+" "+pointerNode.Rnext.data+" ");

//这里貌似实现了个二叉树前序遍历耶！
////        printTree(pointerNode.Lnext);
////        printTree(pointerNode.Rnext);
    }

    //应该是这样的吧 后序遍历？
    //测得有问题 会输出一堆0
    private void sort(int i, Node pointer, int[] list){
        if(pointer.Lnext == null || pointer.Rnext == null){
            return;
        }
        list[i] = pointer.data;
        sort(i++, pointer.Rnext, list);
        sort(i++, pointer.Lnext, list);
    }

    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        binarySearchTree testTree = new binarySearchTree(15);
        for(int i = 0; i < 5;i++){
            System.out.println("input element: ");
            int num = scan.nextInt();
            Node newNode = new Node(num);
            //testTree.addNode(num, 0);
            testTree.pointerAddition(newNode, testTree.nodeArrayList.get(0));
        }
        int[] allElements = new int[testTree.nodeArrayList.size()];
        for(int i = 0; i<testTree.nodeArrayList.size();i++){
             allElements[i] = testTree.nodeArrayList.get(i).data;
        }
        for(int i : allElements){
            System.out.print(i + " ");
        }

        int[] sortedList = new int[5];
        testTree.sort(0, testTree.nodeArrayList.get(0), sortedList);
        for(int i = 0; i < 5;i++){
            System.out.println(sortedList[i]);
        }

//        System.out.print("想删哪个：");
//        int deleteTarget = scan.nextInt();
//        testTree.deleteNode(deleteTarget);
//        int[] allElements1 = new int[testTree.nodeArrayList.size()];
//        for(int i = 0; i<testTree.nodeArrayList.size();i++){
//            allElements1[i] = testTree.nodeArrayList.get(i).data;
//        }
//        for(int i : allElements1){
//            System.out.print(i + " ");
//        }

//        System.out.println("想搜啥：");
//        int searchTarget = scan.nextInt();
//        int foundIndex = testTree.pointerSearch(searchTarget, testTree.nodeArrayList.get(0));
//        if(foundIndex == -1){
//            System.out.println("浪费爷时间");
//        }
//        else{
//            foundIndex++;
//            System.out.println("位置在！：" + foundIndex);
//        }
        
    }
}
