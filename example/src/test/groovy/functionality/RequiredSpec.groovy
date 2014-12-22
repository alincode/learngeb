package functionality

import geb.spock.GebReportingSpec
import pages.GebHomePage
import spock.lang.Stepwise

@Stepwise
class RequiredSpec extends GebReportingSpec{

    def "test required"(){
        when:
        to GebHomePage
        testRequired.click()

        then:
        true
    }

}
