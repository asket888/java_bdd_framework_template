package utils;

public class MathRandomUtil {

    public int getMathRandom (int a, int b) {

        return (int) (Math.random()*((b - a) + 1) + a);
    }
}
