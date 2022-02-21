public class Tester {
    public static void main(String[] args) {
        int numberOfTrues = 0;
        Liturgy lit = new Liturgy();
        double simulations = 10000.0;

        for (int i = 0; i < simulations; i++) {
            lit.getNewHymnNumbers();
            if (lit.hasNoRepeatingInts()) {
                numberOfTrues++;
            }
        }
        System.out.println("Number of liturgies with no repeating ints: " + numberOfTrues);
        System.out.println("Percent chance: " + numberOfTrues / simulations + "%");
    }
}
