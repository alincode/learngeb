package pages

import geb.Page

class GebHomePage3 extends Page {
    static at = { waitFor { title.endsWith("Groovy Browser Automation") } }
    static url = "http://www.gebish.org"
}