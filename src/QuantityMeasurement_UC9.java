public class QuantityMeasurement_UC9 {

    // ================= LENGTH ENUM =================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * toFeetFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toFeetFactor;
        }
    }

    // ================= WEIGHT ENUM =================
    enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double toKgFactor;

        WeightUnit(double toKgFactor) {
            this.toKgFactor = toKgFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * toKgFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toKgFactor;
        }
    }

    // ================= LENGTH CLASS =================
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double base = this.toBase();
            return new QuantityLength(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            double sum = this.toBase() + other.toBase();
            return new QuantityLength(unit.convertFromBaseUnit(sum), unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            double sum = this.toBase() + other.toBase();
            return new QuantityLength(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public String toString() {
            return "Length(" + value + ", " + unit + ")";
        }
    }

    // ================= WEIGHT CLASS =================
    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            double base = this.toBase();
            return new QuantityWeight(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(unit.convertFromBaseUnit(sum), unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public String toString() {
            return "Weight(" + value + ", " + unit + ")";
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        // ===== LENGTH =====
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Length Equality: " + l1.equals(l2));
        System.out.println("Length Convert: " + l1.convertTo(LengthUnit.INCH));
        System.out.println("Length Add: " + l1.add(l2));
        System.out.println("Length Add (YARD): " + l1.add(l2, LengthUnit.YARD));

        // ===== WEIGHT =====
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + w1.equals(w2));
        System.out.println("Weight Convert: " + w1.convertTo(WeightUnit.GRAM));
        System.out.println("Weight Add: " + w1.add(w2));
        System.out.println("Weight Add (POUND): " + w1.add(w2, WeightUnit.POUND));

        // ===== CROSS CATEGORY =====
        System.out.println("Length vs Weight: " + l1.equals(w1)); // false
    }
}