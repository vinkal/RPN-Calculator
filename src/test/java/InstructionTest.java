import com.vinkal.rpn.exception.CalculatorException;
import com.vinkal.rpn.service.Instruction;
import com.vinkal.rpn.service.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class InstructionTest {

    @Test
    public void testReverseOneOperandInstruction() throws CalculatorException {

        Operator mockOperator = Mockito.mock(Operator.class);
        when(mockOperator.getOperandsNumber()).thenReturn(1);

        Random r = new Random();
        Instruction instruction = new Instruction(mockOperator, r.nextDouble());

         Assertions.assertEquals(String.format("%s", mockOperator.getOpposite()), instruction.getReverseInstruction());
    }

    @Test
    public void testReverseTwoOperandInstruction() throws CalculatorException {

        Operator mockOperator = Mockito.mock(Operator.class);
        when(mockOperator.getOperandsNumber()).thenReturn(2);
        when(mockOperator.getOpposite()).thenReturn("-");

        Random r = new Random();
        double value = r.nextDouble();
        Instruction instruction = new Instruction(mockOperator, value);

         Assertions.assertEquals(
                String.format("%f %s %f", value, mockOperator.getOpposite(), value),
                instruction.getReverseInstruction()
        );
    }

    @Test
    public void testInvalidOperandsNumber() {
        Exception exception = assertThrows(CalculatorException.class, () -> {
            Operator mockOperator = Mockito.mock(Operator.class);
            when(mockOperator.getOperandsNumber()).thenReturn(0);

            Random r = new Random();
            Instruction instruction = new Instruction(mockOperator, r.nextDouble());
            instruction.getReverseInstruction();
        });
        String expectedMessage = "invalid operation for operator null";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
