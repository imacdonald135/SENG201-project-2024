package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.services.TowerService;
import seng201.team0.services.UpgradeService;

import java.util.ArrayList;

/**
 * The purchasable interface, implemented by TowerService and UpgradeService.
 * @param <T> Type of object implementing purchasable.
 */
public interface Purchasable <T>{

    /**
     * The getter method for price.
     * @return The price of the object.
     */
    int getPrice();

    /**
     * Used on an object to buy, adds it to the given inventory and updates the value of coins in GameEnvironment.
     * @param e GameEnvironment.
     * @param inventory the inventory corresponding to the object.
     */
    void buy(GameEnvironment e, ArrayList<Purchasable<T>> inventory);

    /**
     * Used on an object to sell, removes it from the given inventory and updates the value of coins in GameEnvironment.
     * @param e GameEnvironment.
     * @param inventory the inventory corresponding to the object.
     * @param index the index of the item in the inventory.
     */
    void sell(GameEnvironment e, ArrayList<Purchasable<T>> inventory, int index);

}
