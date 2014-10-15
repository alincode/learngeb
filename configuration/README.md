# 設定檔
* 在開始動手之前，來看看 [Configuration API](http://www.gebish.org/manual/current/api/geb/Configuration.html)
* 有哪些東西可以設定？找set開頭的method
* 要怎麼設？

setReportOnTestFailureOnly(boolean value)

    reportOnTestFailureOnly = true

setBaseUrl(def baseUrl)

    baseUrl = "http://localhost:8080"

setReportsDir([File](http://download.oracle.com/javase/6/docs/api/java/io/File.html) reportsDir)

    reportsDir = new File("target/geb-reports")

setDriver([WebDriver](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.html) driver)

    driver = {
    	def driver = new FirefoxDriver()
    	driver.javascriptEnabled = true
    	driver
    }

setUnexpectedPages(Collection pages)

    unexpectedPages = [PageNotFoundPage, InternalServerErrorPage]

setDefaultWaitTimeout(Double defaultWaitTimeout)
setDefaultWaitRetryInterval(Double defaultWaitRetryInterval)

    waiting {
    	timeout = 5
    	retryInterval = 0.1
    }

setWaitPreset(String name, Double presetTimeout, Double presetRetryInterval)

    waiting {
    	presets {
    		slow {
    			timeout = 20
    			retryInterval = 1
    		}
    		quick {
    			timeout = 1
    		}
    	}
    }
