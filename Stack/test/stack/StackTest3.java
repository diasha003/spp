package stack;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackTest3 {
    Stack<String> stack;

    @Before
    public void setUpBeforeTest(){
        stack = new Stack<>();

    }

    @Test ( expected = NoSuchElementException.class )
    public void pop() {
       stack.pop();
    }

    @Test ( expected = NoSuchElementException.class )
    public void peek() {
        stack.peek();
    }
}
