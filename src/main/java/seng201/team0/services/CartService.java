package seng201.team0.services;

import seng201.team0.models.Cart;

import java.util.ArrayList;

/**
 * The service class for a cart. Used to interact with carts and has all methods used on carts.
 */
public class CartService {

    private final Cart cart;

    /**
     * The Cart Service Constructor.
     * @param size the size of the cart.
     * @param types the types the cart can be filled up with.
     * @param speed the speed of the cart.
     */
    public CartService(int size, ArrayList<String> types, int speed) {

        cart = new Cart(size, types, speed);

    }

    /**
     * The getter method for size of the cart.
     * @return the size of the cart.
     */
    public int getSize(){

        return cart.getSize();

    }

    /**
     * The getter method for the types of the cart.
     * @return the types of the cart.
     */
    public ArrayList<String> getTypes(){

        return cart.getTypes();

    }

    /**
     * The getter method for the carts speed.
     * @return the carts speed.
     */
    public int getSpeed(){

        return cart.getSpeed();

    }

    /**
     * The setter method for the carts size.
     * @param size the new size of the cart.
     */
    public void setSize(int size){

        cart.setSize(size);

    }

    /**
     * The setter method for the carts speed.
     * @param speed the new speed of the cart.
     */
    public void setSpeed(int speed){

        cart.setSpeed(speed);

    }

    /**
     * The getter method for current fill.
     * @return the current fill.
     */
    public int getCurrentFill(){

        return this.cart.getCurrentFill();

    }

    /**
     * The setter for current fill of the cart.
     * @param i the new current fill.
     */
    public void setCurrentFill(int i){

        this.cart.setCurrentFill(i);

        if(this.cart.getCurrentFill() >= this.cart.getSize()){

            this.cart.setCurrentFill(this.cart.getSize());
            this.cart.setFilled(true);

        }

    }

    /**
     * The getter for time left.
     * @return the carts time.
     */
    public int getTimeLeft(){

        return this.cart.getTimeLeft();

    }

    /**
     * This method decreases the time left of a cart by 1, it doesn't let the time go below one either.
     */
    public void decreaseTimeLeft(){

        this.cart.setTimeLeft(this.cart.getTimeLeft() - 1);

        if (this.cart.getTimeLeft() <= 0) {

            cart.setTimeLeft(0);
            cart.setTimeRanOut(true);

        }

    }

    /**
     * The setter method for time left.
     * @param i the new time.
     */
    public void setTimeLeft(int i){

        this.cart.setTimeLeft(i);

        if (cart.getTimeLeft() <= 0) {

            cart.setTimeLeft(0);
            cart.setTimeRanOut(true);

        }

    }

    /**
     * This method creates another CartService with a cart of the exact same stats.
     * @return a clone of itself.
     */
    public CartService makeClone(){

        return new CartService(this.getSize(), this.getTypes(), this.getSpeed());

    }

    /**
     * The getter method for if the cart is filled.
     * @return a boolean which is true if cart is filled.
     */
    public boolean getFilled(){

        return cart.getFilled();

    }

    /**
     * The getter for if a cart ran out of time.
     * @return a boolean that's true if the cart ran out of time.
     */
    public boolean getTimeRanOut(){

        return cart.getTimeRanOut();

    }

}
