package com.company;
                                /*
                                 * Author: Alex Kaufmann
                                 *Written: 2021-09-10
                                 *The program takes inputs and stores them in a circular linked list amd allows the user to remove elements at a choosen indec in the que
                                 *You run the code and then you input wanted strings in the terminal. The program takes all standard keybord inputs.
                                 * When the delete funktion is called only Integers will get accepted and any none Integers will result in am outprint saying that it is not an Integer
                                 *The code is based on my own knowledge and from the book: Algorithms 4th edition
                                 */
import java.util.Iterator;
import java.util.Scanner;

public class Labb1_5<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {        //what the nodes consists of
        Item item;              //contains item value
        Node next;              //contains the adress to the next node
    }
    public Labb1_5(){
        first = new Node();     //gives first the adress pointing on the first node
        n = 0;                  //node counter
    }
    public Iterator<Item> iterator(){
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item>{
        private Node current = first;           //makes current node point to the first node so that when going thorgh the nodes u allwaus start att the first node
        boolean isFirst = true;

        public boolean hasNext(){       //returns true if there is a next uniqe node
            if (isFirst) {
                return current != null;
            }
            else return current!= first;
        }
        public Item next(){
            if(isFirst) isFirst = false;
            Item item = current.item;   //sets item to the item value of the node which the current pointer points to
            current = current.next;     //current changes so that it points to the next node in line
            return item;                //returns the item value
        }

    }
    public int isInt(){                         //checks if the input is infact an int otherwhise prints: Not an Int
        int number;
        String input;
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            try {
                number = Integer.parseInt(input);   //checks if the inputed data is an int and if it is it will return the Int
                return number;
            }
            catch (NumberFormatException ex) {
                System.out.println("Not an Int");
            }
        }
    }
    public static void main(String[] args) {
        Labb1_5<String> list = new Labb1_5<String>();
        Scanner in = new Scanner(System.in);
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.delete(list.isInt());
        list.add(in.nextLine());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
    }

    public void add(Item item){
        if(n == 0){
            first.item = item;              //Skapar första noden
            first.next = first;
        }
        else{
            Node newNode = new Node();
            newNode.item = item;            //sätter den nya nodens item
            newNode.next = first;           //sätter den nya nodens "nästa" address till den första noden
            first = newNode;                //makes the the adress in first point to the new node
        }
        n++;
        print();
    }
    public void delete(int e){
        if (e == 0) {                               //checks if the input of the index thats wants to be deleted is 0, the que is indext from one
            System.out.println("Index från 1");
        }
        else {
            if (e <= n) {                           //checks if there is nodes on that index
                if (n == 1) {
                    n = 0;                          //gör så att sista noden blir overwrited när nästa add kallas
                }
                else if (n == 0) {
                    System.out.println("Finns i sjön (Finns ingen nod)");
                }
                else {
                    if (e == 1) {
                        first = first.next;         //makes first point to the secoond node insted of the first node
                    }
                    else {
                        Node current = first;
                        for (int i = 0; i < e - 2; i++) {       //goes throgh the node list and stops one node infront of the node that will be deleted

                            current = current.next;
                        }
                        current.next = current.next.next;       //setts the current nodes "next" adrees to point to the node after the one that will be deleted
                    }
                    n--;
                    print();
                }
            } else {
                System.out.println("Finns inte så många element i kön");
            }
        }
    }
    public void print(){                                    //bygger ihop stringen i det formatet som jag vill printa det i
        StringBuilder strBuilder = new StringBuilder();
        int i = 1;
        for (Item item: this){
            if(i==n){
                strBuilder.append("["+item+"]");
                break;
            }
            strBuilder.append("["+item+"],");
            i++;
        }
        System.out.println(strBuilder);
    }
}
