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
        String description = super.getId() + "," + name + "," + species + ",";
        for (String crop : acceptableCropTypes) {
            description = description + crop + ",";
        }
        description = description.substring(0,description.length()-1); // Removes "," at the end.
        return description;
    }
    public void feed(Crop crop) {
        // check if crop is accepted
        // decrease quantity if it's possible to feed.
        if (acceptableCropTypes.contains(crop.getName())) {
            if(crop.takeCrop(1)) {
                System.out.println("Animal feed");
            } else {
                System.out.println("Not enough quantity of " + crop.getName());
            }
        }

    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
