public class QuantityMeasurement_UC1 {

    // Inner class for Feet
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            // same reference
            if (this == obj) return true;

            // null check
            if (obj == null) return false;

            // type check
            if (this.getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            // compare double safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method to test manually
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Are equal? " + f1.equals(f2));
    }
}