package pages

import geb.Page

class GebHomePage extends Page {

    def value1 = 1
    def value2 = 1

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
        testRequired(required: false) { $('.alin')}

        theValue1(cache: true) { value1 }
        theValue2(cache: false) { value2 }
    }
}