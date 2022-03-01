package com.company;
                        /*
                        Author: Alex Kaufmann
                        The code was generated
                         */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static Stack<Character> stack = new Stack<Character>();
    public static Scanner sc = new Scanner(System.in);
    public static StringBuilder sB = new StringBuilder();


    public static void main(String[] args) {
        recursive(sc.nextLine());
        sB.delete(sB.length()-1,sB.length());
        System.out.print(sB+"\n");
        sB.delete(0,sB.length());
        iterative(sc.nextLine());
        sB.delete(sB.length()-1,sB.length());
        System.out.print(sB+"\n");
    }
    public static void recursive(String input){
        if (input.length() > 0){
            stack.push(input.charAt(0));
            recursive(input.substring(1));
            sB.append("["+stack.pop()+"],");
        }
    }
    public static void iterative(String input){
        int length = input.length();
        while (true){
            if(input.length() > 0){
                stack.push(input.charAt(0));
                input = input.substring(1);
            }
            else break;
        }
        for (int i = 0; i<length; i++){
            sB.append("["+stack.pop()+"],");
        }
    }
}
