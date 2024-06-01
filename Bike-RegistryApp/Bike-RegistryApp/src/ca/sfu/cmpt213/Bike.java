package ca.sfu.cmpt213;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code Bike} class represents a bicycle with various attributes such as
 * bike ID, owner, type, serial number, brake type, and wheel size.
 * It provides methods to set and get these attributes.
 * I also validate the inputs, for this I kept the exception handling in the setters.
 * <p>
 * {@code Bike} constructor constructs the bike using the setters and sends the scanner sc as an argument.
 * </p>
 *
 * <p>
 * {@code setOwner, setType}
 * the setters for owner , type first validate the given input. if the string entered is not empty
 * and only contains characters. if it is empty or contains even one non-alphabetic character, it will
 * return false and a prompt would be displayed to the user to enter again.
 * if the user enters an empty string , it is handled by the program and a prompt is given to the user to enter a valid input
 * </p>
 *
 * <p>
 * {@code setSerialNumber}
 * It checks whether any character other tah upper case letters or number are entered. if they are entered, the user is asked again
 * The code runs until the user enters a valid serial number. an ideal serial number contains numbers or alphabets or both.
 * </p>
 *
 * <p>
 * {@code setBrakeType}
 * we are only given 3 types of brake types which are rim , disc, drum. Hence, I first ask the user for a brake type
 * I then set that to lowerCase() and trim() it to clear all the whitespace if any. and if it matches to any of the 3
 * brake types provided , I accept it and set this.brakeType to the string.
 * <p>
 * {@code setWheelSize}
 * is a setter for the wheelsize. if the user enters a string , the exception is handles in the catch statement
 * that catches an input mismatch exception. if the user enters a negative int or equal to 0, i throw an exception
 * as wheelsize can never be less than or equal to 0. both the exceptions are handled and correct statements are
 * displayed for the user.
 * </p>
 *
 * <p>
 * the getters of all these attributes simply return the attribute.
 * </p>
 *
 * <p>
 * {@code toString()}
 * displays all the bikes attributes including the package name and the class name using getClass().getName().
 * </p>
 */

class Bike {
    private int bikeID;
    private String owner;
    private String type;
    private String serialNumber;
    private String brakeType;
    private double wheelSize;

    public Bike(int numberOfBikes, Scanner sc) {

        setBikeID(numberOfBikes);
        System.out.print("Enter owner name: ");
        setOwner(sc);

        System.out.print("Enter bike type: ");
        setType(sc);

        System.out.print("Enter serial number: ");
        setSerialNumber(sc);

        System.out.print("Enter brake type: ");
        setBrakeType(sc);

        System.out.print("Enter wheel size: ");
        setWheelSize(sc);
    }

    public int getBikeID() {
        return bikeID;
    }

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getBrakeType() {
        return brakeType;
    }

    public double getWheelSize() {
        return wheelSize;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public static boolean validate(String s) {
        if (s.trim().isEmpty()) {
            System.out.println("Invalid. Entered an empty string. Please try again.");
            System.out.print("> ");
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            int tempChar = s.charAt(i);
            if (!((tempChar >= 65 && tempChar <= 90) || (tempChar >= 97 && tempChar <= 122) || (tempChar == 32))) {
                System.out.println("Invalid input. Please try again.");
                System.out.print("> ");
                return false;
            }
        }
        return true;
    }

    public static boolean validateSerialNo(String s) {
        if (s.trim().isEmpty()) {
            System.out.println("Invalid. Entered an empty string. Please try again.");
            System.out.print("> ");
            return false;
        }
        boolean alphabet = false;
        boolean digit = false;

        for (int i = 0; i < s.length(); i++) {
            char tempChar = s.charAt(i);
            if ((tempChar >= 65 && tempChar <= 90)) {
                alphabet = true;
            } else if (tempChar >= 48 && tempChar <= 57) {
                digit = true;
            } else {
                System.out.println("Invalid input. Serial number must contain only alphanumeric characters.");
                System.out.print("> ");
                return false;
            }
        }

        if (!alphabet && !digit) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("> ");
            return false;
        }


        return true;
    }


    public void setOwner(Scanner sc) {
        String name = sc.nextLine().trim();
        if (validate(name)) {
            this.owner = name;
        } else {
            setOwner(sc);
        }
    }

    public void setType(Scanner sc) {
        String s = sc.nextLine().trim();
        if (validate(s)) {
            this.type = s;
        } else {
            setType(sc);
        }
    }

    public void setSerialNumber(Scanner sc) {
        String serial = sc.nextLine().trim().toUpperCase();
        if (validateSerialNo(serial)) {
            this.serialNumber = serial;
        } else {
            setSerialNumber(sc);
        }
    }

    public void setBrakeType(Scanner sc) {
        String brake = sc.nextLine().toLowerCase().trim();
        if (brake.equals("rim") || brake.equals("disc") || brake.equals("drum")) {
            this.brakeType = brake;
        } else {
            System.out.println("Invalid brake type please enter again");
            System.out.print("> ");
            setBrakeType(sc);
        }
    }

    public void setWheelSize(Scanner sc) {
        try {
            this.wheelSize = sc.nextDouble();
            sc.nextLine();

            if (this.wheelSize <= 0) {
                throw new Exception();
            }

        } catch (InputMismatchException e) {
            System.out.println("Cannot enter a non numeric wheel size. Please try again.");
            System.out.print("> ");
            sc.nextLine();
            setWheelSize(sc);
        } catch (Exception e) {
            System.out.println("Wheel size must be positive!. Please try again.");
            System.out.print("> ");
            setWheelSize(sc);
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "[ Bike ID: " + bikeID + ", Owner: " + owner + ", Type: " + type + ", Serial Number: " + serialNumber + ", Brake Type: " + brakeType + ", Wheel Size: " + wheelSize + "]";
    }

}