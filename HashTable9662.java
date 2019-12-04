/* YULIANG DONG cs610 9662 prp */
/*
*
* Java version: "1.8.0_65"
* 
*/

import java.io.File; 
import java.util.Scanner; 

public class HashTable9662 {
    private Lexicon9662 lexicon;

    private final static String INSERT = "10";
    private final static String DELETE = "11";
    private final static String SEARCH = "12";
    private final static String PRINT = "13";
    private final static String CREATE = "14";
    private final static String COMMENT = "15";

    public void hashCreate(int m) {
        lexicon = new Lexicon9662(m);
    }

    public boolean hashEmpty() {
        return lexicon == null || lexicon.isEmpty();
    }

    public boolean hashFull() {
        return lexicon != null && !lexicon.isEmpty();
    }

    public void hashPrint() {
        if (lexicon == null) {
            return;
        }
        lexicon.prettyPrint();
    }

    private static int hash(String word, int i, int size) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res += (int) c;
        }
        return (res + i * i) % size;
    }

    // Insert the word to the lexicon, no-op if the word already exists
    public void hashInsert(String word) {
        if (HashTable9662.hashSearch(this.lexicon, word) != -1) {
            return;
        }
        int i = 0;
        int lexSize = lexicon.size();
        // Not sure if i < lexicon.size() tries all positions
        while (i < lexSize && !lexicon.isSlotEmpty(hash(word, i, lexSize))) {
            i++;
        }
        if (i == lexSize) {
            lexicon.expand();
            lexSize = lexicon.size();
            while (i < lexSize && !lexicon.isSlotEmpty(hash(word, i, lexSize))) {
                i++;
            }
        }
        int hashCode = hash(word, i, lexSize);
        if (hashCode < lexSize) {
            lexicon.insert(word, hashCode);
        }
        
    }

    // Delete the given word from the lexicon, no-op if the word is not in the lexicon
    public void hashDelete(String word) {
        int i = HashTable9662.hashSearch(this.lexicon, word);
        if (i == -1) {
            return;
        }
        lexicon.delete(i);
    }

    // Returns the index of the word in the lexicon table, -1 if not exist
    public static int hashSearch(Lexicon9662 lexicon, String word) {
        if (lexicon == null) {
            return -1;
        }
        int i = 0;
        int lexSize = lexicon.size();
        while (i < lexSize) {
            int hashCode = hash(word, i, lexSize);
            if (!lexicon.isSlotEmpty(hashCode) && lexicon.isWordExisted(word, hashCode)) {
                return hashCode;
            }
            i++; // worst case can be linear, e.g. the word doesn't exist
        }
        return -1;
    }

    // Batch process commands from file
    public void hashBatch(String filename) throws Exception {
        File file = new File(filename); 
        Scanner sc = new Scanner(file); 

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            String[] ops = line.split(" ");
            if (ops[0].equals(CREATE)) {
                hashCreate(Integer.parseInt(ops[1]));
            } else if (ops[0].equals(INSERT)) {
                hashInsert(ops[1]);
            } else if (ops[0].equals(DELETE)) {
                hashDelete(ops[1]);
            } else if (ops[0].equals(SEARCH)) {
                int hashCode = hashSearch(lexicon, ops[1]);
                if (hashCode != -1) {
                    System.out.printf("%s\tfound at slot %d\n", ops[1], hashCode);
                } else {
                    System.out.printf("%s\tnot found\n", ops[1]);
                }
            } else if (ops[0].equals(PRINT)) {
                hashPrint();
            }
        }
            
    }

    public static void main(String args[]) {
        HashTable9662 h = new HashTable9662();
        
        try {
            h.hashBatch(args[0]);
        } catch (Exception e) {
            System.out.println("Batch processing error.");
            e.printStackTrace();
        }

    } 
}