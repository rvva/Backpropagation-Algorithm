public class TrainingSet {
    private final int bias;
    private final double x1;
    private final double x2;
    private final int t;//wartość worcowa

    public TrainingSet(double x1, double x2, int t) {
        this.bias = 1;
        this.x1 = x1;
        this.x2 = x2;
        this.t = t;
    }


    public int getT() {
        return t;
    }

    public int getBias() {
        return bias;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }
}
