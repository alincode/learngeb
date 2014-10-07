## Gradle、Spock、Geb 測試流程整合 ##

簡易的 Geb 測試程式只要用 Groovy Script 方式撰寫，但是對一個完整的 Web 專案測試而言，上百甚至上千行的 Script 程式碼，將會讓測試程式變得難以維護。在實務上我們必須搭配自動化專案建置工具與測試框架，讓 Geb 的測試程式也能為專案建置的一部份。

在實務經驗上可行的開發工具組合建議是：

* 搭配 Gradle 專案自動化建置
* 使用 Geb Page Objects 定義每個需要被測試的頁面
* 利用 Spock 或 JUnit 撰寫單元測試案例

我們以 Gradle 建置工具示範 Geb + Spock 的測試流程整合，以下是所需要的 `build.gradle` 設定範例。

```
apply plugin: 'groovy'

repositories {
    mavenCentral()
}

dependencies {
    groovy "org.codehaus.groovy:groovy-all:2.3.3"


    // spock-core
    testCompile "org.spockframework:spock-core:0.7-groovy-2.0"

    // geb, geb-spock
    testCompile "org.gebish:geb-core:0.9.3"
    testCompile "org.gebish:geb-spock:0.9.3"
    testCompile "org.seleniumhq.selenium:selenium-firefox-driver:2.42.0"
    testCompile "org.seleniumhq.selenium:selenium-support:2.42.0"
}
```

分別定義兩個 Page 物件，分別對應 Twitter 登入表單與主畫面。

範例：`src/test/groovy/LoginPage.groovy`（登入表單）

```
import geb.Page

class LoginPage extends Page {
    static url = "https://twitter.com/login"
    static at = { heading.text() == "登入 Twitter" }
    static content = {
        heading { $("h1") }
        loginForm { $("form.signin") }
        loginButton(to: DashboardPage) { $("button.submit") }
    }
}
```

範例：`src/test/groovy/DashboardPage.groovy`（主畫面）

```
import geb.Page

class DashboardPage extends Page {
    static at = { heading.text() == "推文" }
    static content = {
        heading(wait: true) { $("h2#content-main-heading") }
    }
}
```

使用 Spock 定義一個測試案例，驗證輸入正確的帳號密碼後顯示主畫面。

範例：`src/test/groovy/TwitterSpec.groovy`

```
import geb.Page
import geb.spock.GebSpec

class TwitterSpec extends GebSpec {
    def "login to dashboard section"() {
        given:
        to LoginPage

        when:
        $('input.js-username-field').value("username")
        $('input.js-password-field').value("password")

        and:
        loginButton.click()

        then:
        at DashboardPage
    }
}
```

執行 Gradle 的 test 任務時，就會執行 Geb 測試程式。

```
gradle test
```

測試結果也會整合在輸出報表。

![Gradle Report](images/gradle-geb-report-2.png)

整合到專案自動化建置的 Geb 測試程式，會成為專案自動化測試流程的一部份，在使用 Jenkins CI 持續整合時，也順帶完成瀏覽器自動化測試。

請參考本文附錄的[完整程式碼](https://github.com/lyhcode/GroovyTutorial/tree/master/src/09_GebWebTest/gradle-spock-geb)。

## 參考資源 ##

* [The Book of Geb](http://www.gebish.org/manual/current/)
* [Taming Functional Web Testing with Spock and Geb](http://www.infoq.com/presentations/testing-spock-geb) ([slides](http://qconlondon.com/dl/qcon-london-2013/slides/PeterNiederwieser_TamingFunctionalWebTestingWithSpockAndGeb.pdf))
