package seng201.team0.models;

import seng201.team0.services.CartService;

import java.util.ArrayList;

/**
 * The basic round class.
 * Contains getters and setters.
 */
public class Round {

    ArrayList<CartService> carts;

    /**
     * The constructor for a round.
     * @param someCarts the carts in this round.
     */
    public Round(ArrayList<CartService> someCarts){

        this.carts = someCarts;

    }

    /**
     * The getter method for carts.
     * @return the carts of this round.
     */
    public ArrayList<CartService> getCarts(){

        return this.carts;

    }

}
