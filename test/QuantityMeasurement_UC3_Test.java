import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC3_Test {

    @Test
    void givenFeetToFeet_sameValue_shouldReturnTrue() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void givenInchToInch_sameValue_shouldReturnTrue() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void givenFeetAndInch_equivalent_shouldReturnTrue() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(12.0,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void givenDifferentValues_shouldReturnFalse() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);
        var q2 = new QuantityMeasurement_UC3.QuantityLength(2.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void givenNull_shouldReturnFalse() {
        var q1 = new QuantityMeasurement_UC3.QuantityLength(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }
}