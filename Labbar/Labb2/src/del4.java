/*
    Author: Alex Kaufmann
    Written: 24/9-21
    The code generates 3 identical arrays with a lenght that you as a user decides. It then sorts the three arrays using three diffrent sorting algorithms,
    insertionsort, quicksort and mergesort. It also compares the execution times of these algorithms using System.nanoTime();
    The program takes all standard inputs but will return a text saying it's not an int if you do not
    input an int.
    The code was written by me using my prior programming knowledge and from the book: Algorithms 4th edition.
 */
import java.util.Scanner;
import java.util.Random;

public class del4 {
    public static int ARRAY_LENGHT;
    public static int[] testInsertionArray, testMergArray, testQuickArray, aux;

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
    public static void insertionSort(int array[]){                  //Insertionsort
        for (int i = 0;i<ARRAY_LENGHT-1;i++){
            if (array[i]>array[i+1]){                               //checks if the next element in the array is smaller
                swap(array, i);                                     //swaps the two testInsertionArray
                for (int k = 0; k<ARRAY_LENGHT;k++){
                    //System.out.print(testInsertionArray[k] + ", ");
                }
                //System.out.println();
                for (int j = i; j > 0; j--){                        //loops back down to index 0
                    if (array[j]<array[j-1]){                       //checks if the prior element is bigger
                        swap(array,j-1);                         //swaps the two testInsertionArray
                        for (int k = 0; k<ARRAY_LENGHT;k++){
                            //System.out.print(testInsertionArray[k] + ", ");
                        }
                        //System.out.println();
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
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        mergeSort(a, lo, mid); // Sort left half.
        mergeSort(a, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results
    }
    public static void mergeSort(int[] a){
        aux = new int[a.length]; // Allocate space just once.
        mergeSort(a, 0, a.length - 1);
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
            while (less(a[++i], v)) if (i == hi) break; //checks if the array element is smaller than the lowest element and while thats true ++i when i reaches the highest index then break (scans left)
            while (less(v, a[--j])) if (j == lo) break; //checks if the lowest element is smaller than the array element and while thats true --j when j reaches the lowest index then break  (scans right)
            if (i >= j) break; //if i is bigger or the same as j then break otherwise swap index i and j in the array
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }
    public static void quicksSort(int[] a){
        quickSort(a,0,a.length-1);
    }
    private static boolean less(int v, int w) {
        if (v == w) return false;   // optimization when reference equals
        return v<w;
    }
    private static void exch(int[] a, int i, int j) {   //swaps the two elements
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args){
        Random rng = new Random();
        System.out.println("How many testInsertionArray will the array hold?");
        ARRAY_LENGHT = inputInt();
        testInsertionArray = new int[ARRAY_LENGHT];
        testMergArray = new int[ARRAY_LENGHT];
        testQuickArray = new int[ARRAY_LENGHT];
        long execeuteStart, executeEnd;
            for (int i = 0; i < ARRAY_LENGHT; i++) { //creates 3 identical arrays using the random funtion
                int tempValue = rng.nextInt();
                testInsertionArray[i] = tempValue;
                testQuickArray[i] = tempValue;
                testMergArray[i] = tempValue;
            }
            System.out.println();
            execeuteStart = System.nanoTime();   //takes a starting time
            quicksSort(testQuickArray);
            executeEnd = System.nanoTime();      //takes an ending time
            System.out.println("The execution time for the quick sort algorithm with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
            execeuteStart = System.nanoTime();
            insertionSort(testInsertionArray);
            executeEnd = System.nanoTime();
            System.out.println("The execution time for the insertion sort algorithm with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
            execeuteStart = System.nanoTime();
            mergeSort(testMergArray);
            executeEnd = System.nanoTime();
            System.out.println("The execution time for the merge sort algorithm with " + ARRAY_LENGHT + " elements is: " + ((executeEnd - execeuteStart) / 1000000) + " ms");
    }
}
