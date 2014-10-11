## Page URL
* url 由 ( baseUrl + 路徑) 所組成
* 例如http://www.gebish.org/manual/current/all.html
    * baseUrl 是 http://www.gebish.org/
    * 路徑是 manual/current/all.html
再 Page Object 的靜態屬性 url ，我們可以只寫路徑，也可以寫 url。

### 範例一
```
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
url = http://www.gebish.org/manual/current/all.html

### 範例二：有帶變數的路徑
```
import geb.*

class IronmanPage extends Page {
    static url = "ironman7/app/profile"
}
```

```
Browser.drive("http://ithelp.ithome.com.tw/) {
    to IronmanPage, 20092025, dev
}
```
url = http://ithelp.ithome.com.tw/ironman7/app/profile/20092025/dev

### 範例三：物件化的路徑
```
class FacebookPerson {
    String nickname
}
```

```
class FacebookPage {
    static url = ""

    String convertToPath(FacebookPerson person) {
        person.nickname.toString()
    }
}
```

```
def newPerson = new FacebookPerson(nickame: "ShabuComeShabuGo")

Browser.drive {
    go "https://www.facebook.com/"
    to FacebookPage, newPerson
}
```
url = https://www.facebook.com/ShabuComeShabuGo
