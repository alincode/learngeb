# 使用 IntelliJ IDEA 的好處

現在的 IDE 都很聰明，會自動比對是否有打錯字，你可否跟我一樣非常依賴這個功能？但要能自動比對，前提是 IDE 要能辨識的出來才能成立，這也是 Geb 官方推薦 IntelliJ IDEA 的原因。

## 可辨別出 `Browser` 的方法

只要類別是繼承 `GebSpec`，就可以辨識 [`Browser`](http://www.gebish.org/manual/current/api/geb/Browser.html) 的方法，像 `to` 和 `at` 就是 `Browser` 類別的方法。

**Geb Sample Code**

```groovy
class LoginWebSiteSpec extends GebReportingSpec {

    def "go to login page"(){
        when:
        to LoginPage

        then:
        at LoginPage
    }
}
```

## 可辨識出 Content DSL

只要是繼承 `Page` 和 `Module` 的類別，就可以自動辨識出 Content DSL 裡定義的屬性。例如下面範例的 privacyText 變數，在 IntelliJ IDEA 就可以被辨識出來。

**Geb Sample Code**

```groovy
class FrontPage extends Page{

    static content = {
        privacyText {$('.footer a[href$=privacy]').text()}
    }

    def checkLanguageZhTW() {
        waitFor { privacyText == "隱私" }
    }

}
```
