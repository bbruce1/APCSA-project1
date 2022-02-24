import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        int simulations = getSimulationsInput();
        String stringNeeded = getStringInput();

        double[] meansAndSDs = runTotalSimulation(simulations, stringNeeded);
        printGraph(meansAndSDs);
    }

    public static void printDivider() {
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void printXDataSection() {
        System.out.println("|                    |    In 10000 Sims   |   In 100000 Sims   |   In 1000000 Sims  |");
    }

    public static void printGraph(double[] meansAndSDs) {
        printDivider();
        printXDataSection();
        printDivider();

        System.out.print("|        Mean        |");
        printCell(meansAndSDs[0]);
        printCell(meansAndSDs[1]);
        printCell(meansAndSDs[2]);
        System.out.println();
        printDivider();
        System.out.print("| Standard Deviation |");
        printCell(meansAndSDs[3]);
        printCell(meansAndSDs[4]);
        printCell(meansAndSDs[5]);
        System.out.println();
        printDivider();
        System.out.print("|   Percent Chance   |");
        printCell(meansAndSDs[6]);
        printCell(meansAndSDs[7]);
        printCell(meansAndSDs[8]);
        System.out.println();
        printDivider();

    }

    public static void printCell(double value) {
        final int SPACES_PER_CELL = 20;
        value *= 100;
        int intValue = (int) value;
        value = intValue / 100.0;

        if (Double.toString(value).length() % 2 == 0) {
            printSpace((SPACES_PER_CELL - Double.toString(value).length()) / 2);
            System.out.print(value);
            printSpace((SPACES_PER_CELL - Double.toString(value).length()) / 2);
            System.out.print("|");
        } else if (Double.toString(value).length() % 2 == 1) {
            printSpace((SPACES_PER_CELL - Double.toString(value).length()) / 2);
            System.out.print(value);
            printSpace((SPACES_PER_CELL - Double.toString(value).length()) / 2 + 1);
            System.out.print("|");
        }
    }

    public static void printSpace(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
    }


    public static double[] runTotalSimulation(int simulations, String stringNeeded) {
        double[] meansAndSDs = new double[9];

        double[] firstSimulationData = runMultipleSimulations(simulations, stringNeeded, 10000);
        double[] secondSimulationData = runMultipleSimulations(simulations, stringNeeded, 100000);
        double[] thirdSimulationData = runMultipleSimulations(simulations, stringNeeded, 1000000);

        // Get means
        meansAndSDs[0] = getMean(firstSimulationData, simulations);
        meansAndSDs[1] = getMean(secondSimulationData, simulations);
        meansAndSDs[2] = getMean(thirdSimulationData, simulations);

        // get SD
        meansAndSDs[3] = calculateSD(firstSimulationData);
        meansAndSDs[4] = calculateSD(secondSimulationData);
        meansAndSDs[5] = calculateSD(thirdSimulationData);

        // get percent chance
        meansAndSDs[6] = getPercentChance(firstSimulationData, 10000);
        meansAndSDs[7] = getPercentChance(secondSimulationData, 100000);
        meansAndSDs[8] = getPercentChance(thirdSimulationData, 1000000);

        return meansAndSDs;
    }

    public static double getPercentChance(double[] simulationData, int simulations) {
        int totalTrues = 0;
        double mean;
        for (double dummy : simulationData) {
            totalTrues += dummy;
        }
        mean = totalTrues / (double) (simulationData.length * simulations);
        return mean * 100;
    }

    public static double getMean(double[] simulationData, int simulations) {
        int totalInArray = 0;
        for (double dummy : simulationData) {
            totalInArray += dummy;
        }
        return (double) totalInArray / simulations;
    }

    public static double[] runMultipleSimulations(int simulations, String stringNeeded, int dataPoints) {
        double[] totalSimData = new double[simulations];

        for (int i = 0; i < simulations; i++) {
            totalSimData[i] = runSimulation(dataPoints, stringNeeded);
        }
        return totalSimData;
    }

    public static double runSimulation(int simulations, String stringNeeded) {
        int numberOfTrues = 0;
        Liturgy lit = new Liturgy();

        for (int i = 0; i < simulations; i++) {
            lit.getNewHymnNumbers();
            if (lit.hasString(stringNeeded)) {
                numberOfTrues++;
            }
        }
        return numberOfTrues;
    }

    public static int getSimulationsInput() {
        boolean valid = false;
        Scanner userInput = new Scanner(System.in);
        int simulations = 0;

        while (!valid) { // Loop to prompt user for value, then verify it is an integer
            System.out.print("Enter any positive integer for the number of simulations: ");
            String strInput = userInput.nextLine();
            try { // try to convert the string entered by user into an integer
                simulations = Integer.parseInt(strInput);
                valid = true;
            } catch(NumberFormatException e) { // If it does not convert return error and prompt again
                System.out.println("Error! Enter any positive INTEGER!");
                simulations = 1; // set simulations to one so it does not display the next if statement
            }
            if (simulations <= 0) { //check if the int is negative
                System.out.println("Error! Enter any POSITIVE integer!"); //if negative print error
                valid = false;
            }
        }
        return simulations;
    }

    public static String getStringInput() {
        boolean valid = false;
        Scanner userInput = new Scanner(System.in);
        long longStringNeeded = 0;


        while (!valid) { // Loop to prompt user for value, then verify it is an integer
            System.out.print("Enter any positive integer string to check each liturgy for (between 6 and 12 digits): ");
            String strInput = userInput.nextLine();
            char firstChar = strInput.charAt(0);
            if (firstChar == '0') {
                strInput = strInput.substring(1);
                strInput += Character.toString(firstChar);
            }
            try { // try to convert the string entered by user into an integer
                longStringNeeded = Long.parseLong(strInput);
                valid = true;
            } catch(NumberFormatException e) { // If it does not convert return error and prompt again
                System.out.println("Error! Enter any positive INTEGER!");
                longStringNeeded = 1; // set simulations to one so it does not display the next if statement
            }
            if (Long.toString(longStringNeeded).length() < 1 || Long.toString(longStringNeeded).length() > 12) {
                //check if the int is negative
                System.out.println("Error! Enter any integer BETWEEN 6 and 12 digits!"); //if negative print error
                valid = false;
            }
            if (longStringNeeded < 0) { //check if the int is negative
                System.out.println("Error! Enter any POSITIVE integer!"); //if negative print error
                valid = false;
            }
        }
        return Long.toString(longStringNeeded);
    }


    // NOT MINE see: https://www.programiz.com/java-programming/examples/standard-deviation
    public static double calculateSD(double[] arr) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = arr.length;

        for(double num : arr) {
            sum += num;
        }

        double mean = sum/length;

        for(double num : arr) {
            standardDeviation += Math.pow(num - mean, 2);
        }


        return Math.sqrt(standardDeviation/length);
    }
}
