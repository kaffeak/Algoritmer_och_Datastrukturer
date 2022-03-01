#include<stdio.h>
                        /*
                            Author: Alex Kaufmann
                            Written: 21/9-21
                            The code takes inputs and puts them in an array, checks how many inversions there are and then it sorts it using insertion sorting.
                            The program takes all standard inputs but will return a text saying it's not an int if you do not
                            input an int.
                            The code was written by me using my prior programming knowledge.
                        */

void arrayCheck(int array[],int length){ //checks if the value is negative and puts it infront of the non negative numbers
    int place = 0;
    for (int i = 0; i < length; i++)
    {
        if (array[i]<0)                  //checks if the value is negative 
        {
            for (int j = i; j > place; j--){        //moves the negative number down so that it is placed at the highest arrayindex with only negatives being on the lower indexes
                array[j] = array[j] + array[j-1];
                array[j-1] = array[j] - array[j-1];
                array[j] = array[j] - array[j-1];
            }
            place++;                                //when a negative is moved to the front of the que the place counter adds 1 so that the next negative gets placed at 1 higher index in the array
        }
    }    
}
    
int main(void){
    printf("Enter the size of the array\n");
    int ARRAY_LENGHT;
    scanf("%d", &ARRAY_LENGHT);
    int elements[ARRAY_LENGHT];
    for (int i = 0; i < ARRAY_LENGHT; i++)
    {
        scanf("%d", &elements[i]);
    }
    for (int i = 0; i < ARRAY_LENGHT; i++)
    {
        printf("%d ", elements[i]);
    }
    arrayCheck(elements,ARRAY_LENGHT);
    for (int i = 0; i < ARRAY_LENGHT; i++)
    {
        printf("%d ", elements[i]);
    }
}
