package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> cropList;
    private  Scanner sc;
    public CropManager(ArrayList cropList) {
        this.cropList = cropList;
    }
    public void cropMenu() {
        int choice = 0;
        while (choice != 4) {
            choice = 0;
             /*
            if correct input first iteration
            but 2nd iteration gets wrong input and throws exception on parseInt,
            choice would not be updated and the last value on choice will swap case.
            */
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
            System.out.println(crop.getDescription());
        }

    }
    private void addCrop() {
        sc = new Scanner(System.in);
        boolean correctInput1 = false;
        boolean correctInput2 = false;
        int tempId;
        boolean idFound = false;
        while (!correctInput1) {
            System.out.println("Please write the id of the crop: ");
            try {
                tempId = Integer.parseInt(sc.nextLine());
                correctInput1 = true;
                for (Crop crop : cropList) {
                    if (crop.getId() == tempId)
                    {
                        idFound = true;
                        int quantityExisting = 0;
                        while (!correctInput2) {
                            System.out.println("Please write the quantity you like to add to " + crop.getName()+": ");
                            try {
                                quantityExisting = Integer.parseInt(sc.nextLine());
                                correctInput2 = true;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Needs to be a number e.g. 10");
                            }
                        }
                        System.out.println("You add " + quantityExisting + " to " + crop.getName());
                        crop.addCrop(quantityExisting);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Needs to be a number e.g. 10");
            }
        }

        if (!idFound) { // only if the id doesn't exist.
            System.out.println("Please write the name of the crop:");
            String nameCrop = sc.nextLine();
            System.out.println("Please write the type of the crop: ");
            String typeCrop = sc.nextLine();
            System.out.println("Please write the quantity of the crop: ");
            int quantity = 0;
            correctInput1 = false;
            while (!correctInput1) {
                try{
                    quantity = Integer.parseInt(sc.nextLine());
                    correctInput1 = true;
                } catch (NumberFormatException e) {
                    System.out.println("Need to be a number e.g. 10");
                }
            }
            System.out.println("You created: " + nameCrop + " " + typeCrop + " " + quantity);
            int id = 0;
            if (!cropList.isEmpty()) {
                id = cropList.size();
            }
            Crop crop = new Crop(id, nameCrop, typeCrop, quantity);
            cropList.add(crop);
        }






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
                System.out.println("The id should be a number e.g. '10'");
            }

            // remove it from arraylist
            boolean cropFound = false;
            int index = 0;
            for (Crop crop : cropList) {
                if (crop.getId() == idChoice) {
                    System.out.println("Crop " + crop.getName() + " has been removed.");
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

    /**
     *
     * @return arraylist with crops stored and recent added.
     */
    public ArrayList<Crop> getCrops() {
        return cropList;
    }
}
