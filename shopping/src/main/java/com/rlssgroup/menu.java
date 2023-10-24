package com.rlssgroup;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {
    public int usrChoice;
    public String userInput;
    public boolean error, cont;

    public void mainMenu(){
        System.out.println("Select your option by using the number!");
        System.out.println("1 - Add an item to the list");
        System.out.println("2 - Mark and item on the list complete");
        System.out.println("3 - view the list");
    }

    public void conMenu(){
        System.out.println("Do you wish to continue? Select your mumber!");
        System.out.println("1 - Continue with the program");
        System.out.println("2 - To end the program. Results are saved to the DB for future shopping!");
    }

    public void disMenu(){
        System.out.println("What do you want to view?");
        System.out.println("1 - Display all items on the list");
        System.out.println("2 - Display an item based off the number");
        System.out.println("3 - Display not completed items");
        System.out.println("4 - Display completed items");
    }

    public int choice(Scanner scnr, int l, int h){
        error = true;
        usrChoice = 0 ;

        while (error){
            try {
                usrChoice = scnr.nextInt();
                if ( usrChoice >= l && usrChoice <= h ) {
                    error = false;
                }
                else {
                    System.out.println("Invalid input detected. Enter a number on the menu");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input detected. Enter a valid number");
                userInput = scnr.nextLine(); // used for scanner discard
            } 
        }
        return usrChoice;
    }
}