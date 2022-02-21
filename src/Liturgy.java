public class Liturgy {
    public Hymn hymn1;
    public Hymn hymn2;
    public Hymn hymn3;

    public void getNewHymnNumbers() {
        this.hymn1 = new Hymn();
        this.hymn2 = new Hymn();
        this.hymn3 = new Hymn();
    }

    public boolean hasNoRepeatingInts() {
        boolean[] usedInts = new boolean[10];
        int[] hymn1Array = convertHymnNumberToIntArray(hymn1);
        int[] hymn2Array = convertHymnNumberToIntArray(hymn2);
        int[] hymn3Array = convertHymnNumberToIntArray(hymn3);

        for (int currentInt : hymn1Array) {
            if (!(usedInts[currentInt])) {
                usedInts[currentInt] = true;
            } else if (usedInts[currentInt]) {
                return false;
            }
        }

        for (int currentInt : hymn2Array) {
            if (!(usedInts[currentInt])) {
                usedInts[currentInt] = true;
            } else if (usedInts[currentInt]) {
                return false;
            }
        }

        for (int currentInt : hymn3Array) {
            if (!(usedInts[currentInt])) {
                usedInts[currentInt] = true;
            } else if (usedInts[currentInt]) {
                return false;
            }
        }
        return true;
    }

    private int[] convertHymnNumberToIntArray(Hymn hymn) {
        Integer hymnNumber = hymn.hymnNumber;
        String hymnString = hymnNumber.toString();
        int[] hymnIntArray = new int[hymnString.length()];

        int currentInt;
        char dummyChar;
        for (int i = 0; i < hymnString.length(); i++) {
            dummyChar = hymnString.charAt(i);
            currentInt = Character.getNumericValue(dummyChar);
            hymnIntArray[i] = currentInt;
        }
        return hymnIntArray;
    }
}
