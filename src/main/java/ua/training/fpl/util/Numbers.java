package ua.training.fpl.util;

public final class Numbers {

    private Numbers() {}

    public static boolean between(long number, long lowerBound, long upperBound) {
        return (lowerBound <= number) && (number <= upperBound);
    }

    public static Integer parseOrDefault(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }
}
