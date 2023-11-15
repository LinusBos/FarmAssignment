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
        System.out.println("Please write the id of the crop: ");
        int id = 0;
        try{
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Need to be a number e.g 10");
        }
        System.out.println("You want to make: " + nameCrop + " " + typeCrop + " " + quantity);
        Crop crop = new Crop(id, nameCrop, typeCrop, quantity);
        cropList.add(crop);
        // Guess makes a new crop and add to list

    }
    private void removeCrop() {

    }
    public ArrayList<Crop> getCrops() {
        return cropList;
    }
}
