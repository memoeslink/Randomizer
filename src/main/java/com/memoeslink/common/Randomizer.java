package com.memoeslink.common;

import java.util.*;

public class Randomizer {
    private Long seed;
    private Random r;
    private static final HashMap<Integer, Double> ARRAY_WEIGHT_REGISTRY = new HashMap<>();

    public Randomizer() {
        bindSeed(null);
    }

    public Randomizer(Long seed) {
        bindSeed(seed);
    }

    public Long getSeed() {
        return seed;
    }

    public void bindSeed(Long seed) {
        this.seed = seed;

        if (seed != null)
            r = new Random(seed);
        else
            r = new Random();
    }

    public void unbindSeed() {
        bindSeed(null);
    }

    public int getInt() {
        return r.nextInt();
    }

    public int getInt(int n) {
        if (n < 1)
            n = 1;
        return r.nextInt(n);
    }

    public int getInt(int start, int n) {
        int value;
        boolean negative = false;

        if (n < 1)
            n = 1;

        if (start < 0) {
            negative = true;
            start = Math.abs(start);
        }
        value = r.nextInt(n);

        if (negative)
            value = value - start;
        else
            value = value + start;
        return value;
    }

    public boolean getBoolean() {
        return r.nextBoolean();
    }

    public float getFloat() {
        return r.nextFloat();
    }

    public float getFloat(int n) {
        if (n < 1)
            n = 1;
        return r.nextFloat(n);
    }

    public float getFloat(float min, float max) {
        if (max > min)
            return (r.nextFloat() * (max - min)) + min;
        return r.nextFloat();
    }

    public double getDouble() {
        return r.nextDouble();
    }

    public double getDouble(int n) {
        if (n < 1)
            n = 1;
        return r.nextDouble(n);
    }

    public double getDouble(double min, double max) {
        if (max > min)
            return (r.nextDouble() * (max - min)) + min;
        return r.nextDouble();
    }

    public long getLong() {
        return r.nextLong();
    }

    public long getLong(long n) {
        if (n < 1L)
            n = 1L;
        return r.nextLong(n);
    }

    public int getGaussianInt(int standardDeviation, int mean, int constraint) {
        int value;
        int tries = 999;

        try {
            do {
                value = (int) Math.round(r.nextGaussian() * standardDeviation + mean);
                tries--;
            } while (value <= constraint && tries > 0);

            if (tries == 0)
                value = constraint;
            return value;
        } catch (Exception e) {
            return constraint;
        }
    }

    public List<Integer> getIntegers(int maxNumbers, int maxValue, boolean nonRepeating) {
        List<Integer> numbers = new ArrayList<>();

        if (maxNumbers <= 0)
            maxNumbers = 1;

        if (maxValue <= 0)
            maxValue = 2;

        if (r != null) {
            for (int n = 0; n < maxNumbers; n++) {
                numbers.add(r.nextInt(maxValue + 1));
            }

            if (nonRepeating) {
                Set<Integer> set = new HashSet<>();
                set.addAll(numbers);
                numbers.clear();
                numbers.addAll(set);
            }
        }
        return numbers;
    }

    public <T> T getElement(T[] array) {
        if (array == null || array.length == 0)
            return null;
        return array[r.nextInt(array.length)];
    }

    public <T> T getItem(List<T> list) {
        if (list == null || list.size() == 0)
            return null;
        return list.get(r.nextInt(list.size()));
    }

    public char chooseOnWeight(WeightedChar[] weightedChars) {
        weightedChars = weightedChars != null ? weightedChars : new WeightedChar[]{};
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(Arrays.hashCode(weightedChars), 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedChar c : weightedChars) {
                completeWeight += c.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(Arrays.hashCode(weightedChars), completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (WeightedChar c : weightedChars) {
            weight += c.getWeight();

            if (weight > 0.0D && weight >= probability) return c.getValue();
        }
        return new WeightedChar().getValue();
    }
}
