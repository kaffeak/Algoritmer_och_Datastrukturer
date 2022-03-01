package com.company;

                            /*
                            * Author: Alex Kaufmann
                            *Written: 2021-09-08
                            *The program takes an string input an reverses it using the stack.
                            *You run the code and then you input the wanted string in the terminal. The program takes all standard keybord inputs.
                            *The code is based on my own knowledge and from the book: Algorithms 4th edition
                            */

import java.util.Iterator;
import java.util.Scanner;

public class Labb1_2<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {        //what the nodes consists of
        Item item;
        Node next;
    }
    public Labb1_2(){
        first = new Node();     //creats a pointer to the first node
        n = 0;                  //node counter
    }
    public Iterator<Item> iterator(){
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item>{
        private Node current = first;  //giving the current node the adress to the first node

        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;   //sets item to the item value of the node which the current pointer points to
            current = current.next;     //current changes so that it points to the next node in line
            return item;                //returns the item value
        }

    }

    public void push(Item item){        //pushes a new node to the top of the stack
        Node newNode = new Node();      //creats a new node
        newNode.item = item;            //sets the nodes item value to the inputted value
        newNode.next = first;           //gives the node "next" value so that it points to the former first node
        first = newNode;                //the adress which first holds changes so that it points to the new node
        n++;                            //node counter goes upp by one
    }
    public Item pop(){                  //removes the top node from the stack
        Item item = first.item;         //copys the item value from the first node to item
        first = first.next;             //sets the adress in first so that it points to the new first node
        n--;                            //reduces the node counter with one
        return item;                    //returns the item value
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        iterative(scanner.nextLine());
        System.out.println(recursive(scanner.nextLine()));
    }

    public static void iterative(String input){
        Labb1_2<Character> stack = new Labb1_2<Character>();          //creats a new stack
        int length = input.length();
        while (true){
            if(input.length() > 0){                             //if the lengt of input is greater than 0 then its true
                stack.push(input.charAt(0));                    //pushes the first char in the string to the stack
                input = input.substring(1);                     //overides the string input with a substring starting from index 1 (thus loosing the first char)
            }
            else break;                                         //when lenght is not greater than 0 the loop stops
        }
        for (int i = 0; i<length; i++){
            System.out.print(stack.pop());                      //removes the node on top on the stack and prints it out
        }
    }

    public static String recursive(String input){
        if(input.isEmpty()) {                                   //checks if the input string contains any chars
            return input;                                       //returns the string input
        }
        return recursive(input.substring(1)) + input.charAt(0); //the funktion calls itself with the same string but with a substring from index 1 and then adds the char att index 0 to the end
    }
}
