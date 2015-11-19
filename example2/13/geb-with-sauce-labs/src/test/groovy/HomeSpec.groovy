import geb.spock.GebReportingSpec

class HomeSpec extends GebReportingSpec {

    def "check Highlights"() {

        given: "I'm at the home page"
        to HomePage

        when: "I click highlights tab"
        crossLink.click(CrossBrowserPage)

        then: "check correct link"
        heading.text() == "Cross Browser Automation"
    }

}