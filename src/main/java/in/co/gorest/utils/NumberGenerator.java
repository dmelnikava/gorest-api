package in.co.gorest.utils;

import java.security.SecureRandom;

public class NumberGenerator {

    public static int generateInt(int minValue, int maxValue) {
        return new SecureRandom().nextInt(maxValue + 1 - minValue) + minValue;
    }
}
