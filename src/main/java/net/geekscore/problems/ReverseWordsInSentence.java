package net.geekscore.problems;

/**
 * Created by ravirajmulasa on 8/13/17.
 */
public class ReverseWordsInSentence {

    private static final char WHITE_SPACE_CHAR = ' ';

    public static void main(String[] args) {
        System.out.println(reverseSentence("Alice likes Bob"));
        System.out.println(reverseSentence("Bob likes Alice"));
        System.out.println(reverseSentence("Bob "));
        System.out.println(reverseSentence("Bob"));
        System.out.println(reverseSentence(" "));
        System.out.println(reverseSentence(""));
        System.out.println(reverseSentence(null));

    }

    private static final String reverseSentence(final String sentence) {
        if(null == sentence || sentence.length() == 0){
            return "";
        }
        if(sentence.indexOf(String.valueOf(WHITE_SPACE_CHAR), 0) == -1){
            // Only one word, just return sentence;
            return sentence;

        }
        final char[] sentenceCharArray  = sentence.toCharArray();
        // Reverse sentence first
        reverse(sentenceCharArray, 0, sentenceCharArray.length - 1);
        int startPos = 0;
        for (int whiteSpaceIdx = findCharIndex(sentenceCharArray, WHITE_SPACE_CHAR, startPos)
             ;whiteSpaceIdx != -1
                ;whiteSpaceIdx = findCharIndex(sentenceCharArray, WHITE_SPACE_CHAR, startPos)) {
//            Reverse each word until the last whitespace index.
            reverse(sentenceCharArray, startPos, whiteSpaceIdx - 1);
            startPos = (whiteSpaceIdx + 1) % sentenceCharArray.length;
        }
//        Reverse last word since last word does not end with whitespace.
        reverse(sentenceCharArray, startPos, sentenceCharArray.length - 1);
        return String.valueOf(sentenceCharArray);
    }


    private static final void reverse(final char[] charArray, final int startPos, int endPos){
        if(null == charArray || charArray.length ==0){
            return;
        }
        if(null != charArray && charArray.length == 1){
            return;
        }
        int beginIdx = startPos;
        int endIdx = endPos;
        while (endIdx - beginIdx > 0) {
            swap(charArray, beginIdx, endIdx);
            beginIdx += 1;
            endIdx -= 1;
        }
    }


    private static final int findCharIndex(final char[] charArray, final char charToFind, final int startPos){
        if(null == charArray || charArray.length ==0){
            return -1;
        }
        int beginIdx    = startPos;
        int endIdx      = charArray.length - 1;
        for (int i = beginIdx; i <= endIdx ; i++) {
            if((int) charArray[i] == (int) charToFind){
                return i;
            }
        }
        return -1;
    }


    private static void swap(final char[] sentenceCharArray, int i, int j){
        sentenceCharArray[i] = (char) (sentenceCharArray[i] ^ sentenceCharArray[j]);
        sentenceCharArray[j] = (char) (sentenceCharArray[j] ^ sentenceCharArray[i]);
        sentenceCharArray[i] = (char) (sentenceCharArray[i] ^ sentenceCharArray[j]);
    }


}
