public class QuantityMeasurement_UC3 {

    // ENUM for units
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    // Generic Quantity Class
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Convert both to base unit (feet)
        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        // Add
        double sumInFeet = thisInFeet + otherInFeet;

        // Convert back to THIS unit
        double resultValue = sumInFeet / this.unit.toFeet(1.0);

        return new QuantityLength(resultValue, this.unit);
    }

    // Main method
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Are equal? " + q1.equals(q2));
    }
}
