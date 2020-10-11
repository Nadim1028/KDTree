import java.util.Scanner;

public class KDTreeMain
{
    int k;
    Node root = null;

    public void setK(int k) {
        this.k = k;
    }

    public Node createNode(int num)
    {
        Node node = new Node();
        node.coordinate = new int[k];
        node.left = null;
        node.right = null;
        node.parent = null;

        Scanner scan = new Scanner(System.in);

        if(num==100)
            System.out.println("Enter the coordinates of the node.");
        else
            System.out.println("Enter the coordinates of node number "+ (num+1) + ".");

        for(int i=0;i<k;i++){
            node.coordinate[i] = scan.nextInt();
        }

        return node;
    }

    public void insertValue(Node newNode)
    {
        if(root == null){
            root = newNode;
            return;
        }

        Node node = root;

        while(node != null)
        {
            if(newNode.coordinate[(node.depth)%k] < node.coordinate[(node.depth)%k])
            {
                if(node.left == null){
                    node.left = newNode;
                    newNode.depth = newNode.depth + 1;
                    newNode.parent = node;
                    break;
                }

                else {
                    node = node.left;
                }
            }

            else{
                if(node.right == null){
                    node.right = newNode;
                    newNode.depth = newNode.depth + 1;
                    newNode.parent = node;
                    break;
                }

                else {
                    node = node.right;
                }
            }

            newNode.depth = newNode.depth + 1;
        }
    }

    public void traverseKDTree(Node node)
    {
        if(node==null)
            return ;

        System.out.println("Depth : " + node.depth);
        System.out.print("Coordinates : ");
        for(int i=0;i<k;i++){
            System.out.print(node.coordinate[i]+" ");
        }

        System.out.println();

        System.out.print("Parent coordinates: ");
        if(node.parent != null)
        {
            for(int i=0;i<k;i++){
                System.out.print(node.parent.coordinate[i]+" ");
            }
        }
        else
            System.out.print("No parent, it is root node");

        System.out.println();

        System.out.print("Type of this node : ");
        if(node.parent==null){
            System.out.println("Root node");
        }
        else if(node.coordinate[(node.parent.depth)%k] < node.parent.coordinate[(node.parent.depth)%k]){
            System.out.println("Left child");
        }

        else{
            System.out.println("Right child");
        }

        System.out.println();

        traverseKDTree(node.left);
        traverseKDTree(node.right);
    }

    public boolean isSimilarValue(Node parent, int point[])
    {
        boolean flag=true;

        for(int i=0;i<k;i++)
        {
            if(parent.coordinate[i]!=point[i])
                flag=false;
        }
        return flag;
    }

    public boolean pointSearch(Node parent, int[] point, int depth)
    {
        if(parent == null){
            return false;
        }

        if(isSimilarValue(parent,point))
            return true;

        if(point[depth % k] < parent.coordinate[depth % k])
           return pointSearch(parent.left,point,depth+1);
        else
           return pointSearch(parent.right,point,depth+1);
    }
}
