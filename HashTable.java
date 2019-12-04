public class HashTable {
    private Lexicon lexicon;

    // public HashTable() {
    // 	lexicon = new Lexicon(3);
    // }

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

    private int hash(String word, int i) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res += (int) c;
        }
        return (res + i * i) % lexicon.size();
    }

    public void hashInsert(String word) {
        int i = 0;
        // Not sure if i < lexicon.size() tries all positions
        while (i < lexicon.size() && !lexicon.isSlotEmpty(hash(word, i))) {
            i++;
        }
        int hashCode = hash(word, i);
        // TODO: expand the table if i == lexicon.size()
        if (hashCode < lexicon.size()) {
            lexicon.insert(word, hashCode);
        }
        
    }

    public void hashDelete(String word) {
        int i = 0;
        // Not sure if i < lexicon.size() tries all positions
        while (i < lexicon.size() && lexicon.isSlotEmpty(hash(word, i))) {
            i++;
        }
    }

    public boolean hashSearch(String word) {
        int i = 0;
        while (i < lexicon.size()) {
            int hashCode = hash(word, i);
            if (!lexicon.isSlotEmpty(hashCode) && lexicon.isWordExisted(word, hashCode)) {
                return true;
            }
            i++; // worst case can be linear, e.g. the word doesn't exist
        }
        return false;
    }

    public void hashBatch() {}

    public static void main(String args[]) 
    { 
        System.out.println("Hello, World"); 
    } 
}