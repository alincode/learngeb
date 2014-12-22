# Page URL
* url 由 ( baseUrl + 路徑) 所組成
* 例如http://www.gebish.org/manual/current/all.html
    * baseUrl 是 http://www.gebish.org/
    * 路徑是 manual/current/all.html
Page Object 的靜態屬性 url ，我們可以只寫路徑，也可以寫 完整的url。

## 範例一

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://www.gebish.org/'
    to ExamplePage
}.quit()

class ExamplePage extends Page {
    static url = "manual/current/all.html"
}
```

**實際完整路徑**

http://www.gebish.org/manual/current/all.html

## 範例二

有帶變數的路徑

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://ithelp.ithome.com.tw/'
    to IronmanPage, 20092025, 'dev'
}.quit()

class IronmanPage extends Page {
    static url = "ironman7/app/profile"
}
```

**實際完整路徑**

http://ithelp.ithome.com.tw/ironman7/app/profile/20092025/dev

## 範例三

物件化的路徑

**Geb Sample Code**

```groovy
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
```

**實際完整路徑**

https://www.facebook.com/ShabuComeShabuGo
