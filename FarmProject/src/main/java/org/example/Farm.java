package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {
    private CropManager cropManager = new CropManager();
    private AnimalManager animalManager = new AnimalManager();

    private File crops, animals;


    public Farm(){
        // should load the files that have been saved from last session
        // hint: file.exists()

        crops = new File("src/main/resources/cropsData.txt");
        if(crops.exists()) {
            System.out.println("crops file exists!");

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
        }
        else {
            System.out.println("animal file doesn't exists");
            try {
                animals.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /*
        * FileReader fr = new FileReader(file);
        * BufferedReader br = new BufferedReader(fr);
        * String string = br.readLine();
        * while(!line=null)
        *
        */
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
        ArrayList<Animal> animalList = animalManager.getAnimals();
        ArrayList<Crop> cropList = cropManager.getCrops();
        try {
            FileWriter fileWriter = new FileWriter(animals,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Animal animal : animalList) {
                bw.write(animal.getDescription());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(crops,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Crop crop : cropList) {
                bw.write(crop.getDescription());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
