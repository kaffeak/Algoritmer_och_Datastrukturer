package com.company;
                        /*
                         * Author: Alex Kaufmann
                         *Written: 2021-09-09
                         *The program takes inputs and stores them in a FIFO - que based dubble linked list
                         *You run the code and then you input wanted strings in the terminal. The program takes all standard keybord inputs.
                         *The code is based on my own knowledge and from the book: Algorithms 4th edition
                         */

import java.util.Iterator;
import java.util.Scanner;

public class Labb1_3<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {        //what the nodes consists of
        Item item;              //contains item value
        Node next;              //contains the adress to the next node
        Node prev;              //contains the adress to the previus node
    }
    public Labb1_3(){
        first = new Node();     //creats a pointer to the first node
        n = 0;                  //node counter
    }
    public Iterator<Item> iterator(){
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item>{
        private Node current = first;    //giving the current node the adress to the first node
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
        Scanner in = new Scanner(System.in);
        Labb1_3<String> list = new Labb1_3<String>();
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.add(in.nextLine());
        list.forEach(x -> System.out.println(x));
        list.delete();
        list.delete();
        list.delete();
        list.delete();
        list.delete();
    }

    public void add(Item item){
        if(n == 0){
            first.item = item;              //Skapar första noden
            first.next = first;
            first.prev = first;
        }
        else{
            Node newNode = new Node();
            newNode.item = item;            //sätter den nya nodens item
            newNode.next = first;           //sätter den nya nodens "nästa" address till den första noden
            newNode.prev = first.prev;      //sätter den nya nodens "förra" adress till den första nodens gamla "förra" adress
            newNode.prev.next = newNode;    //sätter den "förra" nodens "nästa" adress till den nya nodens adress
            first.prev = newNode;           //sätter den första nodens "förra" adress till den nya nodens adress
        }
        n++;
        print();
    }
    public void delete(){
        if (n == 1){                        //kollar om det är sista noden som tas bort
            n=0;                            //sätter nod räknaren till 0 så att sista noden skrivs över när en ny nod läggs till
        }
        else if (n==0){
            System.out.println("Finns i sjön (Finns inga noder)");
        }
        else {
            first.next.prev = first.prev;   //sätter nod 2 "förra" adressen till den gamla first nodens "förra" adress
            first.prev.next = first.next;   //sätter sista nodens "nästa" adress till nod 2s adress(den nya first noden)
            first = first.next;             //sätter nod 2 till first node
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
