package com.company;
                                    /*
                                     * Author: Alex Kaufmann
                                     *Written: 2021-09-10
                                     *The program takes inputs and stores them in a circular linked list amd allows the user to add and remove elements to the front and the back end of the que
                                     *You run the code and then you input wanted strings in the terminal. The program takes all standard keybord inputs.
                                     *The code is based on my own knowledge and from the book: Algorithms 4th edition
                                     */
import java.util.Iterator;
import java.util.Scanner;

public class Labb1_4<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {        //what the nodes consists of
        Item item;              //contains item value
        Node next;              //contains the adress to the next node
        Node prev;              //contains the adress to the previus node
    }
    public Labb1_4(){
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
    public static void main(String[] args) {
        Labb1_4<String> list = new Labb1_4<String>();
        Scanner in = new Scanner(System.in);
        list.add(in.nextLine(), true);
        list.add(in.nextLine(), false);
        list.add(in.nextLine(), false);
        list.add(in.nextLine(), true);
        list.delete(false);
        list.delete(true);
        list.delete(false);
        list.delete(true);
        list.delete(true);
        list.delete(false);
        list.add(in.nextLine(),true);
        list.add(in.nextLine(),true);
        list.add(in.nextLine(),true);
        list.add(in.nextLine(),false);
        list.forEach(x -> System.out.println(x));
        list.delete(true);
        list.delete(false);
        list.delete(false);
        list.delete(true);
        list.delete(true);
    }

    public void add(Item item,boolean p){
        if(n == 0){
            first.item = item;              //Skapar första noden
            first.next = first;
            first.prev = first;
        }
        else {
            Node newNode = new Node();
            newNode.item = item;            //sätter den nya nodens item
            newNode.next = first;           //sätter den nya nodens "nästa" address till den första noden
            newNode.prev = first.prev;      //sätter den nya nodens "förra" adress till den första nodens gamla "förra" adress
            newNode.prev.next = newNode;    //sätter den "förra" nodens "nästa" adress till den nya nodens adress
            first.prev = newNode;           //sätter den första nodens "förra" adress till den nya nodens adress
            if (!p){                        //if p is false then it sets the new node to first node
                first = newNode;            //sätter adressen för first till den nya noden
            }
        }
        n++;
        print();
    }
    public void delete(boolean p){
        if (n == 1){
            n=0;                        //gör så att sista noden blir overwrited när nästa add kallas
        }
        else if (n==0){
            System.out.println("Finns i sjön (Finns inga noder)");
        }
        else {
            if (p) {                            //om p är true så tas första noden bort och andra ombjektet i kön sätts till first
                first.next.prev = first.prev;   //sätter nod 2s "förra" adressen till den gamla first nodens "förra" adress
                first.prev.next = first.next;   //sätter sista nodens "nästa" adress till nod 2s adress(den nya first noden)
                first = first.next;             //sätter nod 2 till first node
            }
            else {
                first.prev.prev.next = first;   //sätter nya sista nodens "next" till first nodens adress
                first.prev = first.prev.prev;   //sätter första nodens "prev" till sista nodens "prev"
            }
            n--;
            print();
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
