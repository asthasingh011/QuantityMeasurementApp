public class QuantityMeasurement_UC2 {

    // Feet class (same as UC1)
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Inches class (NEW)
    static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;

            Inch other = (Inch) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Static methods (as per requirement)
    public static boolean compareFeet(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    public static boolean compareInch(double v1, double v2) {
        Inch i1 = new Inch(v1);
        Inch i2 = new Inch(v2);
        return i1.equals(i2);
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("Feet equal? " + compareFeet(1.0, 1.0));
        System.out.println("Inch equal? " + compareInch(1.0, 1.0));
    }
}