package net.geekscore.problems;

/**
 * Created by ravirajmulasa on 8/13/17.
 */
public class ReverseWordsInSentence {

    public static void main(String[] args) {
        System.out.println(reverseSentence("Alice likes Bob"));
        System.out.println(reverseSentence("Bob likes Alice"));
        System.out.println(reverseSentence("Bob   Alice"));
        System.out.println(reverseSentence("Bob "));
        System.out.println(reverseSentence(" "));
        System.out.println(reverseSentence(""));
        System.out.println(reverseSentence(null));
    }



    private static final String reverseSentence(final String sentence) {
        if(sentence == null || sentence.length() == 0) return sentence;
        final char[] chars  = sentence.trim().toCharArray();
        reverse(chars, 0, chars.length-1);
        int i=0;
        for(int j=i; j<chars.length; j++){
            if(chars[j]==' '){
                reverse(chars, i, j-1);
                i=j+1;
            }
        }
        reverse(chars, i, chars.length-1);
        return new String(chars);
    }

    private static void reverse(char[] chars, int begin, int end) {
        while(begin<end) {
            if(chars[begin] != chars[end]) {
                swap(chars, begin, end);
            }
            begin++;
            end--;
        }
    }

    private static void swap(char[] chars, int i, int j) {
        final char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


}
