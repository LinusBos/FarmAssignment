package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {
    private ArrayList<Animal> animalList = new ArrayList<>();

    public AnimalManager(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public void animalMenu(ArrayList<Crop> cropList) {
        // menu to use to different class methods
        // cropList should come from cropManager.getCrops()
        int choice = 0;
        while (choice != 5) {
            System.out.println("What would you like to do?");
            System.out.println("1. View animals");
            System.out.println("2. Add animal");
            System.out.println("3. Remove animal");
            System.out.println("4. Feed animal");
            System.out.println("5. Return");
            Scanner sc = new Scanner(System.in);
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 5 || choice < 1) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.out.println("Please input a number between 1-4");
            }

            switch (choice) {
                case 1:
                    viewAnimal();
                    break;
                case 2:
                    addAnimal();
                    break;
                case 3:
                    removeAnimal();
                    break;
                case 4:
                    feedAnimals(cropList);
                    break;
                case 5:
                    break;

            }
        }

    }
    private void viewAnimal() {
        // prints information of the animals that exists
        if (animalList.isEmpty()) {
            System.out.println("There are no animals.");
        }
        for (Animal animal: animalList) {
            System.out.println(animal.getId() + animal.getDescription());
        }
    }
    private void addAnimal() {
        // asks for the variables necessary to create a new animal and after all is correct format, make animal and add to list.
        String name,species,cropString;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please write the name of the animal: ");
        name = sc.nextLine();
        System.out.println("Please write the species of the animal: ");
        species = sc.nextLine();
        System.out.println("Please write the type of crops this animal can eat (separate with space): ");
        cropString = sc.nextLine();
        String[] stringArray = cropString.split(" ");
        ArrayList<String> acceptableCropTypes = new ArrayList<>();
        for (String s: stringArray) {
            acceptableCropTypes.add(s);
        }
        System.out.println("You want to make: " + name + " " + species + " " + acceptableCropTypes);

        int id = 0;
        if(!animalList.isEmpty()) {
            id = animalList.size();
        }

        Animal animal = new Animal(id,name,species,acceptableCropTypes);
        animalList.add(animal);
    }
    private void removeAnimal() {
        viewAnimal();
        // input id of the animal to be removed.
        if (!animalList.isEmpty()) {
            int idChoice = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Please type the id of the animal to be removed.");
            try {
                idChoice = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("The id should be a number e.g '10'");
            }

            // remove it from arraylist
            boolean animalFound = false;
            int index = 0;
            for (Animal animal:animalList) {
                if (animal.getId() == idChoice) {
                    System.out.println("Animal found!");
                    System.out.println("Data: " + animal.getDescription());
                    System.out.println("Index in array: " + animalList.indexOf(animal));
                    animalFound = true;
                    index = animalList.indexOf(animal);

                }
            }
            if (!animalFound) {
                System.out.println("That animal can't be found!");
            }else {
                animalList.remove(index); // Moved remove outside of loop, otherwise it crashes on next iteration
            }

        }

    }
    private void feedAnimals(ArrayList<Crop> cropList) {
        viewAnimal();
        System.out.println("Please type the id of the animal you like to feed: ");
        int idChoiceAnimal, idChoiceCrop;
        boolean animalFound = false;
        Scanner scanner = new Scanner(System.in);
        try {
            idChoiceAnimal = Integer.parseInt(scanner.nextLine());
            for (Animal animal : animalList) {
                if (idChoiceAnimal == animal.getId()) {
                    animalFound = true;

                    for (Crop crop : cropList) {
                        System.out.println(crop.getId() + " " + crop.getName());
                    }
                    boolean cropFound = false;
                    System.out.println("Please type the id of the crop you like to feed to the animal: ");
                    idChoiceCrop = Integer.parseInt(scanner.nextLine());
                    for (Crop crop : cropList) {
                        if (crop.getId() == idChoiceCrop) {
                            cropFound = true;
                            animal.feed(crop);
                        }
                    }
                    if (!cropFound) {
                        System.out.println("The crop was not found.");
                    }


                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Needs to be a number, e.g 10");
        }
    }
    public ArrayList<Animal> getAnimals() {
        return animalList;
    }
}
