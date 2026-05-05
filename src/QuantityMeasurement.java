public class QuantityMeasurement {

    // ================= ENUM =================
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084); // 1 cm in feet

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    // ================= CLASS =================
    public static class QuantityLength {
        public final double value;
        public final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // ================= UC3 / UC4 =================
        private double toFeet() {
            return unit.toFeet(value);
        }

        // ================= UC1–UC4 EQUALITY =================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        // ================= UC5 CONVERSION =================
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (source == null || target == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            double valueInFeet = source.toFeet(value);
            return valueInFeet / target.toFeet(1.0);
        }

        // ================= UC6 ADDITION =================
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumInFeet = this.toFeet() + other.toFeet();

            double resultValue = sumInFeet / this.unit.toFeet(1.0);

            return new QuantityLength(resultValue, this.unit);
        }

        // ================= UC7 ADDITION WITH TARGET =================
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumInFeet = this.toFeet() + other.toFeet();

            double resultValue = sumInFeet / targetUnit.toFeet(1.0);

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        // UC1 / UC2 / UC3 / UC4
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equality: " + q1.equals(q2));

        // UC5
        double converted = QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        System.out.println("Conversion (1 ft to inch): " + converted);

        // UC6
        QuantityLength sum1 = q1.add(q2);
        System.out.println("Addition (default unit): " + sum1);

        // UC7
        QuantityLength sum2 = q1.add(q2, LengthUnit.YARD);
        System.out.println("Addition (target yard): " + sum2);
    }
}