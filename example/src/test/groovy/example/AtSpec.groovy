package example

import geb.spock.GebReportingSpec
import pages.CrossBrowserPage
import pages.GebHomePage

class AtSpec extends GebReportingSpec{

    def 'at example'(){
        to GebHomePage
        item1.click()

        expect:
        $('#main h1').first().text() == 'Cross Browser Automation'
    }

    def 'at example 2'(){
        to GebHomePage
        item1.click()

        expect:
        at CrossBrowserPage
    }

    def 'at example 3'(){
        to GebHomePage
        item1.click(CrossBrowserPage)

        expect:
        true
    }

    def 'at example 4'(){
        to CrossBrowserPage

        expect:
        true
    }

//    def 'at example 2'(){
//        to FrontPage
//        item1.click();
//        println($('#main h1').first().text())
//
//        expect:
//        at CrossBrowserPage
//    }
}