package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {
    private ArrayList<Animal> animalList;

    public AnimalManager(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public void animalMenu(ArrayList<Crop> cropList) {
        // menu to use to different class methods

        int choice = 0;
        while (choice != 5) {
            choice = 0;
            /*
            if correct input first iteration
            but 2nd iteration gets wrong input and throws exception on parseInt,
            choice would not be updated and the last value on choice will swap case.
            */
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
            System.out.println(animal.getDescription());
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
        //System.out.println("You want to add: " + name + " " + species + " " + acceptableCropTypes); //for bugcheck

        int id = 0;
        if(!animalList.isEmpty()) {
            id = animalList.size();
        }

        Animal animal = new Animal(id,name,species,acceptableCropTypes);
        System.out.println("Welcome " + name + " to the farm!");
        animalList.add(animal);
    }
    private void removeAnimal() {
        viewAnimal();
        // input id of the animal to be removed.
        if (!animalList.isEmpty()) {
            int idChoice = 0;
            boolean correctInput = false;
            Scanner sc = new Scanner(System.in);
            while(!correctInput) {
                System.out.println("Please type the id of the animal to be removed.");
                try {
                    idChoice = Integer.parseInt(sc.nextLine());
                    correctInput = true;
                }catch (NumberFormatException e) {
                    System.out.println("The id should be a number e.g. '10'");
                }
            }
            // remove it from arraylist
            boolean animalFound = false;
            int index = 0;
            for (Animal animal:animalList) {
                if (animal.getId() == idChoice) {
                    System.out.println(animal.getName() + " the " + animal.getSpecies() + " has been removed.");
                    animalFound = true;
                    index = animalList.indexOf(animal);
                    animalList.remove(index);
                    return;
                }
            }
            if (!animalFound) {
                System.out.println("That animal can't be found!");
            }
        } else {
            System.out.println("There are no animals.");
        }
    }
    private void feedAnimals(ArrayList<Crop> cropList) {
        viewAnimal();
        int idChoiceAnimal, idChoiceCrop;
        boolean animalFound = false;
        boolean correctInput1 = false;
        boolean correctInput2 = false;
        Scanner scanner = new Scanner(System.in);
        while(!correctInput1){
            System.out.println("Please type the id of the animal you like to feed: ");
            try {
                idChoiceAnimal = Integer.parseInt(scanner.nextLine());
                correctInput1 = true;
                for (Animal animal : animalList) {
                    if (idChoiceAnimal == animal.getId()) {
                        animalFound = true;
                        for (Crop crop : cropList) {
                            System.out.println(crop.getId() + " " + crop.getName());
                        }
                        boolean cropFound = false;
                        while (!correctInput2) {
                            System.out.println("Please type the id of the crop you like to feed to the animal: ");
                            try {
                                idChoiceCrop = Integer.parseInt(scanner.nextLine());
                                correctInput2 = true;
                                for (Crop crop : cropList) {
                                    if (crop.getId() == idChoiceCrop) {
                                        cropFound = true;
                                        animal.feed(crop);
                                        return;
                                    }
                                }
                            } catch (NumberFormatException e2) {
                                System.out.println("Needs to be a number e.g. 10");
                            }

                        }

                        if (!cropFound) {
                            System.out.println("The crop was not found.");
                        }


                    }
                }
                if (!animalFound) {
                    System.out.println("This animal can't be found.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Needs to be a number, e.g. 10");
            }
        }


    }
    public ArrayList<Animal> getAnimals() {
        return animalList;
    }
}
