import geb.driver.SauceLabsDriverFactory
import geb.driver.BrowserStackDriverFactory
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

waiting {
    timeout = 2
}

def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
if (sauceLabsBrowser) {
    driver = {
        def username = System.getenv("GEB_SAUCE_LABS_USER")
        assert username
        def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
        assert accessKey
        new SauceLabsDriverFactory().create(sauceLabsBrowser, username, accessKey)
    }
}

def browserStackBrowser = System.getProperty("geb.browserstack.browser")
if (browserStackBrowser) {
    driver = {
        def username = System.getenv("GEB_BROWSERSTACK_USERNAME")
        assert username
        def accessKey = System.getenv("GEB_BROWSERSTACK_AUTHKEY")
        assert accessKey
        new BrowserStackDriverFactory().create(browserStackBrowser, username, accessKey)
    }
}

environments {

    chrome {
        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }

    ie {
        driver = { new InternetExplorerDriver() }
    }

}

baseUrl = "http://www.gebish.org/"