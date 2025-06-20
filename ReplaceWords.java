import java.util.List;

/**
 * Leetcode 648. Replace Words
 * Link: https://leetcode.com/problems/replace-words/description/
 */
public class ReplaceWords {
    /**
     * Solution using Trie. Build trie using given dictionary, also include isWord
     * boolean class member to identify is word completes at that TrieNode. This will
     * take O(NL) time and space. N -> total N words in dictionary and L -> length of longest word
     *
     * Now iterate over the words in the sentence and check if a prefix for the current word exists
     * in the Trie. If yes, add prefix path only to the result otherwise add entire word.
     * If there are M words in the sentence this will take O(ML) to search their prefixes and O(ML)
     * auxiliary space to create string array of words in the sentence
     *
     * TC: O((M + N)L) SC: O((M + N)L)
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word: dictionary) {
            trie.insert(word);
        }

        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");

        for (String word: words) {
            TrieNode curr = trie.root;
            int i;
            boolean notFound = false;
            for (i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int idx = c - 'a';

                if (curr.children[idx] == null) {
                    notFound = true;
                    break;
                }

                result.append(c);
                curr = curr.children[idx];

                if (curr.isWord) {
                    break;
                }
            }

            if (notFound) {
                result.append(word.substring(i));
            }
            result.append(" ");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    class Trie {
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            for (char c: word.toCharArray()) {
                int idx = c - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];

                if (curr.isWord) { //shorter word is already present
                    break;
                }
            }
            curr.isWord = true;
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}
