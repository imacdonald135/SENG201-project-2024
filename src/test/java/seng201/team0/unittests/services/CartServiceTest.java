package seng201.team0.unittests.services;
import org.junit.jupiter.api.Test;
import seng201.team0.services.CartService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartServiceTest;
    @Test
    void testGetSize(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        int size = cartServiceTest.getSize();
        assertEquals(200, size);

    }

    @Test
    void testGetTypes(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Sweat"));
        for (int i = 0; i < expected.size(); i++){
            assertEquals(expected.get(i), cartServiceTest.getTypes().get(i));
        }

    }

    @Test
    void testGetSpeed(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        assertEquals(5, cartServiceTest.getSpeed());

    }

    @Test
    void testSetSize(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        cartServiceTest.setSize(100);
        assertEquals(100, cartServiceTest.getSize());

    }

    @Test
    void testSetSpeed(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        cartServiceTest.setSpeed(10);
        assertEquals(10, cartServiceTest.getSpeed());

    }

    @Test
    void testGetCurrentFill(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        assertEquals(0, cartServiceTest.getCurrentFill());

    }

    @Test
    void testSetCurrentFill(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        cartServiceTest.setCurrentFill(5);
        assertEquals(5, cartServiceTest.getCurrentFill());
        cartServiceTest.setCurrentFill(205);
        assertEquals(200, cartServiceTest.getCurrentFill());

    }

    @Test
    void testGetTimeLeft(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        assertEquals(30, cartServiceTest.getTimeLeft());
        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 10);
        assertEquals(15, cartServiceTest.getTimeLeft());


    }

    @Test
    void testDecreaseTimeLeft(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        cartServiceTest.decreaseTimeLeft();
        assertEquals(29, cartServiceTest.getTimeLeft());
        cartServiceTest.setTimeLeft(0);
        cartServiceTest.decreaseTimeLeft();
        assertEquals(0, cartServiceTest.getTimeLeft());

    }

    @Test
    void testSetTimeLeft(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        cartServiceTest.setTimeLeft(12);
        assertEquals(12, cartServiceTest.getTimeLeft());
        cartServiceTest.setTimeLeft(-3);
        assertEquals(0, cartServiceTest.getTimeLeft());

    }

    @Test
    void testMakeClone(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        CartService clone = cartServiceTest.makeClone();
        assertEquals(cartServiceTest.getSize(), clone.getSize());
        assertEquals(cartServiceTest.getSpeed(), clone.getSpeed());
        assertEquals(cartServiceTest.getTypes(), clone.getTypes());
        assertEquals(cartServiceTest.getTimeLeft(), clone.getTimeLeft());
        assertEquals(cartServiceTest.getFilled(), clone.getFilled());

    }

    @Test
    void testGetFilled(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        assertFalse(cartServiceTest.getFilled());
        cartServiceTest.setCurrentFill(200);
        assertTrue(cartServiceTest.getFilled());

    }

    @Test
    void testTimeRanOut(){

        cartServiceTest = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
        assertFalse(cartServiceTest.getTimeRanOut());
        cartServiceTest.setTimeLeft(0);
        assertTrue(cartServiceTest.getTimeRanOut());
        cartServiceTest.setTimeLeft(-1);
        assertTrue(cartServiceTest.getTimeRanOut());
        
    }


}
