                        /*
                            Author: Alex Kaufmann
                            Written: 21/9-21
                            The code takes inputs and puts them in an array, checks how many inversions there are and then it sorts it using insertion sorting.
                            The program takes all standard inputs but will return a text saying it's not an int if you do not
                            input an int.
                            The code was written by me using my prior programming knowledge.
                        */
import java.util.Scanner;

public class del2 {
    public static int elements[], ARRAY_LENGHT;

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
    public static int inversions(int array[]){              //Counts the amount of inversions there is and prints out the which elements should be switched
        int counter=0;
        int lastInverse = 0;
        for (int i = 0; i<ARRAY_LENGHT;i++){
            for (int j = i+1; j<ARRAY_LENGHT;j++) {
                if(array[i]>array[j]){                      //checks if the elements should be switched
                    counter++;                              //adds 1 to the counter
                    lastInverse=j;                          //remembers the last element it should switch with
                }
            }
            System.out.println("Inversion: [" + i + ", " + array[i] + "], [" + lastInverse + ", " + array[lastInverse] + "]");
        }
        return counter;
    }
    public static void insertionSort(int array[]){                  //Insertionsort
        for (int i = 0;i<ARRAY_LENGHT-1;i++){
            if (array[i]>array[i+1]){                               //checks if the next element in the array is smaller
                swap(array, i);                                     //swaps the two elements
                for (int k = 0; k<ARRAY_LENGHT;k++){
                    System.out.print(elements[k] + ", ");
                }
                System.out.println();
                for (int j = i; j > 0; j--){                        //loops back down to index 0
                    if (array[j]<array[j-1]){                       //checks if the prior element is bigger
                        swap(array,j-1);                         //swaps the two elements
                        for (int k = 0; k<ARRAY_LENGHT;k++){
                            System.out.print(elements[k] + ", ");
                        }
                        System.out.println();
                    }
                }
            }
        }
    }
    public static void swap(int array[],int i){                     //the swap funktion swaps two elements in the array
        int temp = array[i+1];
        array[i+1] = array[i];
        array[i] = temp;
    }
    public static void main(String[] args){
        System.out.println("How many elements will the array hold?");
        ARRAY_LENGHT = inputInt();
        elements = new int[ARRAY_LENGHT];
        System.out.println("Enter your inputs:");
        for (int i = 0; i<ARRAY_LENGHT;i++){
            elements[i] = inputInt();
        }
        System.out.println("Number of inversions: "+ inversions(elements));
        for (int i = 0; i<ARRAY_LENGHT;i++){
            System.out.print(elements[i] + ", ");
        }
        System.out.println();
        insertionSort(elements);
        for (int i = 0; i<ARRAY_LENGHT;i++){
            System.out.print(elements[i] + ", ");
        }
    }
}
