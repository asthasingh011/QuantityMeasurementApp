public class QuantityMeasurement_UC10 {

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

    // ================= LENGTH ENUM =================
    enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public String getUnitName() {
            return name();
        }
    }

    // ================= WEIGHT ENUM =================
    enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public String getUnitName() {
            return name();
        }
    }

    // ================= GENERIC CLASS =================
    static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        // ================= EQUALITY =================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Quantity<?> other = (Quantity<?>) obj;

            // prevent cross-category comparison
            if (!this.unit.getClass().equals(other.unit.getClass())) return false;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // ================= CONVERSION =================
        public Quantity<U> convertTo(U targetUnit) {
            double base = this.toBase();
            double result = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(result, targetUnit);
        }

        // ================= ADD (DEFAULT UNIT) =================
        public Quantity<U> add(Quantity<U> other) {
            double sum = this.toBase() + other.toBase();
            double result = unit.convertFromBaseUnit(sum);
            return new Quantity<>(result, unit);
        }

        // ================= ADD (TARGET UNIT) =================
        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            double sum = this.toBase() + other.toBase();
            double result = targetUnit.convertFromBaseUnit(sum);
            return new Quantity<>(result, targetUnit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        // ===== LENGTH =====
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Length Equality: " + l1.equals(l2));
        System.out.println("Length Convert: " + l1.convertTo(LengthUnit.INCH));
        System.out.println("Length Add: " + l1.add(l2));
        System.out.println("Length Add (YARD): " + l1.add(l2, LengthUnit.YARD));

        // ===== WEIGHT =====
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + w1.equals(w2));
        System.out.println("Weight Convert: " + w1.convertTo(WeightUnit.GRAM));
        System.out.println("Weight Add: " + w1.add(w2));
        System.out.println("Weight Add (POUND): " + w1.add(w2, WeightUnit.POUND));

        // ===== CROSS CATEGORY =====
        System.out.println("Length vs Weight: " + l1.equals(w1)); // false
    }
}