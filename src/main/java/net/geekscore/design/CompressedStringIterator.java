package net.geekscore.design;

/**
 *  Design and implement a data structure for a compressed string iterator. It should support the
 *  following operations: next and hasNext.
 *
 *  The given compressed string will be in the form of each letter followed by a positive integer
 *  representing the number of this letter existing in the original uncompressed string.
 *
 *  next() - if the original string still has uncompressed characters, return the next letter;
 *  Otherwise return a white space.
 *  hasNext() - Judge whether there is any letter needs to be uncompressed.
 *
 *  Note:
 *  Please remember to RESET your class variables declared in StringIterator,
 *  as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 *  Example:
 *
 *  StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *  iterator.next(); // return 'L'
 *  iterator.next(); // return 'e'
 *  iterator.next(); // return 'e'
 *  iterator.next(); // return 't'
 *  iterator.next(); // return 'C'
 *  iterator.next(); // return 'o'
 *  iterator.next(); // return 'd'
 *  iterator.hasNext(); // return true
 *  iterator.next(); // return 'e'
 *  iterator.hasNext(); // return false
 *  iterator.next(); // return ' '
 */
public class CompressedStringIterator {

    private static class StringIterator {

        private char[] chars = new char[0];
        private int curr = -1;

        private StringIterator(String compressedString) {
            if(compressedString != null && compressedString.length() > 0) {
                this.chars = compressedString.toCharArray();
            }
            this.curr =0;
        }


        private char next() {
            char ch2Return =  ' ';
            if(this.hasNext())  {
                ch2Return = this.chars[this.curr];
                // Decrement count
                int i = this.curr+1;
                int count = 0;
                while (i < this.chars.length && Character.isDigit(this.chars[i])) {
                    count = count * 10 + (this.chars[i] - '0');
                    this.chars[i++] = '0';
                }
                if(--count <= 0) {
                    this.curr = i;
                } else {
                    while (count > 0) {
                        this.chars[i-1] = (char) (count%10 + '0');
                        count /= 10;
                        i--;
                    }
                }
            }
            return ch2Return;
        }

        private boolean hasNext() {
            return this.chars.length - this.curr > 0;
        }

    }

    public static void main(String[] args) {
        final StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
        System.out.println(iterator.next()); // return 'L'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 't'
        System.out.println(iterator.next()); // return 'C'
        System.out.println(iterator.next()); // return 'o'
        System.out.println(iterator.next()); // return 'd'
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.hasNext()); // return false
        System.out.println(iterator.next()); // return ' '

        System.out.println("---------------");

        final StringIterator iterator1 = new StringIterator("L10e2t1C1o1d1e11");
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.hasNext()); // return true
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.hasNext()); // return true
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'L'
        System.out.println(iterator1.next()); // return 'e'
        System.out.println(iterator1.next()); // return 'e'
        System.out.println(iterator1.next()); // return 't'


        System.out.println("---------------");

        final StringIterator iterator2 = new StringIterator("L");
        System.out.println(iterator2.next()); // return 'L'
        System.out.println(iterator2.hasNext()); // return false
        System.out.println(iterator2.next()); // return ' '

    }
}
