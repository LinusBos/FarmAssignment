package org.example;

public class Crop extends Entity {

    private String cropType;
    private int quantity;

    public Crop(int id, String name, String cropType, int quantity) {
        super(id,name);
        this.cropType = cropType;
        this.quantity = quantity;
    }
    public String getCropType() {
        return cropType;
    }
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    @Override
    public String getDescription() {
        String description ="Id: " + super.getId() +" Name: "+ super.getName() + " Crop type: " + cropType + " Quantity: " + quantity;
        return description;
    }

    @Override
    public String getCSV() {
        String descriptionCSV ="," + super.getName() + "," + cropType + "," + quantity;
        return descriptionCSV;
    }

    /**
     *
     * @param amount to increase the quantity with.
     */
    public void addCrop (int amount) {
        quantity = quantity + amount;
    }

    /**
     * @param amount to reduce the current quantity.
     * @return true if argument >= quantity, else false.
     */
    public boolean takeCrop(int amount) {
        if (quantity >= amount) {
            quantity = quantity - amount;
            return true;
        } else {
            return false;
        }
    }
}
