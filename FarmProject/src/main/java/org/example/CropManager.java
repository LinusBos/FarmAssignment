package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> cropList = new ArrayList<>();
    private  Scanner sc;
    public CropManager(ArrayList cropList) {
        this.cropList = cropList;
    }
    public void cropMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("What would you like to do?");
            System.out.println("1. View crops");
            System.out.println("2. Add crop");
            System.out.println("3. Remove crop");
            System.out.println("4. Return");
            sc = new Scanner(System.in);
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
        if (cropList.isEmpty()) {
            System.out.println("There are no crops.");
        }
        for (Crop crop : cropList) {
            System.out.println(crop.getId() + crop.getDescription());
        }

    }
    private void addCrop() {
        sc = new Scanner(System.in);
        System.out.println("Please write the name of the crop:");
        String nameCrop = sc.nextLine();
        System.out.println("Please write the type of the crop: ");
        String typeCrop = sc.nextLine();
        System.out.println("Please write the quantity of the crop: ");
        int quantity = 0;
        try{
            quantity = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Need to be a number e.g 1000");
        }
        System.out.println("You want to make: " + nameCrop + " " + typeCrop + " " + quantity);
        int id = 0;
        if (!cropList.isEmpty()) {
            id = cropList.size();
        }
        Crop crop = new Crop(id, nameCrop, typeCrop, quantity);
        cropList.add(crop);


    }
    private void removeCrop() {
        viewCrops();
        // input id of the crop to be removed.
        if (!cropList.isEmpty()) {
            int idChoice = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Please type the id of the crop to be removed.");
            try {
                idChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The id should be a number e.g '10'");
            }

            // remove it from arraylist
            boolean cropFound = false;
            int index = 0;
            for (Crop crop : cropList) {
                if (crop.getId() == idChoice) {
                    System.out.println("Crop found!");
                    System.out.println("Data: " + crop.getDescription());
                    System.out.println("Index in array: " + cropList.indexOf(crop));
                    cropFound = true;
                    index = cropList.indexOf(crop);

                }
            }
            if (!cropFound) {
                System.out.println("That crop can't be found!");
            } else {
                cropList.remove(index); // Moved remove outside of loop, otherwise it crashes on next iteration
            }
        }
    }
    public ArrayList<Crop> getCrops() {
        return cropList;
    }
}
