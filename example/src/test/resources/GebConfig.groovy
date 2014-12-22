import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities

baseUrl = 'http://localhost:1234/'

driver = { new FirefoxDriver() }

waiting {
    presets {
        verySlow {
            timeout = 1800
            retryInterval = 300
        }
        slow {
            timeout = 60
            retryInterval = 10
        }
        normal {
            timeout = 10
            retryInterval = 1
        }
        quick {
            timeout = 5
            retryInterval = 1
        }
    }
}

environments {

    chrome {
        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver()}
    }

    phantomjs {
        DesiredCapabilities caps = new DesiredCapabilities(
                'phantomjs.cli.args': [
                        '--ignore-ssl-errors=true',
                        '--ssl-protocol=any',
                ] as String[],
        )
        PhantomJSDriverService.createDefaultService(caps)
        driver = {new PhantomJSDriver(caps)}
    }
}