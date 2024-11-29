/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubjectManagementModule;
import util.Helper;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jonch
 */
public class Starter {
     public static void main(String[] args) throws IOException {
        Helper helper = new Helper();

        Scanner sc = new Scanner(System.in);
        while (true) {

            SubjectUII ui = new SubjectUII();
            int choice = ui.mainMenu();

            switch (choice) {

                case 1:
                    ui.tutorMain();
                    helper.clearConsole();
                    break;
                case 2:
                    System.out.println("Press enter to quit the system...");
                    sc.nextLine();
                    return;

            }

        }

    }

}

