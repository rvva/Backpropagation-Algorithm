import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
    TrainingSet trainingSet;
    private double w0;
    private double w1;
    private double w2;
    private double sum;//s1(k)
    private double activationFunction;//y1(k)
    private double derivativeOfAnActivationFunction;//f`(s1(k))



    private Random random;

    public Neuron() {
        double upper = 2;
        double lower = -2;
       this.w0 = Math.random() * (upper - lower) + lower;;
       this.w1 = Math.random() * (upper - lower) + lower;;
       this.w2 = Math.random() * (upper - lower) + lower;;
    }


    public Neuron initialize() {
        sum = sumWeight();
        activationFunction = calculateActivationFunction();
        derivativeOfAnActivationFunction = derivativeOfAnActivationFunction();
        return this;
    }

    public double sumWeight() {
        return trainingSet.getBias() * w0 +
                trainingSet.getX1() * w1 +
                trainingSet.getX2() * w2;
    }

    public double calculateActivationFunction() {
        return 1 / (1 + Math.exp(-Api.alpha * sum));
    }

    public double derivativeOfAnActivationFunction() {
        return Api.alpha * activationFunction * (1 - activationFunction);
    }

    public void changeWeights(double auxiliaryErrorRate){
        setW0(w0+Api.learningRate*auxiliaryErrorRate*trainingSet.getBias());
        setW1(w1+Api.learningRate*auxiliaryErrorRate*trainingSet.getX1());
        setW2(w2+Api.learningRate*auxiliaryErrorRate*trainingSet.getX2());
    }

    public double getDerivativeOfAnActivationFunction() {
        return derivativeOfAnActivationFunction;
    }

    public void setDerivativeOfAnActivationFunction(double derivativeOfAnActivationFunction) {
        this.derivativeOfAnActivationFunction = derivativeOfAnActivationFunction;
    }

    public TrainingSet getTrainingSet() {
        return trainingSet;
    }

    public void setTrainingSet(TrainingSet trainingSet) {
        this.trainingSet = trainingSet;
    }

    public double getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(double activationFunction) {
        this.activationFunction = activationFunction;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }


    public double getW0() {
        return w0;
    }

    public void setW0(double w0) {
        this.w0 = w0;
    }

    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }


    @Override
    public String toString() {
        return "Weight{" +
                "trainingSet=" + trainingSet +
                ", w0=" + w0 +
                ", w1=" + w1 +
                ", w2=" + w2 +
                ", sum=" + sum +
                ", activationFunction=" + activationFunction +
                ", derivativeOfAnActivationFunction=" + derivativeOfAnActivationFunction +
                ", random=" + random +
                '}';
    }
}
