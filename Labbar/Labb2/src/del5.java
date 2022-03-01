/*
    Author: Alex Kaufmann
    Written: 25/9-21
    The code generates a random array with a lenght that you as a user decides. It then sorts the array using mergesort with cutoff to insertionsort.
    It also gives the execution time the algorithm using System.nanoTime();
    The program takes all standard inputs but will return a text saying it's not an int if you do not
    input an int.
    The code was written by me using my prior programming knowledge and from the book: Algorithms 4th edition.
 */
import java.util.Scanner;
import java.util.Random;

public class del5 {
    public static int ARRAY_LENGHT, CUTOFF=5;
    public static int[] testMergArray, aux;

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
    public static void insertionSort(int array[],int lo, int hi){     //Insertionsort
        for (int i = lo;i<hi;i++){
            if (array[i]>array[i+1]){                               //checks if the next element in the array is smaller
                swap(array, i);
                for (int j = i; j > lo; j--){                        //loops back down to index lo
                    if (array[j]<array[j-1]){                       //checks if the prior element is bigger
                        swap(array,j-1);
                    }
                }
            }
        }
    }
    public static void swap(int array[],int i){                     //the swap funktion swaps two testInsertionArray in the array
        array[i] = array[i] + array[i+1];
        array[i+1] = array[i] - array[i+1];
        array[i] = array[i] - array[i+1];
    }
    public static void merge(int[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
            if (i > mid) a[k] = aux[j++];
            else if (j > hi ) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }
    private static void mergeSort(int[] a, int lo, int hi) { // Sort a[lo..hi].
        if (hi <= lo+CUTOFF) {      //checks if hi is smaller or equal to lo+cutoff and if its true it uses insertionsort for the smaller subarrays
            insertionSort(a,lo,hi);
            return;
        }else {
            int mid = lo + (hi - lo) / 2;
            mergeSort(a, lo, mid); // Sort left half.
            mergeSort(a, mid + 1, hi); // Sort right half.
            merge(a, lo, mid, hi); // Merge results (code on page 271).
        }
    }
    public static void mergeSort(int[] a){
        aux = new int[a.length]; // Allocate space just once.
        mergeSort(a, 0, a.length - 1);
    }
    private static boolean less(int v, int w) { //check if v is smaller than w
        if (v == w) return false;   // optimization when reference equals
        return v<w;
    }
    public static void main(String[] args) {
        Random rng = new Random();
        System.out.println("How many elements will the array hold?");
        ARRAY_LENGHT = inputInt();
            testMergArray = new int[ARRAY_LENGHT];
            for (int i = 0; i < ARRAY_LENGHT; i++) {   //creates an random array using the Random.nextInt() funktion
                testMergArray[i] = rng.nextInt();
            }
            System.out.println();
            long execeuteStart, executeEnd;
            execeuteStart = System.nanoTime();  //takes a starting time
            mergeSort(testMergArray);
            executeEnd = System.nanoTime();     //takes an ending time
            System.out.println("The execution time for the merge sort algorithm with cutoff to insertion at " + CUTOFF + " with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
    }
}
