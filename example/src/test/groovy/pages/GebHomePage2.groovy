package pages

import geb.Page

class GebHomePage2 extends Page {
    static at = { $('h1').last().text() == "Build Status" }
    static url = "http://www.gebish.org"
}