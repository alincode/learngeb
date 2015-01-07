# 設定檔

* 在開始動手之前，來看看 [Configuration API](http://www.gebish.org/manual/current/api/geb/Configuration.html)
* 有哪些東西可以設定？找 set 開頭的 method
* 要怎麼設？

**setReportOnTestFailureOnly(boolean value)**

```groovy
reportOnTestFailureOnly = true
```

**setBaseUrl(def baseUrl)**

```groovy
baseUrl = "http://localhost:8080"
```

**setReportsDir([File](http://download.oracle.com/javase/6/docs/api/java/io/File.html) reportsDir)**

```groovy
reportsDir = new File("target/geb-reports")
```

**setDriver([WebDriver](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.html) driver)**

```groovy
driver = {
    def driver = new FirefoxDriver()
    driver.javascriptEnabled = true
    driver
}
```

**setUnexpectedPages(Collection pages)**

```groovy
unexpectedPages = [PageNotFoundPage, InternalServerErrorPage]
```

**setDefaultWaitTimeout(Double defaultWaitTimeout)**

**setDefaultWaitRetryInterval(Double defaultWaitRetryInterval)**

```groovy
waiting {
    timeout = 5
	retryInterval = 0.1
}
```

**setWaitPreset(String name, Double presetTimeout, Double presetRetryInterval)**

```groovy
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
```
