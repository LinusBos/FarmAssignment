package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {
    private CropManager cropManager;
    private AnimalManager animalManager = new AnimalManager();

    private File crops, animals;
    private ArrayList<Crop> cropList = new ArrayList<>();
    private ArrayList<Animal> animalList;


    public Farm() {
        // should load the files that have been saved from last session
        // hint: file.exists()
        crops = new File("src/main/resources/cropsData.txt");
        if(crops.exists()) {
            System.out.println("crops file exists!");
            FileReader fr = null;
            int cropId;
            String cropName;
            String cropType;
            int quantity;
            String[] values;
            try {
                fr = new FileReader(crops);
                BufferedReader br = new BufferedReader(fr);
                while (br.readLine()!=null) {
                    String description = br.readLine();
                    values = description.split(",");
                    cropId = Integer.parseInt(values[0]);
                    cropName = values[1];
                    cropType = values[2];
                    quantity = Integer.parseInt(values[3]);
                    Crop crop = new Crop(cropId, cropName, cropType, quantity);
                    cropList.add(crop);
                }
            } catch (FileNotFoundException e) {

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
        animals = new File("src/main/resources/animalsData.txt");
        if(animals.exists()) {
            System.out.println("animal file exists");
            FileReader fr = null;
            try {
                fr = new FileReader(animals);
                BufferedReader br = new BufferedReader(fr);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

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
        try {
            FileWriter fileWriter = new FileWriter(animals, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Animal animal : animalList) {
                bw.write(animal.getDescription());
            }

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(crops,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Crop crop : cropList) {
                bw.write(crop.getDescription());
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
