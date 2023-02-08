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
        StringUtils.repeat(null , "a", 1);
    }

    @Test(expected = NullPointerException.class)
    public void testThird() {
        StringUtils.repeat("a" , null, 2);
    }

    @Test
    public void testRepeat() {
        Assert.assertEquals("", StringUtils.repeat("e", "|", 0));
        Assert.assertEquals ("e|e|e", StringUtils.repeat("e", "|", 3) );
        Assert.assertEquals ("ABC,ABC", StringUtils.repeat("ABC" , ",", 2));
        Assert.assertEquals ("DBEDBE", StringUtils.repeat("DBE", "", 2));
        Assert.assertEquals ("DBE", StringUtils.repeat("DBE", ":", 1));
        Assert.assertEquals ("::", StringUtils.repeat("", ":", 3));

    }

    @Test
    public void keep(){
        Assert.assertEquals(null, StringUtils.keep (null , "*"));
        Assert.assertEquals("", StringUtils.keep("", "*"));
        Assert.assertEquals("", StringUtils.keep ("*" , null ));
        Assert.assertEquals("",StringUtils.keep ("*" , ""));
        Assert.assertEquals("hll", StringUtils.keep("hello", "hl"));
        Assert.assertEquals("ell", StringUtils.keep("hello", "le"));

    }

    @Test(expected = NullPointerException.class)
    public void testKeep() {
        StringUtils.keep(null, null);
    }
}