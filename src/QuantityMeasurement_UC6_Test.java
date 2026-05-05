import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC6_Test {

    @Test
    void feetPlusFeet_shouldReturnFeet() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(2.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, result.value, 0.0001);
    }

    @Test
    void feetPlusInch_shouldReturnFeet() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(12.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        var result = q1.add(q2);

        assertEquals(2.0, result.value, 0.0001);
    }

    @Test
    void inchPlusFeet_shouldReturnInch() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(12.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(24.0, result.value, 0.0001);
    }

    @Test
    void yardPlusFeet_shouldReturnYard() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.YARD);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(3.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(2.0, result.value, 0.0001);
    }
}

