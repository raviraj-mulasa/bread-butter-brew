package net.geekscore.recursion

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by ravirajmulasa on 6/10/17.
 */
class RecursionSpec extends Specification {



    @Unroll
    def "Sum of Digits for #n: #resultExpecetd"() {
        given:
            def sumDigits = new SumDigits()
        when:
            def result = sumDigits.sumDigits(n)
        then:
            result == resultExpecetd
        where:
        n || resultExpecetd
        0 || 0
        9 || 9
        126 || 9
        49 || 13
        12 || 3
    }

    @Unroll
    def "Recurring Sum of Digits for #n & #k: #resultExpecetd"() {
        given:
        def sumDigits = new SumDigits()
        when:
        def result = sumDigits.recSumDigits(n, k)
        then:
        result == resultExpecetd
        where:
        n || k || resultExpecetd
        148 || 3 || 3
        9875 || 1 || 2
        123 || 3 || 9
        14 || 1 || 5
        14 || 2 || 1
        9875 || 0 || 2
    }

    @Unroll
    def "No of 7s in #n: #resultExpecetd"() {
        given:
        def sumDigits = new SumDigits()
        when:
        def result = sumDigits.count7s(n)
        then:
        result == resultExpecetd
        where:
        n || resultExpecetd
        717 || 2
        7 || 1
        126 || 0
    }

    @Unroll
    def "No of 8s Special in #n: #resultExpecetd"() {
        given:
            def sumDigits = new SumDigits()
        when:
            def result = sumDigits.count8s(n)
        then:
            result == resultExpecetd
        where:
            n || resultExpecetd
            8 || 1
            818 || 2
            8818 || 4
    }


    @Unroll
    def "base:#base power:#n = #resultExpecetd"() {
        given:
            def sumDigits = new SumDigits()
        when:
            def result = sumDigits.power(base, n)
        then:
            result == resultExpecetd
        where:
            base || n || resultExpecetd
            3 || 1 || 3
            3 || 2 || 9
            3 || 3 || 27
            0 || 2 || 0
            3 || 0 || 1
            -1 || 2 || 1
            -1 || 3 || -1
    }

    @Unroll
    def "No.of 'x' in #str = #resultExpecetd"() {
        given:
            def sumDigits = new SumDigits()
        when:
            def result = sumDigits.countX(str)
        then:
            result == resultExpecetd
        where:
            str  || resultExpecetd
            "xxhixx"|| 4
            "xhixhix"|| 3
            "hi" ||  0
            "" ||  0
    }

    @Unroll
    def "No.of 'hi' in #str = #resultExpecetd"() {
        given:
        def sumDigits = new SumDigits()
        when:
        def result = sumDigits.countHi(str)
        then:
        result == resultExpecetd
        where:
        str  || resultExpecetd
        "xxhixx"|| 1
        "xhixhix"|| 2
        "hi" ||  1
        "" ||  0
    }

}
