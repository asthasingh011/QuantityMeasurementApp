import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC2_Test {

    // FEET TESTS
    @Test
    void givenSameFeet_shouldReturnTrue() {
        assertTrue(QuantityMeasurement_UC2.compareFeet(1.0, 1.0));
    }

    @Test
    void givenDifferentFeet_shouldReturnFalse() {
        assertFalse(QuantityMeasurement_UC2.compareFeet(1.0, 2.0));
    }

    // INCH TESTS
    @Test
    void givenSameInch_shouldReturnTrue() {
        assertTrue(QuantityMeasurement_UC2.compareInch(1.0, 1.0));
    }

    @Test
    void givenDifferentInch_shouldReturnFalse() {
        assertFalse(QuantityMeasurement_UC2.compareInch(1.0, 2.0));
    }

    // NULL + TYPE CHECK (important concept)
    @Test
    void givenFeetAndNull_shouldReturnFalse() {
        QuantityMeasurement_UC2.Feet f = new QuantityMeasurement_UC2.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void givenInchAndDifferentType_shouldReturnFalse() {
        QuantityMeasurement_UC2.Inch i = new QuantityMeasurement_UC2.Inch(1.0);
        assertFalse(i.equals("Not Inch"));
    }
}