package example

import geb.Page
import geb.spock.GebReportingSpec

/**
 * @see geb.js.AlertAndConfirmSupport
 */
class JavascriptSpec extends GebReportingSpec{

    def setup() {
        to JavascriptPage
    }

    def "withAlert"(){
        expect:
            withAlert(wait: true) { showAlert.click() } == "Hello World!"

    }

    def "withNoAlert"(){
        expect:
            withNoAlert { showAlert.click() }
//        java.lang.AssertionError: an unexpected browser alert() was raised (message: Hello World!)
    }

    def "withConfirm"(){
        expect:
            withConfirm(true) { showConfirm.click() } == "Do you like Geb?"
    }
}

class JavascriptPage extends Page{
    static url = "javascript.html"
    static content = {
        showAlert {$("input", name: "showAlert")}
        showConfirm {$("input", name: "showConfirm")}
    }
}
