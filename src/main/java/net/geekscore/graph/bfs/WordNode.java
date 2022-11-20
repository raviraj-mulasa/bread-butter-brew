package net.geekscore.graph.bfs;

class WordNode {
    String word;
    int steps;

    WordNode(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "WordNode{" +
                "word='" + word + '\'' +
                ", steps=" + steps +
                '}';
    }
}
