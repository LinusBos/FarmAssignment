package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> cropList = new ArrayList<>();
    public void cropMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("What would you like to do?");
            System.out.println("1. View crops");
            System.out.println("2. Add crop");
            System.out.println("3. Remove crop");
            System.out.println("4. Return");
            Scanner sc = new Scanner(System.in);
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 4 || choice < 1) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.out.println("Please input a number between 1-4");
            }

            switch (choice) {
                case 1:
                    viewCrops();
                    break;
                case 2:
                    addCrop();
                    break;
                case 3:
                    removeCrop();
                    break;
                case 4:
                    break;

            }
        }

    }
    private void viewCrops() {

    }
    private void addCrop() {
        // Guess makes a new crop and add to list

    }
    private void removeCrop() {

    }
    public ArrayList<Crop> getCrops() {
        return cropList;
    }
}
