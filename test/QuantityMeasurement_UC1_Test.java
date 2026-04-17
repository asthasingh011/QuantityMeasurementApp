import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC1_Test {

    @Test
    void givenSameFeetValue_shouldReturnTrue() {
        QuantityMeasurement_UC1.Feet f1 = new QuantityMeasurement_UC1.Feet(1.0);
        QuantityMeasurement_UC1.Feet f2 = new QuantityMeasurement_UC1.Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void givenDifferentFeetValue_shouldReturnFalse() {
        QuantityMeasurement_UC1.Feet f1 = new QuantityMeasurement_UC1.Feet(1.0);
        QuantityMeasurement_UC1.Feet f2 = new QuantityMeasurement_UC1.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void givenNull_shouldReturnFalse() {
        QuantityMeasurement_UC1.Feet f1 = new QuantityMeasurement_UC1.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void givenSameReference_shouldReturnTrue() {
        QuantityMeasurement_UC1.Feet f1 = new QuantityMeasurement_UC1.Feet(1.0);

        assertTrue(f1.equals(f1));
    }

    @Test
    void givenDifferentType_shouldReturnFalse() {
        QuantityMeasurement_UC1.Feet f1 = new QuantityMeasurement_UC1.Feet(1.0);

        assertFalse(f1.equals("Not a Feet object"));
    }
}