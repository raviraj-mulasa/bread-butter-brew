package net.geekscore.tree.trie;

import org.junit.Assert;
import org.junit.Test;

public final class PrefixTrieImplTest {

    @Test
    public void testPut() {

        final ITrie trie    = new PrefixTreeImpl();
        trie.put("ate" , 1);
        trie.put("axe" , 2);
        trie.put("att" , 3);
        trie.put("xyzatt" , 4);
        trie.put("xyzaxy" , 30);

        Assert.assertTrue((Integer) trie.get("ate") == 1);
        Assert.assertTrue((Integer) trie.get("axe") == 2);
        Assert.assertTrue((Integer) trie.get("att") == 3);
        Assert.assertTrue((Integer) trie.get("xyzatt") == 4);
        Assert.assertTrue((Integer) trie.get("xyzaxy") == 30);
        Assert.assertTrue(trie.get("by") == null);



    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        final ITrie trie    = new PrefixTreeImpl();
        trie.put(null, 0);
    }

    @Test
    public void testGet() {

        final ITrie trie    = new PrefixTreeImpl();
        trie.put("ate", 1);
        trie.put("axe", 2);
        trie.put("att", 3);

        Integer attVal  = (Integer) trie.get("att");
        Assert.assertTrue(attVal == 3);

        Integer val  = (Integer) trie.get("a23");
        Assert.assertNull(val);

    }

    @Test
    public void testDelete() {

        final ITrie trie    = new PrefixTreeImpl();
        trie.put("authorize" , 1);
        trie.put("author", 2);
        trie.put("authority", 3);

        Integer attVal  = (Integer) trie.get("authority");
        Assert.assertTrue(attVal == 3);
        trie.delete("authority");
        attVal  = (Integer) trie.get("authority");
        Assert.assertNull(attVal);

        trie.put("authority", 3);
        Assert.assertTrue((Integer) trie.get("authority") == 3);
        Assert.assertTrue((Integer) trie.get("authorize") == 1);
        Assert.assertTrue((Integer) trie.get("author") == 2);

        attVal  = (Integer) trie.get("author");
        Assert.assertTrue(attVal == 2);
        trie.delete("author");
        attVal  = (Integer) trie.get("author");
        Assert.assertNull(attVal);

        Assert.assertTrue((Integer) trie.get("authorize") == 1);
        Assert.assertTrue((Integer) trie.get("authority") == 3);


        final ITrie trie1    = new PrefixTreeImpl();
        trie1.put("hat" , 1);
        trie1.put("hello" , 2);
        trie1.put("have" , 3);
        trie1.put("haven" , 4);
        trie1.put("happy" , 30);

        Assert.assertTrue((Integer) trie1.get("have") == 3);
        Assert.assertTrue((Integer) trie1.get("hat") == 1);
        Assert.assertTrue((Integer) trie1.get("hello") == 2);
        Assert.assertTrue((Integer) trie1.get("haven") == 4);
        Assert.assertTrue((Integer) trie1.get("happy") == 30);

        trie1.delete("have");
        attVal  = (Integer) trie.get("have");
        Assert.assertNull(attVal);

        trie1.delete("haven");
        attVal  = (Integer) trie.get("haven");
        Assert.assertNull(attVal);


    }

    @Test
    public void testContains() {

        final ITrie trie    = new PrefixTreeImpl();
        trie.put("she" , 0);
        trie.put("the", 5);
        trie.put("shores", 7);
        trie.put("shells", 3);
        trie.put("sells", 1);
        trie.put("sea", 6);
        trie.put("by", 4);

        Assert.assertFalse(trie.contains("authority"));

        Assert.assertTrue(trie.contains("by"));
        trie.delete("by");
        Assert.assertFalse(trie.contains("by"));

        Assert.assertTrue((Integer) trie.get("she") == 0);
        Assert.assertTrue((Integer) trie.get("the") == 5);
        Assert.assertTrue((Integer) trie.get("shores") == 7);
        Assert.assertTrue((Integer) trie.get("shells") == 3);
        Assert.assertTrue((Integer) trie.get("sells") == 1);
        Assert.assertTrue((Integer) trie.get("sea") == 6);
        Assert.assertTrue(trie.get("by") == null);


    }
}
