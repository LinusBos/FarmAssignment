package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {
    private CropManager cropManager;
    private AnimalManager animalManager ;

    private File crops, animals;
    private ArrayList<Crop> cropList = new ArrayList<>();
    private ArrayList<Animal> animalList = new ArrayList<>();


    public Farm() {
        crops = new File("src/main/resources/cropsData.txt");
        animals = new File("src/main/resources/animalsData.txt");
        int tempId;
        String tempName;
        String tempType;
        int tempQuantity;
        String[] values;
        if(crops.exists()) {
            //System.out.println("crops file exists!"); // use for bugchecks
            FileReader fr = null;
            try {
                fr = new FileReader(crops);
                BufferedReader br = new BufferedReader(fr);
                String description;
                while ((description = br.readLine())!=null) {
                    values = description.split(",");
                    tempId = Integer.parseInt(values[0]);
                    tempName = values[1];
                    tempType = values[2];
                    tempQuantity = Integer.parseInt(values[3]);
                    Crop crop = new Crop(tempId, tempName, tempType, tempQuantity);
                    cropList.add(crop);
                }
                br.close();
            } catch (FileNotFoundException f) {

            } catch (IOException e) {
                System.out.println("Problem with bufferedReader."); // Bugcheck
            }
        }
        else {
            //System.out.println("crops doesn't exists!"); //use for bugcheck
            try {
                crops.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Crop crop1 = new Crop(0,"Tomato","Vegetable",10);
            cropList.add(crop1);
            Crop crop2 = new Crop(1,"Carrot","Vegetable",5);
            cropList.add(crop2);
            Crop crop3 = new Crop(2,"Wheat","Grain",10);
            cropList.add(crop3);
        }

        if(animals.exists()) {
            //System.out.println("animal file exists"); //use for bugcheck
            FileReader fr = null;
            try {
                fr = new FileReader(animals);
                BufferedReader br = new BufferedReader(fr);
                String description;
                while ((description = br.readLine())!=null) {
                    values = description.split(",");
                    tempId = Integer.parseInt(values[0]);
                    tempName = values[1];
                    tempType = values[2];
                    ArrayList<String> cropTypes = new ArrayList<>();
                    String[] tempArray = values[3].split("@"); //cropsTypes seperated with '@'

                    // tempArray[0] is empty so starting from index 1.
                    for (int i = 1; i < tempArray.length; i++) {
                        cropTypes.add(tempArray[i]);
                    }

                    Animal animal = new Animal(tempId, tempName, tempType, cropTypes);
                    animalList.add(animal);
                }
                br.close();
                } catch (FileNotFoundException e) {

                } catch (IOException e) {
                    System.out.println("Problem with bufferedReader.");
                }
        }
        else {
            // System.out.println("animal file doesn't exist"); // Bugcheck
            try {
                animals.createNewFile();
                ArrayList<String> food1 = new ArrayList<>();
                ArrayList<String> food2 = new ArrayList<>();
                ArrayList<String> food3 = new ArrayList<>();
                food1.add("Forage");
                food2.add("Vegetable");
                food3.add("Seed");
                Animal animal1 = new Animal(0,"Ros-Marie","Cow",food1);

                Animal animal2 = new Animal(1,"Olle","Pig",food2);
                Animal animal3 = new Animal(2,"Kerstin","Hen",food3);
                animalList.add(animal1);
                animalList.add(animal2);
                animalList.add(animal3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        cropManager = new CropManager(cropList);
        animalManager = new AnimalManager(animalList);


    }

    public void mainMenu() {
        int choice = 0;
        while (choice != 4){
            choice = 0;
             /*
            if correct input first iteration
            but 2nd iteration gets wrong input and throws exception on parseInt,
            choice would not be updated and the last value on choice will swap case.
            */
            System.out.println("What would you like to do?");
            System.out.println("1. Manage crops");
            System.out.println("2. Manage animals");
            System.out.println("3. Save");
            System.out.println("4. Exit");
            Scanner sc = new Scanner(System.in);
            try {
                choice = Integer.parseInt(sc.nextLine());
                if(choice > 4 || choice < 1) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e){
                System.out.println("Please input a number between 1-4");
            }

            switch (choice){
                case 1:
                    cropManager.cropMenu();
                    break;
                case 2:
                    animalManager.animalMenu(cropManager.getCrops());
                    break;
                case 3:
                    save();
                    break;
                case 4:
                    break;

            }
        }



    }
    private void save() {
        animalList = animalManager.getAnimals();
        cropList = cropManager.getCrops();
        int idCounter;
        try {
            //Reading Animals
            FileWriter fileWriter = new FileWriter(animals);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            idCounter = 0;
            for (Animal animal : animalList) {
                String lineToFile = idCounter + animal.getCSV();
                bw.write(lineToFile);
                bw.newLine();
                idCounter++;
            }

            bw.close();
            System.out.println("Animals saved!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //Reading Crops
            FileWriter fileWriter = new FileWriter(crops);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            idCounter = 0;
            for (Crop crop : cropList) {
                String lineToFile = (idCounter) + crop.getCSV();
                bw.write(lineToFile);
                bw.newLine();
                idCounter++;
            }
            bw.close();
            System.out.println("Crops saved!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
