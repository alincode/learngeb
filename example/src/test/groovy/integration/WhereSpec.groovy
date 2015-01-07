package integration

import spock.lang.Specification

class WhereSpec extends Specification{

    def "兩個數值比大小運算"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        5 | 3 || 5
        1 | 9 || 9
        5 | 9 || 9
    }
}
