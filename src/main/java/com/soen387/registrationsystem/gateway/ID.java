package com.soen387.registrationsystem.gateway;
import java.util.Random;

public class ID {
    private static final Random random = new Random();
    public static int getID() {
        return random.nextInt(1000000000) + 1;
    }
}
