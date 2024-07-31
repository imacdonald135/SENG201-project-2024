package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.services.CartService;
import seng201.team0.services.RoundService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundServiceTest {

    @Test
    void testGetCarts(){

        CartService easyBloodCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Blood")), 5);
        CartService easyTearsCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Tears")), 5);
        RoundService testRound = new RoundService(new ArrayList<>(Arrays.asList(easyBloodCartService, easyTearsCartService)));
        ArrayList<CartService> carts = testRound.getCarts();
        assertEquals(Arrays.asList(easyBloodCartService, easyTearsCartService), carts);

    }
}
