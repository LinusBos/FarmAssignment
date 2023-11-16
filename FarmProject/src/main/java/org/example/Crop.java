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
        String description ="," + super.getName() + "," + cropType + "," + quantity;
        return description;
    }
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
