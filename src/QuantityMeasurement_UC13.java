public class QuantityMeasurement_UC13 {

    // ================= INTERFACE =================
    interface IMeasurable {
        double getConversionFactor();

        default double convertToBaseUnit(double value) {
            return value * getConversionFactor();
        }

        default double convertFromBaseUnit(double baseValue) {
            return baseValue / getConversionFactor();
        }

        String getUnitName();
    }

    // ================= LENGTH =================
    enum LengthUnit implements IMeasurable {
        FEET(1.0), INCH(1.0 / 12.0), YARD(3.0), CM(0.0328084);

        private final double factor;

        LengthUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }
        public String getUnitName() { return name(); }
    }

    // ================= WEIGHT =================
    enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0), GRAM(0.001), POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }
        public String getUnitName() { return name(); }
    }

    // ================= VOLUME =================
    enum VolumeUnit implements IMeasurable {
        LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }
        public String getUnitName() { return name(); }
    }

    // ================= ARITHMETIC ENUM =================
    enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final java.util.function.DoubleBinaryOperator operation;

        ArithmeticOperation(java.util.function.DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    // ================= GENERIC CLASS =================
    static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        // ================= CENTRAL VALIDATION =================
        private void validate(Quantity<U> other, U targetUnit, boolean requireTarget) {
            if (other == null) throw new IllegalArgumentException("Null quantity");
            if (!this.unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException("Different measurement categories");

            if (requireTarget && targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");
        }

        // ================= CENTRAL ARITHMETIC =================
        private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
            double a = this.toBase();
            double b = other.toBase();
            return op.compute(a, b);
        }

        // ================= EQUALITY =================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Quantity<?> other = (Quantity<?>) obj;

            if (!this.unit.getClass().equals(other.unit.getClass())) return false;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // ================= CONVERSION =================
        public Quantity<U> convertTo(U targetUnit) {
            double base = this.toBase();
            return new Quantity<>(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        // ================= ADD =================
        public Quantity<U> add(Quantity<U> other) {
            validate(other, null, false);
            double result = performBaseArithmetic(other, ArithmeticOperation.ADD);
            return new Quantity<>(round(unit.convertFromBaseUnit(result)), unit);
        }

        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            validate(other, targetUnit, true);
            double result = performBaseArithmetic(other, ArithmeticOperation.ADD);
            return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
        }

        // ================= SUBTRACT =================
        public Quantity<U> subtract(Quantity<U> other) {
            validate(other, null, false);
            double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
            return new Quantity<>(round(unit.convertFromBaseUnit(result)), unit);
        }

        public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
            validate(other, targetUnit, true);
            double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
            return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
        }

        // ================= DIVIDE =================
        public double divide(Quantity<U> other) {
            validate(other, null, false);
            return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
        }

        // ================= ROUND =================
        private double round(double val) {
            return Math.round(val * 100.0) / 100.0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Add: " + l1.add(l2));
        System.out.println("Subtract: " + l1.subtract(l2));
        System.out.println("Divide: " + l1.divide(l2));
    }
}
