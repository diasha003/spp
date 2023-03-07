package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest1 {

    Stack<String> stack;


    @Before
    public void setUpBeforeTest(){
        stack = new Stack<>();
        stack.push("200");
        stack.push("800");
        stack.push("900");
    }


    @Test
    public void pop() {
        assertEquals("900", stack.pop());
    }

    @Test
    public void peek() {
        assertEquals("900", stack.peek());
    }

    @Test
    public void testToString() {
        assertEquals("900-800-200", stack.toString());
    }


}
