/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;

/**
 *
 * @author jonch
 */
public class Helper {
    public static int digit(int range1, int range2, Scanner scanner) {

        int choice = 0;
        int notNum = 1;

        //validation of isDigit and not out of Range
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("\n\nPlease enter a correct value!!!");
                System.out.print("Re-Enter your choice (" + range1 + " until " + range2 + ") > ");
                String choice1 = scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < range1 || choice > range2) {
                System.out.println("\n\nInvalid input. ");
                System.out.print("Re-Enter your choice (" + range1 + " until " + range2 + ") >");
                notNum = 1;

            } else {
                notNum = 0;
            }

        } while (notNum == 1);

        return choice;
    }

     public void clearConsole() {
        for (int i = 0; i < 15; i++) {
            System.out.println("");

        }

    }
}
