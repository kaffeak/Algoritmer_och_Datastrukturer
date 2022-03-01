/*
                            Author: Alex Kaufmann
                            Written: 29/9-21
                            The code takes an string input and replaces any nonalphabetical or nextline with space.
                            The code takes all standard input.
                            The code was written by me using my prior programming knowledge.
                         */
import java.util.Scanner;

public class del1 {
    public static String checkString(String string){ //Replaces all non alfabetical, blank space or newline with blankspace and returns a string
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0;i<string.length();i++){
            c = string.charAt(i);
            if (checkAlpha(c)){ //Checks if the character is a-z, A-Z or \n and adds them to the string, else it adds ' ' to the string
                sb.append(c);
            }
            else sb.append(' ');
        }
        return sb.toString();
    }
    private static boolean checkAlpha(char c){ //checks if the char is in the aplhabet or newline
        if (c>='A' && c<='Z' ||
            c>='a' && c<='z' ||
            c=='Ö' ||
            c=='Ä' ||
            c=='Å' ||
            c=='ö' ||
            c=='ä' ||
            c=='å' ||
            c=='\n')
            return true;
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println(checkString(in.nextLine()));
        }
    }
}
