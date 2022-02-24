public class Liturgy {
    public Hymn hymn1;
    public Hymn hymn2;
    public Hymn hymn3;

    public void getNewHymnNumbers() {
        this.hymn1 = new Hymn();
        this.hymn2 = new Hymn();
        this.hymn3 = new Hymn();
    }

    public boolean hasString(String stringNeeded) {
        int hymn1Number = this.hymn1.hymnNumber;
        int hymn2Number = this.hymn2.hymnNumber;
        int hymn3Number = this.hymn3.hymnNumber;

        String hymn1String = Integer.toString(hymn1Number);
        String hymn2String = Integer.toString(hymn2Number);
        String hymn3String = Integer.toString(hymn3Number);

        String totalString = "";
        totalString += hymn1String;
        totalString += hymn2String;
        totalString += hymn3String;

        char currentChar;
        String currentStringNumber;
        for (int i = 0; i < stringNeeded.length(); i++) {
            currentChar = stringNeeded.charAt(i);
            currentStringNumber = Character.toString(currentChar);

            if (totalString.contains(currentStringNumber)) {
                totalString = totalString.replaceFirst(currentStringNumber, "");
            } else if (!(totalString.contains(currentStringNumber))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String totalString = "";
        int hymn1Number = this.hymn1.hymnNumber;
        int hymn2Number = this.hymn2.hymnNumber;
        int hymn3Number = this.hymn3.hymnNumber;
        String hymn1String = Integer.toString(hymn1Number);
        String hymn2String = Integer.toString(hymn2Number);
        String hymn3String = Integer.toString(hymn3Number);
        totalString += hymn1String;
        totalString += hymn2String;
        totalString += hymn3String;
        return totalString;
    }
}
