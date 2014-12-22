@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'firefox'

Browser.drive {
    def newPerson = new FacebookPerson(nickname: "ShabuComeShabuGo")
    to FacebookPage, newPerson
}.quit()

class FacebookPerson {
    String nickname
}

class FacebookPage extends Page{
    static url = "https://www.facebook.com/"

    String convertToPath(FacebookPerson person) {
        person.nickname.toString()
    }
}