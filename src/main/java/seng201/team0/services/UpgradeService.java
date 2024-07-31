package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Purchasable;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;

/**
 * The upgrade service class used to interact with upgrades. Contains all methods on upgrades.
 */
public class UpgradeService implements Purchasable<UpgradeService>{

    private Upgrade upgrade;

    /**
     * The constructor for a load size or speed upgrade service.
     * @param uType either "LoadSize" or "Speed".
     * @param multiplier the multiplier amount.
     * @param price the price.
     */
    public UpgradeService(String uType, double multiplier, int price){

        upgrade = new Upgrade(uType, multiplier, price);
    }

    /**
     * The upgrade service constructor when it's a life upgrade service.
     * @param price the price.
     */
    public UpgradeService(int price){

        upgrade = new Upgrade(price);

    }

    /**
     * The getter method for load size multiplier.
     * @return the load size multiplier.
     */
    public double getLoadSizeMultiplier(){

        return upgrade.getLoadSizeMultiplier();

    }

    /**
     * The getter method for speed multiplier.
     * @return the speed multiplier.
     */
    public double getSpeedMultiplier(){

        return upgrade.getSpeedMultiplier();

    }

    /**
     * The getter method for what type of upgrade it is.
     * @return the type of the upgrade.
     */
    public String getUpgradeType(){

        return upgrade.getUpgradeType();

    }

    /**
     * The getter method for price.
     * @return the price.
     */
    public int getPrice() {

        return upgrade.getPrice();

    }

    /**
     * The buy method for the upgrade service.
     * @param e the game enviroment to access and change coins.
     * @param inventory the inventory the upgrade service is added too.
     */
    public void buy(GameEnvironment e, ArrayList<Purchasable<UpgradeService>> inventory){

        if (e.getCoins() - this.getPrice() >= 0) {

            inventory.add(this);
            e.setCoins(e.getCoins() - this.getPrice());

        }

    }

    /**
     * The sell method for an upgrade, removes it from its inventory and refunds the player some coins.
     * @param e the game environment.
     * @param inventory the inventory the upgrade was in.
     * @param index the index the upgrade was at.
     */
    public void sell(GameEnvironment e, ArrayList<Purchasable<UpgradeService>> inventory, int index){

        if (index == 0 && !e.getLoadSizeUpgrades().isEmpty()) {

            UpgradeService temp = (UpgradeService) e.getLoadSizeUpgrades().get(0);
            e.getLoadSizeUpgrades().remove(0);
            e.setCoins((int) (e.getCoins() + (0.5 * temp.getPrice())));

        } else if (index == 1 && !e.getSpeedUpgrades().isEmpty()) {

            UpgradeService temp = (UpgradeService) e.getSpeedUpgrades().get(0);
            e.getSpeedUpgrades().remove(0);
            e.setCoins((int) (e.getCoins() + (0.5 * temp.getPrice())));

        } else if (index == 2 && !e.getLifeUpgrades().isEmpty()) {

            UpgradeService temp = (UpgradeService) e.getLifeUpgrades().get(0);
            e.getLifeUpgrades().remove(0);
            e.setCoins((int) (e.getCoins() + (0.5 * temp.getPrice())));

        }

    }

    /**
     * This method creates a clone of an Upgrade Service, used to add upgrades to your inventory.
     * @return a clone of itself
     */
    public UpgradeService makeClone(){

        if (upgrade.getUpgradeType().equals("Life")){

            return new UpgradeService(this.getPrice());

        } else if (upgrade.getUpgradeType().equals("Speed")) {

            return new UpgradeService(this.getUpgradeType(), this.getSpeedMultiplier(), this.getPrice());

        } else {

            return new UpgradeService(this.getUpgradeType(), this.getLoadSizeMultiplier(), this.getPrice());

        }

    }

}
