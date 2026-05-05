import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurement_UC5_Test {

    @Test
    void feetToInches_shouldReturn12() {
        double result = QuantityMeasurement_UC3.convert(1.0,
                QuantityMeasurement_UC3.LengthUnit.FEET,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertEquals(12.0, result, 0.0001);
    }

    @Test
    void inchesToFeet_shouldReturn2() {
        double result = QuantityMeasurement_UC3.convert(24.0,
                QuantityMeasurement_UC3.LengthUnit.INCH,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertEquals(2.0, result, 0.0001);
    }

    @Test
    void yardsToFeet_shouldReturn9() {
        double result = QuantityMeasurement_UC3.convert(3.0,
                QuantityMeasurement_UC3.LengthUnit.YARD,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertEquals(9.0, result, 0.0001);
    }

    @Test
    void cmToInches_shouldReturnApprox1() {
        double result = QuantityMeasurement_UC3.convert(2.54,
                QuantityMeasurement_UC3.LengthUnit.CM,
                QuantityMeasurement_UC3.LengthUnit.INCH);

        assertEquals(1.0, result, 0.001);
    }

    @Test
    void sameUnit_shouldReturnSameValue() {
        double result = QuantityMeasurement_UC3.convert(5.0,
                QuantityMeasurement_UC3.LengthUnit.FEET,
                QuantityMeasurement_UC3.LengthUnit.FEET);

        assertEquals(5.0, result, 0.0001);
    }
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        // Validation
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // Convert to base unit (feet)
        double valueInFeet = source.toFeet(value);

        // Convert to target unit
        return valueInFeet / target.toFeet(1.0);
    }
}