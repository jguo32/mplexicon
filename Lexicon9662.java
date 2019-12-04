/* YULIANG DONG cs610 9662 prp */

public class Lexicon9662 {
    private final static int CHARS_TO_SLOTS_RATIO = 15;

    private int[] T;
    private char[] A;

    private int currentIndex;


    public Lexicon9662(int m) {
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

    public void insert(String word, int hashCode) {
        T[hashCode] = currentIndex;
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

    public void delete(int hashCode) {
        if (hashCode >= T.length) {
            return;
        }
        T[hashCode] = -1;
    }

    public void prettyPrint() {
        System.out.println("\n\tT");
        for (int i = 0; i < T.length; i++) {
            if (T[i] == -1) {
                System.out.printf("%d:\n", i);
            } else {
                System.out.printf("%d: %d\n", i, T[i]);
            }
        }
        
        System.out.print("\nA: ");
        for (int i = 0; i < A.length; i++) {
            if (A[i] == (char) 0) {
                System.out.print("\\");
            } else {
                System.out.print(A[i] + "");
            }
        }
    }

    public void expand() {
        int[] newT = new int[T.length * 2];
        char[] newA = new char[A.length * 2];

        for (int i = 0; i < T.length; i++) {
            newT[i] = T[i];
        }

        for (int i = 0; i < A.length; i++) {
            newA[i] = A[i];
        }

        this.A = newA;
        this.T = newT;
    }
}