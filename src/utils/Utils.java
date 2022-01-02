package utils;

import datastructures.Node;
import jdk.jshell.execution.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

public class Utils {
    private static boolean isUsingSeed = false;
    private static long seed = 0;
    private static Random rand = Utils.setRand();
    
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(randomArray(0d, 10d, 10)));
    }
    
    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.<T>emptyList() : iterable;
    }
    
    public static <T> T randomElement(T[] array) {
        return array[rand.nextInt(array.length)];
    }
    
    public static <T extends Number> T[] randomArray(T min, T max, int size) {
        T[] arr = (T[])new Number[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Utils.randomNumber(min, max);
        }
        return arr;
    }
    
    public static <T extends Number> T randomNumber(T min, T max) {
        if (isUsingSeed) {
            rand = new Random(seed);
        }
        double value = rand.nextDouble() * (max.doubleValue() - min.doubleValue()) + min.doubleValue();
        if (min instanceof Integer) {
            return (T)Integer.valueOf((int)value);
        } else if (min instanceof Long) {
            return (T)Long.valueOf((long)value);
        } else if (min instanceof Float) {
            return (T)Float.valueOf((float)value);
        } else if (min instanceof Double) {
            return (T)Double.valueOf(value);
        } else {
            throw new IllegalArgumentException("Unsupported type");
        }
    }
    
    public static <T extends Number> void shuffle(T[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = Utils.rand.nextInt(i + 1);
            T a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
    
    public static void setSeed(long seed) {
        Utils.seed = seed;
        Utils.isUsingSeed = true;
        Utils.rand = new Random(seed);
    }
    
    public static void setSeed() {
        Utils.isUsingSeed = false;
        Utils.rand = new Random();
    }
    
    public static Random setRand() {
        return Utils.isUsingSeed ? new Random(Utils.seed) : new Random();
    }
}
