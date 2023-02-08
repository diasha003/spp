package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SumTest {

    @Test
    public void accum() {

        // Arrange - подготовка
        int a= 6;
        int b = 8;
        int c = -100;
        int expected = -86;

        // Act - действия
        int sum = Sum.accum(a,b,c);

        // Assert - проверка
        Assert.assertEquals(expected, sum);

    }

    @Test
    public void accumLong() {
        // Arrange
        long a= 60000;
        long b = 80000;
        long c = -1667543822;
        long expected = -1667403822;

        // Act
        long sum = Sum.accumLong(a,b,c);

        // Assert
        Assert.assertEquals(expected, sum);

    }
}