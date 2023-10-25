package com.rlssgroup;

import java.io.IOException;
import java.util.Scanner;

public class Shopping {
    
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Wecome to the RLSS Shopping List App!");
            System.out.println("This is sprint one, additional features will continue to be added!");
            System.out.println("Currently we only function via terminal!");
            Menu menu = new Menu();
            Scanner scnr = new Scanner(System.in);
            int cont = 1;
            User user = new User();
            String userName = user.getUserName();
            boolean authStatus = user.getAuthStatus();

            if (authStatus){
                while (cont == 1){
                    menu.mainMenu();
                    int usrChoice = menu.choice(scnr, 1, 3);
                        String userInput = scnr.nextLine();
                        switch(usrChoice) {
                            case 1:
                                System.out.println("Enter the description of the item you want to add");
                                userInput = scnr.nextLine();
                                ItemAdd.addItem(userName, userInput);
                                break;
                            case 2:
                                System.out.println("Enter the item number you wish to mark as completed");
                                userInput = scnr.nextLine();
                                ItemComp.compItem(userName, userInput);
                                break;
                            case 3:
                                ItemDisplay.displayMenu(scnr);
                                break;
                        }
                    menu.conMenu();
                    cont = menu.choice(scnr, 1, 2);
                    }
                }
        else {
            System.out.println("You are not an authorized user!");
            Logging.logEvent("User attempted to authorize as " + userName + " details did not match in DB");
        }
        System.out.println("Thank you for using the RLSS Shopping List App!");
        DBConnect.connectionClose();
        scnr.close();
        }
        catch(Exception e){
            Logging.logException(e);
        }
    }
}