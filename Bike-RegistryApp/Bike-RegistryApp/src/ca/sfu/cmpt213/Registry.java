package ca.sfu.cmpt213;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Registry class has two member variables - registeredBikes and an arrayList named list.
 * registeredBike is used to give every bike a unique ID and also keeps into account the total number of bikes ever registered.
 * <p>
 * {@code printHeading} is a static function used to print headings with stars.
 * </p>
 *
 * <p>
 * {@code displayMainMenu} is a static method and is called using the name of the class in main program.
 * </p>
 *
 * <p>
 * {@code displayBikes } is a non-static method and is used to display the bikes in the ArrayList 'list' with indentation using string formatting
 * </p>
 *
 * <p>
 * {@code addNewBike} is a non-static method that takes the Scanner sc as an argument and constructs a new bike using the non default constructor
 * and it then adds the bike in the list.
 * </p>
 *
 * <p>
 * {@code displayAllBikesToString} is a non-static method used to print all the bikes in the arrayList, the override function toString present
 * in Bike class is used.
 * </p>
 *
 * <p>
 * {@code deleteBike} is a non-static method that deletes a bike from the arrayList.It first displays all the bikes
 * using {@code displayBikes }and then validates the bikeID provided by calling {@code validateInt}. If the user enters a string, we get a InputMismatchException
 * which is handled by {@code validateInt}. If the user enters an integer, it is accepted by {@code validateInt} and
 * then inside {@code deleteBike} , it is checked whether a bike exists with that bikeID.If there exists, the bike is deleted
 * else if it does not exist, the {@code deleteBike} is called again recursively.
 * Empty string input is also handled by this program.
 * </p>
 *
 * <p>
 * {@code alterBike} is a non-static method that first lists all the bike using {@code displayBikes} and then prompts the user
 * to choose a bike by entering the bikeID. I then validate my bikeID by calling {@code validateInt}.
 * If i get a valid integer, then I search for the bikeID in the arrayList. If i find the bikeID, i ask for the attribute to change
 * if i get a valid attribute, i call the setter of the respective attribute and the bike is altered.
 * * if i don't get the bikeID, I first print all the bikes using {@code displayAllBikes} and then again ask for the bikeID.
 * Empty string input is also handled by this program.
 * </p>
 *
 * <p>
 * {@code getNumberOfBikes} is a non-static method that displays the total number of bikes using the .size() function of arrayList
 * </p>
 */

public class Registry {
    private int registeredBikes = 0;
    private final List<Bike> list = new ArrayList<>();

    public static void printHeading(String heading) {
        for (int i = 0; i < heading.length() + 4; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("* " + heading + " *");
        for (int i = 0; i < heading.length() + 4; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void displayMainMenu() {
        System.out.println();
        printHeading("MAIN MENU");
        System.out.println("1 : List Bikes");
        System.out.println("2 : Add a new bike");
        System.out.println("3 : Remove a bike");
        System.out.println("4 : Alter a bike");
        System.out.println("5 : DEBUG: Dump objects (toString)");
        System.out.println("6 : TOTAL BIKES");
        System.out.println("7 : Exit");
    }

    public void displayAllBikes() {
        System.out.println();
        printHeading("List of Bikes");
        System.out.printf("%-5s %-18s %-12s %-18s %-8s %-10s%n",
                "ID", "OWNER", "TYPE", "SERIAL NUMBER", "BRAKE", "WHEEL SIZE");

        for (Bike b : list) {
            System.out.printf("%-5s %-18s %-12s %-18s %-8s %-10s%n",
                    b.getBikeID(),
                    b.getOwner(),
                    b.getType(),
                    b.getSerialNumber(),
                    b.getBrakeType(),
                    b.getWheelSize());
        }
    }

    public void addNewBike(Scanner sc) {
        System.out.println("Adding a new Bike");
        registeredBikes++;
        Bike newBike = new Bike(registeredBikes, sc);
        list.add(newBike);
        System.out.println("Bike added successfully!");
    }

    public void displayAllBikesToString() {
        System.out.println("All Bike objects");
        int n = 0;
        for (Bike b : list) {
            System.out.println(++n + ". " + b);
        }
    }

    public void deleteBike(Scanner sc) {
        if (list.isEmpty()) {
            System.out.println("No bikes registered");
            return;
        }
        displayAllBikes();
        System.out.println("Please enter the ID of the Bike you would like to delete or press 0 to cancel");
        int choice = validateInt(sc);
        if (choice == 0) {
            System.out.println("Cancelled by user");
            return;
        }
        for (Bike b : list) {
            if (b.getBikeID() == choice) {
                list.remove(b);
                System.out.println("Bike removed successfully!");
                return;
            }
        }
        System.out.println("Bike not found. Please try again.");
        System.out.print("> ");
        deleteBike(sc);
    }

    public int validateInt(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.trim().isEmpty()) {
                    throw new Exception();
                } else {
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input expected an integer. Please try again.");
                System.out.print("> ");
            } catch (Exception e) {
                System.out.println("Empty string. Please enter again.");
                System.out.print("> ");
            }
        }
    }

    public void alterBike(Scanner sc) {
        if (list.isEmpty()) {
            System.out.println("No bikes registered");
            return;
        }
        displayAllBikes();
        System.out.println("Please choose a bikeID to change or press 0 to cancel");
        int choice = validateInt(sc);
        if (choice == 0) {
            System.out.println("Cancelled by user");
            return;
        }
        for (Bike bike : list) {
            if (bike.getBikeID() == choice) {
                System.out.println("Please enter a bike attribute");
                while (true) {
                    String attribute = sc.nextLine().toLowerCase().trim();
                    if (Bike.validate(attribute)) {
                        switch (attribute) {
                            case "owner":
                                System.out.println("Please enter new owner's name");
                                bike.setOwner(sc);
                                System.out.println("Saved!");
                                return;

                            case "type":
                                System.out.println("Please enter new type");
                                bike.setType(sc);
                                System.out.println("Saved!");
                                return;

                            case ("serial"):
                            case ("serialnumber"):
                            case ("serial number"):
                                System.out.println("Please enter new serial number");
                                bike.setSerialNumber(sc);
                                System.out.println("Saved!");
                                return;

                            case "brake":
                                System.out.println("Please enter new brake type");
                                bike.setBrakeType(sc);
                                System.out.println("Saved!");
                                return;

                            case "wheel size":
                            case "wheelsize":
                                System.out.println("Please enter new wheel size");
                                bike.setWheelSize(sc);
                                System.out.println("Saved!");
                                return;

                            default:
                                System.out.println("Invalid input. Please try again");
                                System.out.print("> ");

                        }
                    }
                }
            }
        }
        System.out.println("ID does not exist, try again.");
        alterBike(sc);
    }

    public void getNumberOfBikes() {
        System.out.println("The number of bikes are : " + list.size());
    }
}


