import com.vinkal.rpn.service.Calculator;
import com.vinkal.rpn.exception.CalculatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void testAritmeticOperators() throws Exception {
        Calculator calculator = new Calculator();

        calculator.eval("5 2");
      Assertions.assertEquals(5, calculator.getValuesStack().get(0), 0);
      Assertions.assertEquals(2, calculator.getStackItem(1), 0);

        // substraction
        calculator = new Calculator();
        calculator.eval("5 2 -");
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(3, calculator.getStackItem(0), 0);
        calculator.eval("3 -");
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(0, calculator.getStackItem(0), 0);

        // negative
        calculator = new Calculator();
        calculator.eval("1 2 3 4 5 *");
        Assertions.assertEquals(4, calculator.getValuesStack().size());


        // division
        calculator = new Calculator();
        calculator.eval("7 12 2 /");
        Assertions.assertEquals(7, calculator.getStackItem(0), 0);
        Assertions.assertEquals(6, calculator.getStackItem(1), 0);
        calculator.eval("*");
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(42, calculator.getStackItem(0), 0);
        calculator.eval("4 /");
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(10.5, calculator.getStackItem(0), 0);

        //multiplication
        calculator = new Calculator();
        calculator.eval("1 2 3 4 5");
        calculator.eval("* * * *");
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(120, calculator.getStackItem(0), 0);

    }

    @Test
    public void testInsuficientParameters() {
        Calculator calculator = new Calculator();
        try {
            calculator.eval("1 2 3 * 5 + * * 6 5");
        } catch (CalculatorException e) {
            Assertions.assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
        }
        Assertions.assertEquals(1, calculator.getValuesStack().size());
        Assertions.assertEquals(11, calculator.getStackItem(0), 0);
    }

    @Test
    public void testOnlyOperators() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval("+ +");
        });
        String expectedMessage = "empty stack";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInvalidCharacters() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval("2 a +");
        });
        String expectedMessage = "invalid operator";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNoSpaces() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval("22+");
        });
        String expectedMessage = "empty stack";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNoSpaces2() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval("2 2+ 3");
        });
        String expectedMessage = "invalid operator";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval("1 0 /");
        });
        String expectedMessage = "Cannot divide by 0.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullInput() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.eval(null);
        });
        String expectedMessage = "Input cannot be null.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
