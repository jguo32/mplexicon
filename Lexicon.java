public class Lexicon {
    private final static int CHARS_TO_SLOTS_RATIO = 15;

    private int[] T;
    private char[] A;

    private int currentIndex;


    public Lexicon(int m) {
        T = new int[m];
        A = new char[m * CHARS_TO_SLOTS_RATIO];

        currentIndex = 0;

        for (int i = 0; i < m; i++) {
            T[i] = -1;
        }

        for (int i = 0; i < m * CHARS_TO_SLOTS_RATIO; i++) {
            A[i] = ' ';
        }

    }

    public boolean isEmpty() {
        for (int i = 0; i < T.length; i++) {
            if (T[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return T.length;
    }

    public boolean isSlotEmpty(int i) {
        return i < T.length && T[i] == -1;
    }

    public void insert(String word, int i) {
        for (char c : word.toCharArray()) {
            A[currentIndex++] = c;
            if (currentIndex >= A.length) {
                // Expand A for more capacity
                break;
            }
        }
        if (currentIndex < A.length) {
            A[currentIndex] = (char) 0; // Is it the right way to set NUL?
            currentIndex++;
        }
    }

    public boolean isWordExisted(String word, int hashCode) {
        int i = T[hashCode];
        int j = 0;
        while (i < A.length && j < word.length()) {
            if (A[i] != word.charAt(j)) {
                return false;
            }
            i++; j++;
        }
        return true;
    }
}