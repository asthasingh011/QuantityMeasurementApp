public class QuantityMeasurement {

    public static class QuantityLength_UC8 {

        private final double value;
        private final LengthUnit unit;

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

        // Convert to base unit (feet)
        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        // ================= EQUALITY (UC1–UC4) =================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // ================= UC5: CONVERSION =================
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = this.toBase();
            double converted = targetUnit.convertFromBaseUnit(base);

            return new QuantityLength(converted, targetUnit);
        }

        // ================= UC6: ADDITION (DEFAULT UNIT) =================
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumBase = this.toBase() + other.toBase();
            double result = this.unit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, this.unit);
        }

        // ================= UC7: ADDITION (TARGET UNIT) =================
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumBase = this.toBase() + other.toBase();
            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        // UC1–UC4: Equality
        System.out.println("Equality: " + q1.equals(q2));

        // UC5: Conversion
        System.out.println("Convert to INCH: " + q1.convertTo(LengthUnit.INCH));

        // UC6: Addition (default unit)
        System.out.println("Add (default unit): " + q1.add(q2));

        // UC7: Addition (target unit)
        System.out.println("Add (target YARD): " + q1.add(q2, LengthUnit.YARD));
    }
}