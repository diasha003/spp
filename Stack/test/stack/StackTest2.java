package stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StackTest2 {
    Stack<String> stack;

    @Before
    public void setUpBeforeTest(){
        stack = new Stack<>();

    }

    @Test
    public void isEmpty() {
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, stack.size());
    }

    @Test
    public void push() {
        stack.push("500");
        assertEquals("500", stack.pop());
    }

    @Test
    public void peek() {
        stack.push("555");
        assertEquals("555", stack.peek());
    }

    @Test
    public void testToString() {
        stack.push("555");
        stack.push("444");
        stack.push("333");
        assertEquals("333-444-555", stack.toString());
    }

}
