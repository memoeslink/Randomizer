package com.memoeslink.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RandomizerTest {

    @Test
    void getSeedAsNull(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        assertNull(r.getSeed());
        System.out.println(testInfo.getDisplayName() + " -> " + r.getSeed());
    }

    @Test
    void getSeedAsLong(TestInfo testInfo) {
        Randomizer r = new Randomizer(0L);
        assertNotNull(r.getSeed());
        System.out.println(testInfo.getDisplayName() + " -> " + r.getSeed());
    }

    @Test
    void bindSeedAsNull(TestInfo testInfo) {
        Randomizer r = new Randomizer(0L);
        r.bindSeed(null);
        assertNull(r.getSeed());
        System.out.println(testInfo.getDisplayName() + " -> " + r.getSeed());
    }

    @Test
    void bindSeedAsLong(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        r.bindSeed(0L);
        assertNotNull(r.getSeed());
        System.out.println(testInfo.getDisplayName() + " -> " + r.getSeed());
    }

    @Test
    void unbindSeed(TestInfo testInfo) {
        Randomizer r = new Randomizer(0L);
        r.unbindSeed();
        assertNull(r.getSeed());
        System.out.println(testInfo.getDisplayName() + " -> " + r.getSeed());
    }

    @Test
    void getBoolean(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        boolean value = r.getBoolean();
        assertNotNull(value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getInt(TestInfo testInfo) {
        Randomizer r = new Randomizer(15212817L);
        int value = r.getInt();
        assertTrue(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getInt(100);
        assertTrue(0 <= value && value < 100);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntFromZeroBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getInt(0);
        assertEquals(0, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntFromNegativeBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getInt(-10);
        assertEquals(0, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getInt(-15, -10);
        assertTrue(-15 <= value && value < -10);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntFromOriginWithLesserBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getInt(-15, -25);
        assertEquals(0, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getIntInRange(-5, 5);
        assertTrue(-5 <= value && value <= 5);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntInRangeAsPositive(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getIntInRange(20, 30);
        assertTrue(20 <= value && value <= 30);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntInRangeAsNegative(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getIntInRange(-10, -5);
        assertTrue(-10 <= value && value <= -5);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntInRangeWithEqualLimits(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getIntInRange(-10, -10);
        assertEquals(-10, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getIntInRangeWithLesserMax(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getIntInRange(-10, -15);
        assertEquals(0, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getInts(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Integer> values = r.getInts(10);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getIntsFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Integer> values = r.getInts(10, 10);
        assertTrue(values.stream().allMatch(value -> 0 <= value && value < 10));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getIntsFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Integer> values = r.getInts(10, -100, 100);
        assertTrue(values.stream().allMatch(value -> -100 <= value && value < 100));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getIntsInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Integer> values = r.getIntsInRange(10, -5, 5);
        assertTrue(values.stream().allMatch(value -> -5 <= value && value <= 5));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getLong(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong();
        assertTrue(Long.MIN_VALUE <= value && value <= Long.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong(100L);
        assertTrue(0L <= value && value < 100L);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongFromZeroBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong(0L);
        assertEquals(0L, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongFromNegativeBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong(-10L);
        assertEquals(0L, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong(-15L, -10L);
        assertTrue(-15L <= value && value < -10L);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongFromOriginWithLesserBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLong(-15L, -25L);
        assertEquals(0L, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLongInRange(-5L, 5L);
        assertTrue(-5L <= value && value <= 5L);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongInRangeAsPositive(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLongInRange(20L, 30L);
        assertTrue(20L <= value && value <= 30L);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongInRangeAsNegative(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLongInRange(-10L, -5L);
        assertTrue(-10L <= value && value <= -5L);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongInRangeWithEqualLimits(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLongInRange(-10L, -10L);
        assertEquals(-10L, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongInRangeWithLesserMax(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        long value = r.getLongInRange(-10L, -15L);
        assertEquals(0L, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getLongs(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Long> values = r.getLongs(10);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> Long.MIN_VALUE <= value && value <= Long.MAX_VALUE));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getLongsFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Long> values = r.getLongs(10, 10);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> 0L <= value && value < 10L));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getLongsFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Long> values = r.getLongs(10, -100L, 100L);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -100L <= value && value < 100L));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getLongsInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Long> values = r.getLongsInRange(10, -5L, 5L);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -5L <= value && value <= 5L));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getFloat(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat();
        assertTrue(0.0F <= value && value <= 1.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat(100.0F);
        assertTrue(0.0F <= value && value < 100.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatFromZeroBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat(0.0F);
        assertEquals(0.0F, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatFromNegativeBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat(-10.0F);
        assertEquals(0.0F, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat(-15.0F, -10.0F);
        assertTrue(-15.0F <= value && value < -10.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatFromOriginWithLesserBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloat(-15.0F, -25.0F);
        assertEquals(0.0F, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloatInRange(-5.0F, 5.0F);
        assertTrue(-5.0F <= value && value <= 5.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatInRangeAsPositive(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloatInRange(20.0F, 30.0F);
        assertTrue(20.0F <= value && value <= 30.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatInRangeAsNegative(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloatInRange(-10.0F, -5.0F);
        assertTrue(-10.0F <= value && value <= -5.0F);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatInRangeWithEqualLimits(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloatInRange(-10.0F, -10.0F);
        assertEquals(-10.0F, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloatInRangeWithLesserMax(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        float value = r.getFloatInRange(-10.0F, -15.0F);
        assertEquals(0.0F, r.getFloatInRange(-10.0F, -15.0F));
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getFloats(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Float> values = r.getFloats(10);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> 0.0F <= value && value <= 1.0F));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getFloatsFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Float> values = r.getFloats(10, 100.0F);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> 0.0F <= value && value < 100.0F));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getFloatsFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Float> values = r.getFloats(10, -15.0F, 10.0F);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -15.0F <= value && value < 10.0F));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getFloatsInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Float> values = r.getFloatsInRange(20, -2.5F, 11.25F);
        assertEquals(20, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -2.5F <= value && value <= 11.25F));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getDouble(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble();
        assertTrue(0.0D <= value && value <= 1.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(100.0D);
        assertTrue(0.0D <= value && value < 100.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleFromZeroBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(0.0D);
        assertEquals(0.0D, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleFromNegativeBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(-10.0D);
        assertEquals(0.0D, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(-15.0D, -10.0D);
        assertTrue(-15.0D <= value && value < -10.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleFromOriginWithLesserBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(-15.0D, -25.0D);
        assertEquals(0.0D, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDoubleInRange(-5.0D, 5.0D);
        assertTrue(-5.0D <= value && value <= 5.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleInRangeAsPositive(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDouble(20.0D, 30.0D);
        assertTrue(20.0D <= value && value <= 30.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleInRangeAsNegative(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDoubleInRange(-10.0D, -5.0D);
        assertTrue(-10.0D <= value && value <= -5.0D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleInRangeWithEqualLimits(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDoubleInRange(-10.0D, -10.0D);
        assertEquals(-10.0D, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubleInRangeWithLesserMax(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getDoubleInRange(-10.0D, -15.0D);
        assertEquals(0.0D, value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getDoubles(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Double> values = r.getDoubles(10);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> 0.0D <= value && value <= 1.0D));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getDoublesFromBound(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Double> values = r.getDoubles(10, 10.0D);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> 0.0D <= value && value < 10.0D));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getDoublesFromOrigin(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Double> values = r.getDoubles(10, -15.0D, 10.0D);
        assertEquals(10, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -15.0D <= value && value < 10.0D));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getDoublesInRange(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Double> values = r.getDoublesInRange(20, -2.5D, 11.25D);
        assertEquals(20, values.size());
        assertTrue(values.stream().allMatch(Objects::nonNull));
        assertTrue(values.stream().allMatch(value -> -2.5D <= value && value <= 11.25D));
        System.out.println(testInfo.getDisplayName() + " -> " + values);
    }

    @Test
    void getGaussian(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getGaussian();
        assertTrue(-Double.MAX_VALUE <= value && value <= Double.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getGaussianWithStdDeviation(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getGaussian(10.0D, 2.0D);
        assertTrue(-Double.MAX_VALUE <= value && value <= Double.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getGaussianWithConstraint(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        double value = r.getGaussian(10.0D, 2.0D, 11.5D);
        assertTrue(-Double.MAX_VALUE <= value && value <= 11.5D);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getGaussianInt(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getGaussianInt();
        assertTrue(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getGaussianIntWithStdDeviation(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getGaussianInt(10.0D, 2.0D);
        assertTrue(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getGaussianIntWithConstraint(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        int value = r.getGaussianInt(10.0D, 2.0D, 11);
        assertTrue(Integer.MIN_VALUE <= value && value <= 11);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getCharBasedOnWeight(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        WeightedChar[] values = new WeightedChar[]{new WeightedChar('a', 0.35D), new WeightedChar('b', 0.4D), new WeightedChar('c', 0.25D)};
        char value = r.getCharBasedOnWeight(values);
        assertNotNull(value);
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }

    @Test
    void getElementInArray(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        String[] values = {"test", "sample"};
        String element = r.getElement(values);
        assertTrue(List.of(values).contains(element));
        System.out.println(testInfo.getDisplayName() + " -> " + element);
    }

    @Test
    void getElementInList(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        List<Integer> values = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toList());
        int element = r.getElement(values);
        assertTrue(values.contains(element));
        System.out.println(testInfo.getDisplayName() + " -> " + element);
    }

    @Test
    void getElementInSet(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        Set<Integer> values = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toSet());
        int element = r.getElement(values);
        assertTrue(values.contains(element));
        System.out.println(testInfo.getDisplayName() + " -> " + element);
    }

    @Test
    void getElementInMap(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        Map<Integer, Integer> values = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> i));
        int element = r.getElement(values);
        assertNotNull(values.getOrDefault(element, null));
        System.out.println(testInfo.getDisplayName() + " -> " + element);
    }

    @Test
    void getEnum(TestInfo testInfo) {
        Randomizer r = new Randomizer();
        DayOfWeek value = r.getEnum(DayOfWeek.class);
        assertTrue(List.of(DayOfWeek.values()).contains(value));
        System.out.println(testInfo.getDisplayName() + " -> " + value);
    }
}
