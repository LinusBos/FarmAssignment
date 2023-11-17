package org.example;

import java.util.ArrayList;

public class Animal extends Entity {


    private String species;
    private ArrayList<String> acceptableCropTypes;
    public Animal(int id, String name, String species, ArrayList<String> acceptableCropTypes) {
        super(id, name);
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }

    @Override
    public String getDescription() {
        String description = "Id: " + super.getId() +" Name: "+ super.getName() + " Species: " + species + " \nAcceptable crops types:";
        for (String crop : acceptableCropTypes) {
            description = description + " " + crop;
        }
        return description;
    }

    @Override
    public String getCSV() {
        String descriptionCSV = "," + super.getName() + "," + species + ",";
        for (String crop : acceptableCropTypes) {
            descriptionCSV = descriptionCSV + "@" + crop;
        }
        return descriptionCSV;
    }

    public void feed(Crop crop) {

        if (acceptableCropTypes.contains(crop.getCropType())) {
            if(crop.takeCrop(1)) {
                System.out.println("The " + getSpecies() + " " + getName() + " eats " + crop.getName());
            } else {
                System.out.println("Not enough quantity of " + crop.getName());
            }
        } else {
            System.out.println("This animal can't eat this type of crop.");
        }

    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ArrayList<String> getAcceptableCropTypes() {
        return acceptableCropTypes;
    }
}
