public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(0.0328084);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert this unit → base unit (feet)
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // Convert from base unit (feet) → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}