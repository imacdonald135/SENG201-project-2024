package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Purchasable;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Random;

/**
 * The tower service class, used to interact with tower. It contains all the methods used on towers.
 */
public class TowerService implements Purchasable<TowerService>{

    private Tower tower;

    /**
     * The contructor for a tower service.
     * @param size the size of the tower.
     * @param speed the speed of the tower.
     * @param type the types of the tower.
     * @param price the price of the tower.
     * @param name the towers name.
     */
    public TowerService(double size, double speed, ArrayList<String> type, int price, String name){

        tower = new Tower(size, speed, type, price, name);

    }

    /**
     * The getter method for types.
     * @return the types of the towersService.
     */
    public ArrayList<String> getTypes(){

        return tower.getTypes();

    }

    /**
     * The getter method for load size
     * @return the loadSize of the tower service
     */
    public double getLoadSize() {

        return tower.getLoadSize();

    }

    /**
     * The getter method for loadSpeed.
     * @return the loadSpeed of the tower service.
     */
    public double getLoadSpeed() {

        return tower.getLoadSpeed();

    }

    /**
     * The getter method for towerName.
     * @return the name of the tower.
     */
    public String getTowerName(){

        return tower.getTowerName();

    }

    /**
     * The get method for thw towers price.
     * @return the tower price.
     */
    public int getPrice(){

        return tower.getTowerPrice();

    }

    /**
     * This method levels up the tower.
     */
    public void levelUp(){

        tower.setLoadSpeed(tower.getLoadSpeed() * 1.1);
        tower.setLoadSize(tower.getLoadSize() * 1.1);
        tower.setLevel(tower.getLevel() + 1);

    }

    /**
     * This method returns whether a tower breaks or not, with a higher level tower being more likely to break.
     * @return a boolean that is true if the tower broke.
     */
    public boolean doesTowerBreak(){

        Random random = new Random();
        if (tower.getLevel() >= 15) {

            return true;

        }

        int num = random.nextInt(16- tower.getLevel());
        return num == 3;

    }

    /**
     * The buy method for purchasable.
     * @param e the game environment, to access and change coins.
     * @param inventory the inventory the tower service is added to.
     */
    public void buy(GameEnvironment e, ArrayList<Purchasable<TowerService>> inventory) {

        if (e.getCoins() - this.getPrice() >= 0 && inventory.size() < 10) {
            inventory.add(this);
            e.setCoins(e.getCoins() - this.getPrice());
        }

    }

    /**
     * The sell method for purchasable.
     * @param e the game environment.
     * @param inventory the inventory the tower is being sold from.
     * @param index where the tower is in the inventory.
     */
    public void sell(GameEnvironment e, ArrayList<Purchasable<TowerService>> inventory, int index) {

        if (e.getTowerInventory().size() - 1 >= index) {
            TowerService temp = (TowerService) e.getTowerInventory().get(index);
            e.getTowerInventory().remove(index);
            e.setCoins((int) (e.getCoins() + (0.5 * temp.getPrice())));
        }

    }

    /**
     * The getter method for the towers level.
     * @return the level of the tower.
     */
    public int getLevel(){

        return tower.getLevel();

    }

    /**
     * This method creates a clone of a tower, used to add towers to your inventory.
     * @return a clone of itself.
     */
    public TowerService makeClone(){

        return new TowerService(this.getLoadSize(), this.getLoadSpeed(), this.getTypes(), this.getPrice(), this.getTowerName());

    }

    /**
     * This method uses an upgrade to upgrade the tower
     * @param u the upgrade being used on the tower.
     */
    public void upgradeTower(UpgradeService u){

        if (u.getUpgradeType().equals("LoadSize")) {

            tower.setLoadSize(tower.getLoadSize()*u.getLoadSizeMultiplier());

        } else {

            tower.setLoadSpeed(tower.getLoadSpeed()*u.getSpeedMultiplier());

        }

    }

}
