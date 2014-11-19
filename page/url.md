# Page URL
* url 由 ( baseUrl + 路徑) 所組成
* 例如http://www.gebish.org/manual/current/all.html
    * baseUrl 是 http://www.gebish.org/
    * 路徑是 manual/current/all.html
Page Object 的靜態屬性 url ，我們可以只寫路徑，也可以寫 完整的url。

## 範例一

**Geb Sample Code**

```groovy
import geb.*

class ExamplePage extends Page {
    static url = "manual/current/all.html"
}
```

```
Browser.drive("http://www.gebish.org/") {
    to ExamplePage
}
```

**實際完整路徑**

http://www.gebish.org/manual/current/all.html

## 範例

有帶變數的路徑

**Geb Sample Code**

```groovy
import geb.*

class IronmanPage extends Page {
    static url = "ironman7/app/profile"
}
```

```groovy
Browser.drive("http://ithelp.ithome.com.tw/) {
    to IronmanPage, 20092025, dev
}
```

**實際完整路徑**

http://ithelp.ithome.com.tw/ironman7/app/profile/20092025/dev

## 範例三

物件化的路徑

**Geb Sample Code**

```groovy
class FacebookPerson {
    String nickname
}
```

```groovy
class FacebookPage extends Page{
    static url = ""

    String convertToPath(FacebookPerson person) {
        person.nickname.toString()
    }
}
```

```groovy
def newPerson = new FacebookPerson(nickame: "ShabuComeShabuGo")

Browser.drive {
    go "https://www.facebook.com/"
    to FacebookPage, newPerson
}
```

**實際完整路徑**

https://www.facebook.com/ShabuComeShabuGo
