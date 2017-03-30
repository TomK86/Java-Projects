/**
 * Trie.java
 * Implementation of a Trie-tree, a data structure that makes searching for matches
 * in a large dictionary much faster than a standard hash set.
 */

import java.io.*;
import java.utils.*;

public class Trie {

    private static final int TABLE_SIZE = 256;
    
    private int words;
    private int prefixes;
    private Trie[] edges;
    
    public Trie() {
        this.words = 0;
        this.prefixes = 0;
        this.edges = new Trie[TABLE_SIZE];
    }
    
    // This method will add a given string `word` to the dictionary
    public void addWord(String word) {
        if (word.isEmpty()) {
            words++;
        } else {
            prefixes++;
            int k = word.charAt(0);
            if (edges[k] == null) {
                edges[k] = new Trie();
            }
            edges[k].addWord(word.substring(1));
        }
    }
    
    // This method will count the number of words in the dictionary
    // that match exactly with a given string `word`
    public int countWords(String word) {
        int k = word.charAt(0);
        if (word.isEmpty()) {
            return words;
        } else if (edges[k] == null) {
            return 0;
        } else {
            return edges[k].countWords(word.substring(1));
        }
    }

    // This method will count the number of words in the dictionary
    // that have a given string `prefix` as a prefix
    public int countPrefixes(String prefix) {
        int k = prefix.charAt(0);
        if (prefix.isEmpty()) {
            return prefixes;
        } else if (edges[k] == null) {
            return 0;
        } else {
            return edges[k].countPrefixes(prefix.substring(1));
        }
    }
    
    // This method checks whether the dictionary contains the given
    // string `word` (returns true if it does, else false)
    public boolean containsWord(String word) {
        int k = word.charAt(0);
        if (word.isEmpty()) {
            return words > 0;
        } else if (edges[k] == null) {
            return false;
        } else {
            return edges[k].containsWord(word.substring(1));
        }
    }

    // This method checks whether the dictionary contains the given
    // string `prefix` as a prefix (returns true if it does, else false)
    public boolean containsPrefix(String prefix) {
        int k = prefix.charAt(0);
        if (prefix.isEmpty()) {
            return prefixes > 0;
        } else if (edges[k] == null) {
            return false;
        } else {
            return edges[k].containsPrefix(prefix.substring(1));
        }
    }
    
}