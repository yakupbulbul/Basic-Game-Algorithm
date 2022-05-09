
import java.util.*;

class GenericTree{
    public static void main(String[] args) {
        GenericTree tree=new GenericTree();
        System.out.println("A loses paths: ");
        tree.printAllRootToLeafPaths(tree.root, "A");
        System.out.println("B loses paths: ");
        tree.printAllRootToLeafPaths(tree.root, "B");

    }
    class Node{
        String data;
        LinkedList<Node> children;
        Node(String data){
            this.data=data;
            children= new LinkedList<>();
        }
    }

    private Node root;

    GenericTree(){
        this.root=constructGT(null,0);
    }

    private Node constructGT(Node parent,int i) {
        Scanner sc= new Scanner(System.in);
        String data="";
        if(parent==null){
            //System.out.println("Enter the data for the root node...");
            data=sc.next();
        }else{
            //System.out.println("Enter the data for the "+i+" th child of: "+parent.data);
            data=list(i, parent.data);
        }

        Node node =new Node(data);
        int number =lengthFinder(node.data);
        //System.out.println("Enter the number of children for "+node.data+" is the(number of children): "+number);
        for(int j=0; j<number; j++) {
            Node child= constructGT(node,j);
            node.children.add(child);
        }
        return node;
    }

    public String sort(int a []){
        String list="";

        for(int j=1; j<a.length; j++) {
            int temp=a[j];
            int i;

            for(i=j; i>0 && a[i-1]>temp; i--) {
                a[i]=a[i-1];
            }
            a[i]=temp;
        }
        for(int i=a.length-1; i>=0; i--) {
            if(i==0){
                list=list+a[i];
            }else{
                list=list+a[i]+", ";

            }
        }

        return list;
    }

    public String  list(int num, String str){

        String items[] = str.split(", ");
        int length=0;
        int reminder=0;
        int ent[] = new int[items.length];
        for(int i=0;i<items.length;i++){
            try{
                ent[i] = Integer.parseInt(items[i]);
            }catch(NumberFormatException e){
            }
        }
        ArrayList<String> result= new ArrayList<>();
        for(int i=0;i<ent.length;i++){
            try{
                if(reminder!=ent[i]){
                    int numberarray[] = new int[items.length+1];
                    int counter=0;
                    if(ent[i]%2==0){
                        length=ent[i]/2-1;
                    }else{
                        length=ent[i]/2;
                    }
                    for(int j=0; j<ent.length; j++){
                        if(i!=j){
                            numberarray[counter]=ent[j];
                            counter++;
                        }

                    }

                    for(int j=0; j<length; j++){
                        int x=counter;
                        numberarray[x]=ent[i]-(j+1);
                        x++;
                        numberarray[x]=(j+1);
                        int list2 [] =new int[numberarray.length];
                        for(int k=0; k<numberarray.length; k++){
                            list2[k]=numberarray[k];
                        }
                        String nem=sort(list2);
                        result.add(nem);

                    }

                }
                reminder=ent[i];
            }catch(NumberFormatException e){
            }
        }
        return result.get(num);

    }

    public int lengthFinder(String numbers){
        String items[] = numbers.split(", ");
        int length=0;
        int reminder=0;
        int ent[] = new int[items.length];
        for(int i=0;i<items.length;i++){
            try{
                ent[i] = Integer.parseInt(items[i]);
                if(ent[i]!=reminder){
                    if(ent[i]%2==0){
                        length=length+ent[i]/2-1;
                    }else{
                        length=length+ent[i]/2;
                    }
                }
                reminder=ent[i];
            }catch(NumberFormatException e){
                //Error
            }
        }
        return length;
    }

    public void printPath(ArrayList<String> path, String type) {


        if (type=="A"){
            for (String list : path)
            {
                if (path.size()%2!=0){
                    System.out.print("["+list + "] ");

                }
            }
            if (path.size()%2!=0){
                System.out.println();
            }
        }
        if (type=="B"){
            for (String list : path)
            {
                if (path.size()%2==0){
                    System.out.print("["+list + "] ");

                }
            }
            if (path.size()%2==0){
                System.out.println();
            }
        }
    }

    public void printAllRootToLeafPaths(Node root, ArrayList<String> path, String type) {

        if (root == null){
            return;
        }

        path.add(root.data);

        if (root.children.isEmpty()) {
            printPath(path, type);
            path.remove(path.size() - 1);
            return;
        }

        for (int i = 0; i < root.children.size(); i++){
            printAllRootToLeafPaths(root.children.get(i), path, type);
        }
        path.remove(path.size() - 1);
    }

    public void printAllRootToLeafPaths(Node root, String type) {

        if (root == null){
            return;
        }

        ArrayList<String> list = new ArrayList<>();

        printAllRootToLeafPaths(root, list, type);

    }
}
