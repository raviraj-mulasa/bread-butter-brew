package edu.learn.me.tree.trie;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 9/20/16.
 */
public final class TrieImplTest {

    @Test
    public void testPut() {

        final ITrie trie    = new TrieImpl();
//        trie.put("authorize" , 1);
//        trie.put("author" , 2);
//        trie.put("authority" , 3);

        trie.put("ate" , 1);
        trie.put("axe" , 2);
        trie.put("att" , 3);

        int x = 0;

    }

    @Test
    public void testGet() {

        final ITrie trie    = new TrieImpl();
//        trie.put("authorize" , 1);
//        trie.put("author" , 2);
//        trie.put("authority" , 3);

        trie.put("ate" , 1);
        trie.put("axe" , 2);
        trie.put("att" , 3);

        Integer attVal  = (Integer) trie.get("att");
        Assert.assertTrue(attVal == 3);

        Integer val  = (Integer) trie.get("a23");
        Assert.assertNull(val);

    }
}
