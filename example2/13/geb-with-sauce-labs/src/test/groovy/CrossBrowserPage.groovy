import geb.Page

class CrossBrowserPage extends Page{

    static at = { waitFor {$('.selected a').text() == "Cross Browser" }}

    static url = 'crossbrowser'

    static content = {
        heading { $('#main h1').first()}
    }
}
