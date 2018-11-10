package ua.training.fpl.util;

public final class Numbers {

    private Numbers() {}

    public static boolean between(long number, long lowerBound, long upperBound) {
        return (lowerBound <= number) && (number <= upperBound);
    }
}
