package daa.project;

import java.util.Scanner;

/**
 *
 * @author hassa
 */
public class DAAProject {

    public static void main(String[] args) {

        MyGraphh graphObj = new MyGraphh(5);

        for (int i = 65; i < 70; i++) {
            String x = input();
            String y = input2();
            graphObj.addUser(x, y);
        }
        // view AdjList after each edge insertion 
        graphObj.makeFriend("A", "B");
        System.out.println("\nAdjList On The Basis Of Mutual Friends\n===============");
        graphObj.printForMutuals();
        System.out.println("\n\nAdjList On The Basis OF Workplace\n===============");
        graphObj.printForWorkplace();

        graphObj.makeFriend("B", "D");
        System.out.println("\nAdjList On The Basis Of Mutual Friends\n===============");
        graphObj.printForMutuals();
        System.out.println("\n\nAdjList On The Basis OF Workplace\n===============");
        graphObj.printForWorkplace();

        graphObj.makeFriend("C", "B");
        System.out.println("\nAdjList On The Basis Of Mutual Friends\n===============");
        graphObj.printForMutuals();
        System.out.println("\n\nAdjList On The Basis OF Workplace\n===============");
        graphObj.printForWorkplace();

        graphObj.makeFriend("A", "E");
        System.out.println("\nAdjList On The Basis Of Mutual Friends\n===============");
        graphObj.printForMutuals();
        System.out.println("\n\nAdjList On The Basis OF Workplace\n===============");
        graphObj.printForWorkplace();

        System.out.println("");
    }
    static String sp;

    public static String input() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user name: ");
        sp = in.nextLine();
        return sp;
    }

    public static String input2() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter workplace of " + sp + ": ");
        String s = in.nextLine();
        return s;
    }

}

class user {

    String label, workplace;
    boolean visit;
    Linkledlist<user> neighbor = new Linkledlist<user>();
    Linkledlist<user> neighbor2 = new Linkledlist<user>();

    user(String d, String w) {
        label = d;
        visit = false;
        workplace = w;
    }

}

class MyGraphh {

    user[] AdjList;

    MyGraphh() {
        AdjList = new user[10];
    }

    MyGraphh(int s) {
        AdjList = new user[s];
    }

    public void addUser(String l, String w) {
        int i = 0;
        user newUser = new user(l, w);
        while ((AdjList[i] != null) && !(AdjList[i].equals(l)) && i < AdjList.length) {
            i++;
        }
        if (i < AdjList.length && AdjList[i] == null) {
            AdjList[i] = newUser;
        }
    }

    public void makeFriend(String user1, String user2) {
        int i = 0;
        int j = 0;
        while (AdjList[i] != null && i < AdjList.length && !(AdjList[i].label.equals(user1))) {
            i++;
        }
        while (AdjList[j] != null && j < AdjList.length && !(AdjList[j].label.equals(user2))) {
            j++;
        }

        System.out.println("\n\nWhen " +AdjList[i].label + " and " +AdjList[j].label +" are Friends");
        if (i < AdjList.length && j < AdjList.length) {
            //FOR MUTUAL FRIENDS

            Linkledlist<user> local1 = AdjList[i].neighbor;
            if (local1.search(AdjList[j]) == false) {
                AdjList[i].neighbor.insert(AdjList[j]);
            }
            // undirected graph put reveres edge also
            Linkledlist<user> local2 = AdjList[j].neighbor;
            if (local2.search(AdjList[i]) == false)// if not already placed
            {
                AdjList[j].neighbor.insert(AdjList[i]);
            }

            //FOR WORKPLACE
            Linkledlist<user> temporary = AdjList[i].neighbor2;
            if (temporary.searchWork(AdjList[j]) == false && AdjList[i].workplace.equals(AdjList[j].workplace)) {
                AdjList[i].neighbor2.insert(AdjList[j]);
            }
            // undirected graph put reveres edge also
            Linkledlist<user> temporary2 = AdjList[j].neighbor2;
            if (temporary2.searchWork(AdjList[i]) == false && AdjList[j].workplace.equals(AdjList[i].workplace)) {
                AdjList[j].neighbor2.insert(AdjList[i]);
            }

        }
    }

    public void printForMutuals() {
        for (int i = 0; i < AdjList.length; i++) {
            System.out.print("\n" + AdjList[i].label + " --> ");
            int k = 0;
            while (k < AdjList[i].neighbor.Length()) {
                System.out.print(AdjList[i].neighbor.get(k).label);
                k++;
            }
        }
    }

    public void printForWorkplace() {
        for (int i = 0; i < AdjList.length; i++) {
            System.out.print("\n" + AdjList[i].label + " --> ");
            int k = 0;
            while (k < AdjList[i].neighbor2.Length()) {
                System.out.print(AdjList[i].neighbor2.get(k).label);
                k++;
            }
        }
    }
}

class MyNode<T> {

    T data;
    MyNode<T> next;

    MyNode(T d) {
        data = d;
    }
}

class Linkledlist<T> {

    MyNode<T> headNode;
    int count;

    public void insert(T d) {
        count++;
        MyNode<T> nd = new MyNode<T>(d); // create MyNode

        if (headNode == null) {
            headNode = nd;
        } else {
            MyNode<T> local = headNode;
            while (local.next != null) {
                local = local.next;
            }
            local.next = nd;
        }
    }

    public boolean search(T d) {
        MyNode<T> local = headNode;

        while (local != null && local.data != d) {
            local = local.next;
        }
        if (local != null && local.data == d) {
            return true;
        } else {
            return false;
        }
    }

    public boolean searchWork(T w) {
        MyNode<T> local = headNode;

        while (local != null && local.data != w) {
            local = local.next;
        }
        if (local != null && local.data == w) {
            return true;
        } else {
            return false;
        }
    }

    public int Length() {
        return count;
    }

    public T get(int i) {
        MyNode<T> local = headNode;
        int j = 0;
        while (j < i && local != null) {
            local = local.next;
            j++;
        }
        T val = local.data;
        return val;
    }
}
