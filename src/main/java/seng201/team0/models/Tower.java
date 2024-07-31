package seng201.team0.models;

import java.util.ArrayList;

/**
 * The basic tower class.
 * Contains getters and setters.
 */
public class Tower {

    ArrayList<String> types;
    double loadSize;
    double loadSpeed;
    String name;
    int price;
    int level = 1;

    /**
     * The constructor for a tower.
     * @param size The towers size.
     * @param speed The towers speed.
     * @param type The towers types.
     * @param price The price of the tower.
     * @param name The towers name.
     */
    public Tower(double size, double speed, ArrayList<String> type, int price, String name){

        this.types = type;
        this.loadSize = size;
        this.loadSpeed = speed;
        this.name = name;
        this.price = price;

    }

    /**
     * The getter method for types.
     * @return the types of the tower.
     */
    public ArrayList<String> getTypes(){

        return this.types;

    }

    /**
     * The setter method for loadSize.
     * @param loadSize the new load size.
     */
    public void setLoadSize(double loadSize) {

        this.loadSize = loadSize;

    }

    /**
     * The getter method for load size.
     * @return the loadSize of the tower.
     */
    public double getLoadSize() {

        return this.loadSize;

    }

    /**
     * The setter method for loadSpeed.
     * @param loadSpeed the new loadSpeed.
     */
    public void setLoadSpeed(double loadSpeed) {

        this.loadSpeed = loadSpeed;

    }

    /**
     * The getter method for loadSpeed.
     * @return the loadSpeed of the tower.
     */
    public double getLoadSpeed() {

        return loadSpeed;

    }

    /**
     * The getter method for towerName.
     * @return the name of the tower.
     */
    public String getTowerName(){

        return name;

    }

    /**
     * The getter method for TowerPrice.
     * @return the name of the tower.
     */
    public int getTowerPrice(){

        return price;

    }

    /**
     * The getter method for the towers level.
     * @return the towers current level.
     */
    public int getLevel(){

        return level;

    }

    /**
     * The setter method for tower level.
     * @param newLevel the new level.
     */
    public void setLevel(int newLevel){

        level = newLevel;

    }

}
