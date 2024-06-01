package ca.sfu.cmpt213;

import java.util.Scanner;

/**
 * @author Kabir Singh Sidhu
 * @version 1.0
 * <p>
 * {@code Main}
 * is used to run the program. i used switch cases to implement all the different choices.
 * if the user enters an empty value i.e just enter or space and then enter, i throw an exception
 * and i later catch it. this is done to make sure that each time a valid input is given,
 * and if the user enters something else, it is prompted to the user that there is an error.
 * </p>
 *
 * <p>
 * By taking the initial input in a string and checking if it is empty, I am able to detect if it is empty.
 * to use the switch case with an integer, i parse the string into an integer and run the switch case.
 * </p>
 *
 * <p>
 * if the user enters 7, the switch case and the whole program is terminated using System.exit()
 * and i also close the scanner before that.
 * </p>
 */

public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("WELCOME TO SFU BICYCLE REGISTRY");
        Scanner sc = new Scanner(System.in);
        Registry registry = new Registry();

        while (true) {
            Registry.displayMainMenu();
            System.out.print("> ");
            try {
                String input = sc.nextLine().trim();
                if (input.trim().isEmpty()) {
                    throw new Exception();
                }
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        registry.displayAllBikes();
                        break;
                    case 2:
                        registry.addNewBike(sc);
                        break;
                    case 3:
                        registry.deleteBike(sc);
                        break;
                    case 4:
                        registry.alterBike(sc);
                        break;
                    case 5:
                        registry.displayAllBikesToString();
                        break;
                    case 6:
                        registry.getNumberOfBikes();
                        break;
                    case 7:
                        System.out.println("Exiting program. Thank you.");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.print("Invalid choice .Please try again.");
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.print("Entered an empty string. Please try again. ");
            }
        }
    }
}