package net.geekscore.problems;

/**
 * Created by ravirajmulasa on 9/16/16.
 */
public final class RunLengthEncoding {

    public static void main(String[] args) {
        runLengthEncode();
        runLengthDecode();
    }

    private static void runLengthEncode() {
        System.out.println("---------Encode Rec -----------------");
        System.out.println(runLengthEncoderRec("aaaabcccaa".toCharArray(), 0, 1));
        System.out.println(runLengthEncoderRec("abc".toCharArray(), 0, 1));
        System.out.println(runLengthEncoderRec("a".toCharArray(), 0, 1));
        System.out.println(runLengthEncoderRec("\b\b\b".toCharArray(), 0, 1)); // 3 whitespaces
        System.out.println(runLengthEncoderRec("\t\t".toCharArray(), 0, 1)); // 2 tabs
        System.out.println(runLengthEncoderRec("3333".toCharArray(), 0, 1)); // 4 3's
        System.out.println(runLengthEncoderRec("22".toCharArray(), 0, 1)); // 2 2's
        System.out.println("----------Encode Iter ------------");
        System.out.println(runLengthEncoder("aaaabcccaa".toCharArray()));
        System.out.println(runLengthEncoder("abc".toCharArray()));
        System.out.println(runLengthEncoder("a".toCharArray()));
        System.out.println(runLengthEncoder("\b\b\b".toCharArray())); // 3 whitespaces
        System.out.println(runLengthEncoder("\t\t".toCharArray())); // 2 tabs
        System.out.println(runLengthEncoder("3333".toCharArray())); // 4 3's
        System.out.println(runLengthEncoder("22".toCharArray())); // 2 2's
    }
    private static void runLengthDecode() {
        System.out.println("--------- Decode -----------------");
        System.out.println(runLengthDecoder("4a1b3c2a"));
        System.out.println(runLengthDecoder("1a1b1c"));
        System.out.println(runLengthDecoder("1a"));
        System.out.println(runLengthDecoder("3\b"));
        System.out.println(runLengthDecoder("2\t"));
        System.out.println(runLengthDecoder("43"));
        System.out.println(runLengthDecoder("22"));
    }

    private static final String runLengthEncoderRec(final char[] chars, final int currCharIdx, final int currCharCount) {
        if(chars == null || chars.length == 0){
            return "";
        }
        final StringBuffer stringBuffer  = new StringBuffer();
        if(currCharIdx == chars.length - 1) {
            // Current char is last char
            stringBuffer.append(currCharCount);
            stringBuffer.append(chars[currCharIdx]);
        }else {
            final char currChar     = chars[currCharIdx];
            final int  nextCharIdx  = currCharIdx + 1;
            final char nextChar     = chars[nextCharIdx];
            if((int) nextChar == (int) currChar){
                stringBuffer.append(runLengthEncoderRec(chars, nextCharIdx, currCharCount + 1));
            } else {
                stringBuffer.append(currCharCount);
                stringBuffer.append(currChar);
                stringBuffer.append(runLengthEncoderRec(chars, nextCharIdx, 1));
            }
        }
        return stringBuffer.toString().trim();
    }


    private static final String runLengthEncoder(final char[] chars) {
        if(chars == null || chars.length == 0){
            return "";
        }
        final StringBuffer stringBuffer  = new StringBuffer();
        int currCharCnt = 1;
        for (int i = 0; i < chars.length; i++) {
            if(i ==  chars.length - 1){
                stringBuffer.append(currCharCnt);
                stringBuffer.append(chars[i]);
            } else {
                final char currChar = chars[i];
                final char nextChar = chars[i+1];
                if((int) nextChar == (int) currChar){
                    currCharCnt += 1;
                } else {
                    stringBuffer.append(currCharCnt);
                    stringBuffer.append(currChar);
                    currCharCnt = 1;
                }
            }
        }
        return stringBuffer.toString().trim();
    }

    private static final String runLengthDecoder(final String string2Decode) {
        if(null == string2Decode) {
            return "";
        }
        if(string2Decode.length() <= 1){
            return string2Decode;
        }
        final StringBuffer stringBuffer = new StringBuffer();
        final char[] chars = string2Decode.toCharArray();
        for(int i=0 ; i < chars.length - 1; i += 2){
            char _char = chars[i];
            if(isDigit(_char)){
                final int times2Repeat = (int) _char % (int) '0';
                _char =  chars[i+1];
                for (int j = 0; j < times2Repeat; j++) {
                    stringBuffer.append(_char);
                }
            }
        }
        return stringBuffer.toString().trim();
    }

    private static final boolean isDigit(char val) {
        final int intCharVal = (int) val;
        return (48 <= intCharVal && intCharVal <=  57);
    }
}
