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
        // should load the files that have been saved from last session
        // hint: file.exists()
        crops = new File("src/main/resources/cropsData.txt");
        animals = new File("src/main/resources/animalsData.txt");
        int tempId;
        String tempName;
        String tempType;
        int tempQuantity;
        String[] values;
        if(crops.exists()) {
            System.out.println("crops file exists!");
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
                System.out.println("Problem with bufferedReader.");
            }
        }
        else {
            System.out.println("crops doesn't exists!");
            try {
                crops.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(animals.exists()) {
            System.out.println("animal file exists");
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
                    // tempArray[0] = " "
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
            cropManager = new CropManager(cropList);
            animalManager = new AnimalManager(animalList);

        }
        else {
            System.out.println("animal file doesn't exists");
            try {
                animals.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }

    public void mainMenu() {
        int choice = 0;
        while (choice != 4){
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
        // TODO save files to csv-file.
        // TODO calls animalManager.getAnimals() and cropManager.getCrops()
        animalList = animalManager.getAnimals();
        cropList = cropManager.getCrops();
        int idCounter;
        try {
            FileWriter fileWriter = new FileWriter(animals);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            idCounter = 0;
            for (Animal animal : animalList) {
                String lineToFile = idCounter + animal.getDescription();
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
            FileWriter fileWriter = new FileWriter(crops);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            idCounter = 0;
            for (Crop crop : cropList) {
                String lineToFile = (idCounter) + crop.getDescription();
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
