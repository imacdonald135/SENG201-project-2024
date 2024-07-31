package seng201.team0.models;

import java.util.ArrayList;

/**
 * The basic cart class.
 * Contains getters and setters.
 */
public class Cart {

    ArrayList<String> types;
    int size;
    int speed;
    int currentFill = 0;
    int timeLeft;
    boolean filled = false;
    boolean timeRanOut = false;

    /**
     * The constructor for a cart.
     * @param s the size of this cart.
     * @param type the types that can fill the cart up.
     * @param aSpeed the speed of the cart.
     */
    public Cart(int s, ArrayList<String> type, int aSpeed){

        this.types = type;
        this.size = s;
        this.speed = aSpeed;
        this.timeLeft = (int)150/aSpeed;


    }

    /**
     * The getter method for carts time left.
     * @return the time left.
     */
    public int getTimeLeft(){

        return this.timeLeft;

    }

    /**
     * The setter for carts time left.
     * @param newTime the new time left.
     */
    public void setTimeLeft(int newTime){

        this.timeLeft = newTime;

    }

    /**
     * The setter method for size.
     * @param newSize the new size.
     */
    public void setSize(int newSize){

        this.size = newSize;

    }

    /**
     * The getter method for size.
     * @return the carts size.
     */
    public int getSize() {

        return size;

    }

    /**
     * The getter method for types.
     * @return the types of this cart.
     */
    public ArrayList<String> getTypes() {

        return types;

    }

    /**
     * The setter method for speed.
     * @param newSpeed the new speed.
     */
    public void setSpeed(int newSpeed){

        this.speed= newSpeed;

    }

    /**
     * The getter method for speed.
     * @return the carts speed.
     */
    public int getSpeed() {

        return this.speed;

    }

    /**
     * The setter method for currentFill.
     * @param newFill the new currentFill.
     */
    public void setCurrentFill(int newFill){

        this.currentFill= newFill;

    }

    /**
     * The getter method for currentFill.
     * @return the carts currentFill.
     */
    public int getCurrentFill() {

        return this.currentFill;

    }

    /**
     * The getter for filled boolean.
     * @return true if cart is filled.
     */
    public boolean getFilled(){

        return filled;

    }

    /**
     * The getter for timeRanOut boolean.
     * @return true if cart ran out of time.
     */
    public boolean getTimeRanOut(){

        return timeRanOut;

    }

    /**
     * The setter for if the cart is filled.
     * @param b true if cart is filled.
     */
    public void setFilled(boolean b){

        filled = b;

    }

    /**
     * The setter for if the cart is filled.
     * @param b true if cart is filled.
     */
    public void setTimeRanOut(boolean b){

        timeRanOut = b;

    }

}
