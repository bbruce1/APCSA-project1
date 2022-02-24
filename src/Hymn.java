public class Hymn {
    private static final int range = (1100 - 10) + 1;
    public int hymnNumber;

    public Hymn() {
        this.hymnNumber = (int) (Math.random() * range + 10);
    }

}
