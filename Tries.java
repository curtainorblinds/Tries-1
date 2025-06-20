/**
 * Leetcode 208. Implement Trie (Prefix Tree)
 * Link: https://leetcode.com/problems/implement-trie-prefix-tree/description/
 */
public class Tries {
    private TrieNode root;

    public Tries() {
        this.root = new TrieNode();
    }

    /**
     * TC: O(L) L -> Length of the word
     * SC: O(26L) for 1 word. If there are total N words worst case O(26NL). 26 can be ignored as constant
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            int idx = c - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }
        curr.isWord = true;
    }

    /**
     * TC: O(L) L -> Length of the word
     * SC: O(1) there is no extra auxiliary space being used to search on Trie
     */
    public boolean search(String word) {
        TrieNode lastNode = getTrieNodeWith(word);
        return lastNode != null && lastNode.isWord;
    }

    /**
     * TC: O(L) L -> Length of the word
     * SC: O(1) there is no extra auxiliary space being used to search on Trie
     */
    public boolean startsWith(String prefix) {
        return getTrieNodeWith(prefix) != null;
    }

    private TrieNode getTrieNodeWith(String prefix) {
        TrieNode curr = root;
        for(char c: prefix.toCharArray()) {
            int idx = c - 'a';

            if (curr.children[idx] == null) {
                return null;
            }

            curr = curr.children[idx];
        }
        return curr;
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
}
