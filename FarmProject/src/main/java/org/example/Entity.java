package org.example;

public abstract class Entity {
    private int id;

    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    /**
     * @return a string with the variables for the class separated with ",".
     */
    public abstract String getDescription();
    public abstract String getCSV();
}
