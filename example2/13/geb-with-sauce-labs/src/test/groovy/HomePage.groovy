import geb.Page

class HomePage extends Page {

    static at = { title == "Geb - Very Groovy Browser Automation" }

    static content = {
        crossLink { waitFor { $('.crossbrowser a') } }
    }
}