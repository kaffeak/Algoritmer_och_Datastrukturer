package com.company;
/*
 * Author: Alex Kaufmann
 *Written: 2021-09-14
 *The program takes int inputs and compairs if there is a balenced sett between '(',')','[',']','{','}' and tells you otherwise
 *You run the code and then you input wanted strings in the terminal. The program takes all standard keybord inputs.
 *Only the chars '(',')','[',']','{','}' will get accepted and any other characters will result in error
 *The code is based on my own knowledge and from the book: Algorithms 4th edition
 */
import java.util.Iterator;
import java.util.Scanner;

public class Main<Item> implements Iterable<Item> {
    private Node first;
    private int n;
    private boolean check = true;

    private class Node {        //what the nodes consists of
        Item item;              //contains item value
        Node next;              //contains the adress to the next node
    }
    public Main(){
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
        Main<String> list = new Main<String>();
        Scanner in = new Scanner(System.in);
        if(list.checkBalance(in.nextLine(),list)){
            System.out.println("The string is balanced");
        }
        else System.out.println("The string is not balanced");
    }
    public boolean checkBalance(String input,Main list){    //checks if the string is balanced, this is a recursive funktion using an FILO - que
        if(input.isEmpty()){                                //checks if there is chars left in the string
            if(n==0) return true;                           //if there are no more things on the stack it returns true otherwise returns false
            else return false;
        }
        else {
            switch (input.charAt(0)) {                      //checks the first char in the string
                case '(':
                    list.push('(');                            //pushes the opening bracket to the stack
                check = checkBalance(input.substring(1),list);      //recalls itself without the first character in the string
                    if (check && !input.substring(1).isEmpty()){
                        check = checkBalance(input.substring(1),list);
                    }
                break;
                case '{':
                    list.push('{');
                    check = checkBalance(input.substring(1),list);  //-----------------||------------------------
                break;
                case '[':
                    list.push('[');
                    check = checkBalance(input.substring(1),list);  //-----------------||------------------------

                break;
                case ')':
                    if(n>0){                                        //checks if there is something on the stack else it returns false
                        if(list.pop() == '(') return true;          //checks if the top char of the stack is the same as the required char to balance the brackets else it returns false
                        else return false;
                    }
                    else return false;
                case '}':
                    if(n>0){
                        if(list.pop() == '{'){                      //-----------------||------------------------
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                case ']':
                    if(n>0){
                        if(list.pop() == '['){                      //-----------------||------------------------
                            return true;
                        }
                        else return false;
                    }
                    else return false;
            }
            while (true) {
                if (input.substring(1).isEmpty() && n > 0) {
                    check =  false;  // checks if there is somting left on the stack when the string is empty and if true sets check to false
                    break;
                }
                else if (!input.substring(1).isEmpty()) {
                    check = checkBalance(input.substring(1), list);
                    break;
                }
            }
            return check;
        }
    }
    public void push(Item item){
        if(n == 0){
            first.item = item;              //Skapar första noden
            first.next = first;
        }
        else{
            Node newNode = new Node();
            newNode.item = item;            //sätter den nya nodens item
            newNode.next = first;           //sätter den nya nodens "nästa" address till den första noden
            first = newNode;
        }
        n++;
    }
    public char pop(){
        char thisChar = 0;
        if (n == 1){                        //kollar om det är sista noden som tas bort
            thisChar = (Character) first.item;
            n=0;                            //sätter nod räknaren till 0 så att sista noden skrivs över när en ny nod läggs till
        }
        else if (n==0){
            System.out.println("Finns i sjön (Finns inga noder)");
        }
        else {
            thisChar = (Character) first.item;
            first = first.next;             //sätter nod 2 till first node
            n--;
        }
        return thisChar;
    }
}
