/*
    Author: Alex Kaufmann
    Written: 26/9-21
    The code generates two random arrays with a lenght that you as a user decides. It then sorts the arrays using quicksort and quicksort with Median-of-three partitioning.
    It also compares the execution times of these algorithms using System.nanoTime();
    The program takes all standard inputs but will return a text saying it's not an int if you do not
    input an int.
    The code was written by me using my prior programming knowledge and from the book: Algorithms 4th edition.
 */
import java.util.Scanner;
import java.util.Random;

public class del6 {
    public static int ARRAY_LENGHT;
    public static int[] testQuickArray, testQuickArrayThreeWay;

    public static int inputInt(){                           //checks if the input is infact an int otherwhise prints: Not an Int
        int number;
        String input;
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            try {
                number = Integer.parseInt(input);       //checks if the inputed data is an int and if it is it will return the Int
                return number;
            }
            catch (NumberFormatException ex) {
                System.out.println("Not an Int");
            }
        }
    }
    private static void quickSort(int[] a, int lo, int hi) { //Quicksort
        if (hi <= lo) return; //if the partition gets to small it will stop the recursion
        int j = partition(a, lo, hi); // Partition.
        quickSort(a, lo, j-1); // Sort left part a[lo .. j-1].
        quickSort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }
    private static int partition(int[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        int v = a[lo]; // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break; //checks if the array element is smaller than the lowest element and while thats true ++i when i reaches the highest index then break
            while (less(v, a[--j])) if (j == lo) break; //checks if the lowest element is smaller than the array element and while thats true --j when j reaches the lowest index then break
            if (i >= j) break; //if i is bigger or the same as j then break otherwise swap index i and j in the array
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }
    public static void quicksSort(int[] a){
        quickSort(a,0,a.length-1);
    }
    private static void quickSortThreeWay(int[] a, int lo, int hi){//Quicksort
        if (hi <= lo) return;   //if the partition gets to small it will stop the recursion
        exch(a, median(a,lo,hi,lo+((hi-lo)/2)),lo); //takes three values: the lowest,the highest and the one in the middle. Then it checks the three values and returns the median value whitch then is swaped with the lowest value
        int j = partitionThreeWay(a, lo, hi); // Partition
        quickSortThreeWay(a, lo, j-1); // Sort left part a[lo .. j-1].
        quickSortThreeWay(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }
    private static int partitionThreeWay(int[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        int v = a[lo]; // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break; //checks if the array element is smaller than the lowest element and while thats true ++i when i reaches the highest index then break
            while (less(v, a[--j])) if (j == lo) break; //checks if the lowest element is smaller than the array element and while thats true --j when j reaches the lowest index then break
            if (i >= j) break;  //if i is bigger or the same as j then break otherwise swap index i and j in the array
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }
    private static int median(int[] array,int lo,int hi,int mid){ //checks the median of the 3 values
        if (array[lo]>array[hi] ^ array[lo] > array[mid]){          //if the value of array lo is greater than only one of array mid or array hi then return lo
            return lo;
        }
        else if(array[hi]>array[lo] ^ array[hi] > array[mid]){      //if the value of array hi is greater than only one of array mid or array lo then return hi
            return hi;
        }
        else return mid;
    }
    public static void quickSortThreeWay(int[] a){
        quickSortThreeWay(a,0,a.length-1);
    }
    private static boolean less(int v, int w) {
        if (v == w) return false;   // optimization when reference equals
        return v<w;
    }
    private static void exch(int[] a, int i, int j) { //swaps the two elements
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args){
        Random rng = new Random();
        System.out.println("How many testInsertionArray will the array hold?");
        ARRAY_LENGHT = inputInt();
        testQuickArray = new int[ARRAY_LENGHT];
        testQuickArrayThreeWay = new int[ARRAY_LENGHT];
        long execeuteStart, executeEnd;
        for (int i = 0; i < ARRAY_LENGHT; i++) { //generates two identical, random arrays
            int tempInput = rng.nextInt();
            testQuickArrayThreeWay[i] = tempInput;
            testQuickArray[i] = tempInput;
        }
        System.out.println();
        execeuteStart = System.nanoTime();  //takes a starting time
        quicksSort(testQuickArrayThreeWay);
        executeEnd = System.nanoTime();     //takes an ending time
        System.out.println("The execution time for the quick sort algorithm with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
        execeuteStart = System.nanoTime();
        quickSortThreeWay(testQuickArray);
        executeEnd = System.nanoTime();
        System.out.println("The execution time for the quick sort three way algorithm with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
    }
}
