package org.example;
/*
* A small program made as an assignment for a course at NBI Handelsakademin.
* The Program is about a farm that has animals and crops. The animals can be feed with crops, add new animals and remove.
* The crops can also be added and removed.
* When the program is executed the main menu will show up and from there the user can make decisions e.g. save, which stores the data about the animals and crops in .txt files in CSV format.
* Made by Linus Boström 2023-11-17
*/
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Farm!");
        Farm farm = new Farm();
        farm.mainMenu();
    }
}