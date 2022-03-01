#include <stdio.h>
                        /*
                        *Author: Alex Kaufmann
                        *Written: 2021-09-08
                        *The program takes an string input an reverses it using the stack.
                        *You run the code and then you input the wanted string in the terminal, for the iterative there is a max lenght of 49 characters.
                        *The program takes all standard keybord inputs. 
                        *The code is based on my own knowledge.
                        */

void iterative(){

    char input[50], currentChar;
    char *string = &input;              //setts creats an pointer pointing to the input array

    int i = 0;
    currentChar = getchar();
    while (currentChar != '\n')         //puts the chars into the arrays, aslong as the char is not '\n'
    {
        input[i] = currentChar;         //puts the current char into the array a place i
        i++;
        string += 1;
        currentChar = getchar();        //currentChar gets the next char of the input
    }
    string -= 1;
    for (int j = 0 ; j < i; j++)
    {
        putchar(*string);               //prints the char last in the array
        string -= 1;                    //goes to the preveus char in the array
    }
    putchar('\n');
}
void recursive(){
        char string = getchar();        //gives string the char value of the current char from the input
    if (string != '\n'){                
        recursive();                    //calls it self
        putchar(string);                //returns the char
    }
}


int main(void){   
    iterative();                        //calls the iterative funktion
    recursive();                        //calls the recursive funktion
}