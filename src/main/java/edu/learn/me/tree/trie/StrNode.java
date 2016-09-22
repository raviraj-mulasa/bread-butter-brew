package edu.learn.me.tree.trie;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/1/16.
 *
 * https://www.topcoder.com/community/data-science/data-science-tutorials/using-tries/
 *
 * http://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems
 *
 * https://community.topcoder.com/stat?c=problem_statement&pm=3972
 *
 * http://sukwonoh.blogspot.com/2012/09/trie-application-ii-topcoder-srm-232.html
 *
 * http://algs4.cs.princeton.edu/lectures/52Tries.pdf
 *
 * https://www.cs.bu.edu/teaching/c/tree/trie/
 */
public final class StrNode {

    private String key                      = "";

    private Object value                    = null;

    private Map<String,StrNode> children   = Collections.emptyMap();


    StrNode() {
        this.key        = "";
        this.value      = null;
        this.children   = Collections.emptyMap();
    }

    StrNode(final String key, final Object value, final StrNode child) {
        this.key        = key;
        this.value      = value;
        this.addChild(child);
    }

    StrNode(final String key) {
        this(key, null);
    }

    StrNode(final String key, final Object value) {
        this(key, value, null);
    }


    public final String getKey() {
        return this.key;
    }

    public final Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public final StrNode getChild(final String keyAtChild) {
        if(this.children.isEmpty()) {
            return null;
        }
        return this.children.get(keyAtChild);
    }


    public void addChild(final StrNode child) {
        if(null != child) {
            if(this.children == Collections.EMPTY_MAP || null == this.children) {
                this.children = new HashMap<>();
            }
            this.children.put(child.getKey(), child);
        }
    }

    public final Map<String, StrNode> getChildren() {
        return this.children;
    }

    public final boolean deleteChild(final StrNode child) {
        if(null != child) {
            return this.children.remove(child.getKey(), child);
        }
        return false;
    }
}
