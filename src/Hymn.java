public class Hymn {
    private static int range = (1100 - 10) + 1;
    public int hymnNumber = 0;

    public Hymn() {
        this.hymnNumber = (int) (Math.random() * range + 10);
    }

}
