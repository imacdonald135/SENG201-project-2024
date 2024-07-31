package seng201.team0.models;

import java.util.ArrayList;

/**
 * The basic upgrade class.
 * Contains getters and setters.
 */
public class Upgrade{
    private String upgradeType;
    private double loadSizeMultiplier;
    private double speedMultiplier;
    private int upgradePrice;

    /**
     * The constructor for a load size or speed upgrade.
     * @param uType either "LoadSize" or "Speed".
     * @param multiplier the multiplier amount.
     * @param price the price.
     */
    public Upgrade(String uType, double multiplier, int price){

        upgradePrice = price;

        if (uType.equals("LoadSize")) {

            upgradeType = uType;
            loadSizeMultiplier = multiplier;

        } else {

            upgradeType = "Speed";
            speedMultiplier = multiplier;

        }

    }

    /**
     * The upgrade constructor when it's a spare life.
     * @param price the price.
     */
    public Upgrade(int price){

        upgradePrice = price;
        upgradeType = "Life";

    }

    /**
     * The getter method for load size multiplier.
     * @return the load size multiplier.
     */
    public double getLoadSizeMultiplier(){

        return loadSizeMultiplier;

    }

    /**
     * The getter method for speed multiplier.
     * @return the speed multiplier.
     */
    public double getSpeedMultiplier(){

        return speedMultiplier;

    }

    /**
     * The getter method for what type of upgrade it is.
     * @return the type of the upgrade.
     */
    public String getUpgradeType(){

        return upgradeType;

    }

    /**
     * The getter method for price.
     * @return the price of the upgrade.
     */
    public int getPrice() {

        return upgradePrice;

    }



}
