/*
                            Author: Alex Kaufmann
                            Written: 29/9-22
                            Test program from Prinston.
                            The code was written by me using my prior programming knowledge and Algorithms, 4th ediditon Sedgewick and Wayne.
                         */
import java.util.Scanner;

public class FrequencyCounter
{
    public static void main(String[] args) {
        Long startTime, endTime;
        Scanner in = new Scanner(System.in);
        int minlen = 1; // key-length cutoff
        del3<String, Integer> st = new del3<String, Integer>(); //change del2 to del3 to change witch program you are testing
        for (int i = 0; i<1000;i++)
        { // Build symbol table and count frequencies.
            String word = in.next();
            if (word.length() < minlen) continue; // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        startTime = System.nanoTime();
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        endTime = System.nanoTime();
        System.out.println(max + " " + st.get(max));
        System.out.println("Running time: " + (endTime -startTime)/1000000 + " ms");
    }
}