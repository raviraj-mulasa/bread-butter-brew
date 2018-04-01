package net.geekscore.tree.trie;

/**
 * Created by ravirajmulasa on 9/20/16.
 */
public interface ITrie {
    void put(final String key, final Object value);
    Object get(final String key);
    void delete(final String key);
    boolean contains(final String key);
    TrieNode root();
}
