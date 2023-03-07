package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testOne() {
        StringUtils.repeat("e", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testTwo() {
        StringUtils.repeat(null , 1);
    }

    @Test
    public void testRepeat() {
        Assert.assertEquals ("", StringUtils.repeat("e", 0));
        Assert.assertEquals ("eee", StringUtils.repeat("e", 3));
        Assert.assertEquals ("ABCABC", StringUtils.repeat("ABC", 2));

    }

}