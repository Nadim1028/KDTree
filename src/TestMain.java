import java.util.Scanner;

public class TestMain {
    public static void main(String[] args)
    {
        System.out.println("Enter the value of k.");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();

        System.out.println("Enter the number of total nodes.");
        int points = scan.nextInt();
        KDTreeMain kd = new KDTreeMain();

        for(int i=0;i<points;i++)
        {
            Node newNode;
            kd.setK(k);
            newNode = kd.createNode(i);
            kd.insertValue(newNode);
        }

        System.out.println("\n");

        int num = 100;
        while(num!=0){
            System.out.println("1.Print 2.Search 3.Insert 4.Exit");
            System.out.println("Select an option.");
            num = scan.nextInt();
            System.out.println();

            if(num == 1){
                kd.traverseKDTree(kd.root);
            }

            else if(num == 2){
                System.out.println("Enter the search point's coordinates.");
                int searchKey[] = new int[2];
                for(int i=0;i<k;i++){
                    searchKey[i] = scan.nextInt();
                }

                if(kd.pointSearch(kd.root,searchKey,0))
                    System.out.println("Point is found.");
                else
                    System.out.println("Point is not found.");
            }

            else if(num == 3){
                Node newNode;
                kd.setK(k);
                newNode = kd.createNode(100);
                kd.insertValue(newNode);
            }

            else if(num == 4){
                break;
            }

            else{
                System.out.println("Select right option.");
            }
        }
    }
}
