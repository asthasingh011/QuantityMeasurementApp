import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC4_Test {

    @Test
    void yardToFeet_shouldBeEqual() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.YARD);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(3.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void yardToInch_shouldBeEqual() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.YARD);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(36.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void cmToInch_shouldBeEqual() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.CM);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(0.393701,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void cmToFeet_shouldNotBeEqual() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.CM);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }
}