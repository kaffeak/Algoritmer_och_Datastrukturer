package com.company;
                                    /*
                                     * Author: Alex Kaufmann
                                     *Written: 2021-09-13
                                     *The program takes int inputs and stores them in a circular linked list witch automaticly sort the elements in ascending order
                                     *You run the code and then you input wanted strings in the terminal. The program takes all standard keybord inputs.
                                     *Only Integers will get accepted and any none Integers will result in am outprint saying that it is not an Integer
                                     *The code is based on my own knowledge and from the book: Algorithms 4th edition
                                     */
import java.util.Iterator;
import java.util.Scanner;

public class Labb1_6<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {        //what the nodes consists of
        Item item;              //contains item value
        Node next;              //contains the adress to the next node
    }
    public Labb1_6(){
        first = new Node();     //gives first the adress pointing on the first node
        n = 0;                  //node counter
    }
    public Iterator<Item> iterator(){
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item>{
        private Node current = first;       //makes current node point to the first node so that when going thorgh the nodes u allwaus start att the first node
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
             number = Integer.parseInt(input);      //checks if the inputed data is an int and if it is it will return the Int
             return number;
            }
            catch (NumberFormatException ex) {
                System.out.println("Not an Int");
            }
        }
    }
    public static void main(String[] args) {
        Labb1_6<Integer> list = new Labb1_6<Integer>();
        Scanner in = new Scanner(System.in);
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.add(list.isInt());
        list.add(list.isInt());
        list.delete(list.isInt());
        list.add(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
        list.delete(list.isInt());
    }

    public void add(Item item){
        if(n == 0){
            first.item = item;              //Skapar första noden
            first.next = first;
        }
        else {
            Node currentNode = first;
            int e = 0;
            boolean isFirst = true, isLast = false;
            while (true) {
                int curentValue =Integer.valueOf((Integer) currentNode.item);
                int inputValue =Integer.valueOf((Integer) item);
                if (inputValue > curentValue){
                    isFirst = false;
                    if (currentNode.next == first){
                        isLast = true;
                        break;
                    }
                    else{
                        currentNode = currentNode.next;
                        e++;
                    }
                }
                else {
                    break;
                }
            }
            Node nodeChange = first;
            for (int i = 0; i <= e-2; i++){
                nodeChange = nodeChange.next;
            }
            Node newNode = new Node();
            if (isFirst){
                newNode.item = first.item;          //överför gamla first nodens "item" värde till den nya noden på position 2
                newNode.next = first.next;          //överför gamla first nodens "nästa" värde till den nya noden på position 2
                first.item = item;                  //skriver över first nodens värden med den nya nodens värden
                first.next = newNode;               //-----------------------||-------------------------//
            }
            else if(isLast){
                nodeChange = nodeChange.next;       //flyttar fram noden så att den nya noden hamnar sist
                newNode.item = item;                //sätter den nya nodens item
                newNode.next = first;               //sätter den nya nodens "nästa" adress till first noden
                nodeChange.next = newNode;          //sätter den förr sista nodens "nästa" adress till den nya noden
            }
            else {
                newNode.item = item;                 //sätter den nya nodens item
                newNode.next = nodeChange.next;      //sätter den nya nodens "nästa" address till noden vars plats togs
                nodeChange.next = newNode;           //sätter noden innan "nästa" adress till den nya noden
            }
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
                        Node temp = first;
                        for (int i = 0; i < e - 2; i++) {       //goes throgh the node list and stops one node infront of the node that will be deleted
                            temp = temp.next;
                        }
                        temp.next = temp.next.next;             //setts the current nodes "next" adrees to point to the node after the one that will be deleted
                    }
                    n--;
                    print();
                }
            } else {
                System.out.println("Finns inte så många element i kön");
            }
        }
    }
    public void print(){                                        //bygger ihop stringen i det formatet som jag vill printa det i
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
