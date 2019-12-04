/**
* 
* Author: Yuliang Dong
* Java version: "1.8.0_65"
* 
*/

import java.io.File; 
import java.util.Scanner; 

public class HashTable {
    private Lexicon lexicon;

    public void hashCreate(int m) {
        lexicon = new Lexicon(m);
    }

    public boolean hashEmpty() {
        return lexicon == null || lexicon.isEmpty();
    }

    public boolean hashFull() {
        return lexicon != null && !lexicon.isEmpty();
    }

    public void hashPrint() {}

    private static int hash(String word, int i, int size) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res += (int) c;
        }
        return (res + i * i) % size;
    }

    public void hashInsert(String word) {
        if (HashTable.hashSearch(this.lexicon, word)) {
            return;
        }
        int i = 0;
        int lexSize = lexicon.size();
        // Not sure if i < lexicon.size() tries all positions
        while (i < lexSize && !lexicon.isSlotEmpty(hash(word, i, lexSize))) {
            i++;
        }
        int hashCode = hash(word, i, lexSize);
        // TODO: expand the table if i == lexSize
        if (hashCode < lexSize) {
            lexicon.insert(word, hashCode);
        }
        
    }

    public void hashDelete(String word) {
        if (!HashTable.hashSearch(this.lexicon, word)) {
            return;
        }
        int i = 0;
        int lexSize = lexicon.size();
        while (i < lexSize) {
            int hashCode = hash(word, i, lexSize);
            if (!lexicon.isSlotEmpty(hashCode) && lexicon.isWordExisted(word, hashCode)) {
                lexicon.delete(hashCode);
            }
            i++;
        }
    }

    public static boolean hashSearch(Lexicon lexicon, String word) {
        int i = 0;
        int lexSize = lexicon.size();
        while (i < lexSize) {
            int hashCode = hash(word, i, lexSize);
            if (!lexicon.isSlotEmpty(hashCode) && lexicon.isWordExisted(word, hashCode)) {
                return true;
            }
            i++; // worst case can be linear, e.g. the word doesn't exist
        }
        return false;
    }

    public void hashBatch(String filename) throws Exception {
        File file = new File(filename); 
        Scanner sc = new Scanner(file); 

        while (sc.hasNextLine()) 
            System.out.println(sc.nextLine()); 
    }

    public static void main(String args[]) {
        HashTable h = new HashTable();
        h.hashCreate(10);
        h.hashInsert("abcedfsdf");
        h.hashInsert("a");
        h.hashInsert("b");
        h.hashInsert("c");
        h.hashInsert("a");
        try {
            h.hashBatch(args[0]);
        } catch (Exception e) {
            System.out.println("Batch processing error.");
        }
        
        // for (int i = 0; i < h.lexicon.getT().length; i++)
        //     System.out.println(h.lexicon.getT()[i]);

        // for (int i = 0; i < h.lexicon.getA().length; i++)
        //     System.out.println(h.lexicon.getA()[i]);

    } 
}