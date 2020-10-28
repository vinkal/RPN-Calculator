import com.vinkal.rpn.exception.CalculatorException;
import com.vinkal.rpn.service.Calculator;
import com.vinkal.rpn.service.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.vinkal.rpn.service.Operator.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorTest {

    @Test
    public void testCalculate() throws CalculatorException {
        Random r = new Random();
        double firstOperand = r.nextDouble();
        double secondOperand = r.nextDouble();
        Assertions.assertEquals(secondOperand + firstOperand, ADDITION.calculate(firstOperand, secondOperand), 0);
        Assertions.assertEquals(secondOperand - firstOperand, SUBTRACTION.calculate(firstOperand, secondOperand), 0);
        Assertions.assertEquals(secondOperand * firstOperand, MULTIPLICATION.calculate(firstOperand, secondOperand), 0);
        Assertions.assertEquals(secondOperand / firstOperand, DIVISION.calculate(firstOperand, secondOperand), 0);
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(CalculatorException.class, () -> DIVISION.calculate(0.0, 0.0));
        String expectedMessage = "Cannot divide by 0.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInvalidOperations(){
        Exception exception = assertThrows(CalculatorException.class, () -> DIVISION.calculate(0.0, 0.0));
        String expectedMessage = "Cannot divide by 0.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
