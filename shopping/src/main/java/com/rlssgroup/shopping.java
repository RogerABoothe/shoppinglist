package com.rlssgroup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Shopping {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        System.out.println("Wecome to the RLSS Shopping List App!");
        System.out.println("This is sprint one, additional features will continue to be added!");
        System.out.println("Currently we only function via terminal!");
        Menu menu = new Menu();
        Scanner scnr = new Scanner(System.in);
        int cont = 1;

        while (cont == 1){
            menu.mainMenu();
            int usrChoice = menu.choice(scnr, 1, 3);
            try {
                String userInput = scnr.nextLine();
                switch(usrChoice) {
                    case 1:
                        System.out.println("Enter the description of the item you want to add");
                        userInput = scnr.nextLine();
                        ItemAdd.addItem("Roger", userInput);
                        break;
                    case 2:
                        System.out.println("Enter the item number you wish to mark as completed");
                        userInput = scnr.nextLine();
                        ItemComp.compItem("Roger", userInput);
                        break;
                    case 3:
                        ItemDisplay.displayMenu(scnr);
                        break;
                }
            }
            catch(Exception e){
                Logging.logEvent(e.getMessage());
                System.out.println(e);
            }
            menu.conMenu();
            cont = menu.choice(scnr, 1, 2);
        }
        System.out.println("Thank you for using the RLSS Shopping List App!");
        DBConnect.connectionClose();
        scnr.close();
    }
}