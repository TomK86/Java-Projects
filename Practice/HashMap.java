/**
 * HashMap.java
 * Implementation of a very simple hash map using only arrays.
 */

import java.io.*;
import java.util.*;

public class HashMap<K,V> {

  private class HashEntry<K,V> {
    private K key;
    private V value;

    public HashEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

  private final static int TABLE_SIZE = 256;

  private HashEntry<K,V>[] table;
  private int len;

  public HashMap() {
    table = new HashEntry[TABLE_SIZE+1];
    len = 0;
  }

  public V get(K key) {
    HashEntry<K,V> entry = table[hash(key)];
    return entry != null ? entry.getValue() : null;
  }

  public void put(K key, V value) {
    int hash = hash(key);
    if (table[hash] == null) {
      table[hash] = new HashEntry<K,V>(key, value);
      len++;
    } else {
      table[hash].setValue(value);
    }
  }

  public V remove(K key) {
    int hash = hash(key);
    if (table[hash] != null) {
      V value = table[hash].getValue();
      table[hash] = null;
      len--;
      return value;
    }
    return null;
  }

  public boolean containsKey(K key) {
    return table[hash(key)] != null;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public int size() {
    return len;
  }

  private int hash(K key) {
    if (key == null) {
      return TABLE_SIZE;
    }
    int hash = key % TABLE_SIZE;
    while (table[hash] != null && table[hash].getKey() != key) {
      hash = (hash + 1) % TABLE_SIZE;
    }
    return hash;
  }

}