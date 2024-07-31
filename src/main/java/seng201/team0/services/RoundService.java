package seng201.team0.services;

import seng201.team0.models.Round;

import java.util.ArrayList;

/**
 * The service class for a round, used to interact with rounds.
 */
public class RoundService {

    private final Round round;

    /**
     * The constructor for a cart service.
     * @param someCarts the carts in the round.
     */
    public RoundService(ArrayList<CartService> someCarts){

        round = new Round(someCarts);

    }

    /**
     * The getter method for the carts in a round.
     * @return the carts in the round.
     */
    public ArrayList<CartService> getCarts(){

        return round.getCarts();

    }

}
