package TEST;

public class Utils {
    public static boolean isWithinBounds(int x, int y, int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
