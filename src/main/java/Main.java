import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TrainingSet[] trainingSets = {new TrainingSet(0, 0, 1),
                new TrainingSet(0, 1, 0),
                new TrainingSet(1, 0, 0),
                new TrainingSet(1, 1, 1)};

        Api api = new Api();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj warunki stopu:");
        System.out.println("Wybierz 1 : maksymalna liczba iteracjii.");
        System.out.println("Wybierz 2 : maksymalny dopuszczlny błąd dla epoki (0; 1).");
        System.out.println("Wybierz 3 : oba warunki stopu.");

        int count = 0;

        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("Podaj liczbę maksymalnej iteracjii");
            int maxIteration=scanner.nextInt();
            while (count < maxIteration) {
                System.out.println("---------- krok : " + count + " -------------");
                api.run(trainingSets[count % 4]);
                count++;
            }

        }
        if (option == 2) {
            System.out.println("Podaj maksymalny dopuszczlny błąd dla epoki");
            double errorMax=scanner.nextDouble();
            double epochError = 1;
            while (epochError > errorMax) {
                System.out.println("---------- krok : " + count + " -------------");
                api.run(trainingSets[count % 4]);
                count++;

                if (count%4==0)
                    epochError = api.getSumErrorEpoch();
            }
        }
        if (option ==3 ){
            System.out.println("Podaj liczbę maksymalnej iteracjii");
            int maxIteration=scanner.nextInt();
            System.out.println("Podaj maksymalny dopuszczlny błąd dla epoki");
            double errorMax=scanner.nextDouble();

            double epochError =1;
            while (count < maxIteration && epochError > errorMax) {
                System.out.println("---------- krok : " + count + " -------------");
                api.run(trainingSets[count % 4]);
                count++;

                if (count%4==0)
                    epochError = api.getSumErrorEpoch();
            }
        }
    }
}




