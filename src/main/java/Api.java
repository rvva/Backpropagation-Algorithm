import java.util.ArrayList;
import java.util.List;

public class Api {
    static double learningRate = 0.5;//ro
    static double alpha = 2;//Al
    public static double auxiliaryErrorRateN1 = 1;//d(k)
    public static double auxiliaryErrorRateN2 =1 ;//d(k)
    public static double auxiliaryErrorRateN =1 ;//d(k)

    private int stepCounter=0;

    private Neuron neuronN1;
    private Neuron neuronN2;
    private Neuron neuronN;
    private double error;
    private int output;
    private double sumErrorEpoch;
    List<Double> errorList;

    public Api() {
        this.neuronN1 = new Neuron();
        this.neuronN2 = new Neuron();
        this.neuronN = new Neuron();
        errorList=new ArrayList<>();
    }

    public double getSumErrorEpoch() {
        return sumErrorEpoch;
    }


    public void run(TrainingSet trainingSet) {

        stepCounter++;
        neuronN1.setTrainingSet(trainingSet);
        neuronN2.setTrainingSet(trainingSet);

        neuronN1 = neuronN1.initialize();
        neuronN2 = neuronN2.initialize();

        neuronN.setTrainingSet(new TrainingSet(neuronN1.getActivationFunction(),
                                                neuronN2.getActivationFunction(),
                                                neuronN1.getTrainingSet().getT()));
        neuronN = neuronN.initialize();

        auxiliaryErrorRateN = calculateAuxiliaryErrorRateForLayerOutput();
        auxiliaryErrorRateN1 = calculateAuxiliaryErrorRateForLayerHidden(1);
        auxiliaryErrorRateN2 = calculateAuxiliaryErrorRateForLayerHidden(2);
        error = calculateError();

        errorList.add(error);
        if(errorList.size()==4){
            sumErrorEpoch=epochError();
            errorList.clear();
        }

        System.out.println(viewResults(trainingSet));

        neuronN1.changeWeights(auxiliaryErrorRateN1);
        neuronN2.changeWeights(auxiliaryErrorRateN2);
        neuronN.changeWeights(auxiliaryErrorRateN);
    }

    public double epochError(){
        return errorList.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public String viewResults(TrainingSet trainingSet){

       String input = "Wejście: x_0 = " + trainingSet.getBias()
               + ", x_1 = " + trainingSet.getX1() + ", x_2 = " + trainingSet.getX2() + ", t = " + trainingSet.getT();

        String N1 = "Neuron 1: w_10 = " + neuronN1.getW0() + ", w_11 = " + neuronN1.getW1() +
                        ", w_12 = " + neuronN1.getW2() + ", s_1 = " + neuronN1.getSum() + ", y(1) = " +
                neuronN1.getActivationFunction() + ", f'(s_1) = " + neuronN1.getDerivativeOfAnActivationFunction() +
                ", d_1 = " + auxiliaryErrorRateN1;

        String N2 = "Neuron 2: w_20 = " + neuronN2.getW0() + ", w_21 = " + neuronN2.getW1() +
                ", w_22 = " + neuronN2.getW2() + ", s_2 = " + neuronN2.getSum() + ", y(2) = " +
                neuronN2.getActivationFunction() + ", f'(s_2) = " + neuronN2.getDerivativeOfAnActivationFunction() +
                ", d_2 = " + auxiliaryErrorRateN2;

        String inputN = "Wejście N: y_0 = " + neuronN.getTrainingSet().getBias() +  ", y_1 = " + neuronN.getTrainingSet().getX1()
                + ", y_2 = " + neuronN.getTrainingSet().getX2();


        String N = "Neuron wyjściowy: w_0 = " + neuronN.getW0() + ", w_1 = " + neuronN.getW1() +
                ", w_2 = " + neuronN.getW2() + ", s = " + neuronN.getSum() + ", y = " +
                neuronN.getActivationFunction() + ", f'(s) = " + neuronN.getDerivativeOfAnActivationFunction() +
                ", d = " + auxiliaryErrorRateN;

        String error = " Błąd = " +  this.error;
        String output = "Wyjście = " + calculateOutput();

        String epochError = "";
        if (stepCounter%4==0 && stepCounter!=0){
            epochError = " Błąd epoki: "+ sumErrorEpoch ;
        }

        return input +'\n' + N1 + '\n' + N2 + '\n' + inputN + '\n' + N + '\n' + output + error + epochError + "\t TEST: "+ test() ;
    }


    private String test() {
        return calculateOutput() == neuronN.trainingSet.getT() ? "OK" : "_";
    }

    private int calculateOutput() {
        return neuronN.getActivationFunction() > 0.5 ? 1 : 0;
    }

    private double calculateError() {
        return 0.5 * Math.pow(neuronN.getActivationFunction() - neuronN1.trainingSet.getT(), 2);
    }

    public double calculateAuxiliaryErrorRateForLayerOutput() {
        return neuronN.getDerivativeOfAnActivationFunction() * (neuronN1.trainingSet.getT() - neuronN.getActivationFunction());
    }

    public double calculateAuxiliaryErrorRateForLayerHidden(int a) {
        return a == 1 ? neuronN1.getDerivativeOfAnActivationFunction() *
                calculateAuxiliaryErrorRateForLayerOutput()
                * neuronN.getW1() : neuronN2.getDerivativeOfAnActivationFunction() *
                calculateAuxiliaryErrorRateForLayerOutput()
                * neuronN.getW2();
    }
}
