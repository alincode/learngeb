# Run Geb with Groovy Shell

使用 Groovy Shell 開始學習 Geb

Groovy Shell 是 REPL（Read–eval–print loop）的 command-line 工具，可以方便測試 Groovy 程式碼，每次輸入一行指令，按下 Enter 就能立即執行並看到結果。

在 Groovy Shell 輸入指令時，可以按下 Tab 列出某個 Package 下有哪些可用類別或是某個物件有哪些方法可以存取。

需要的軟體

* JDK
* Groovy
* Firefox 瀏覽器

執行 Groovy Shell

```bash
$ groovysh
```

測試，執行一段 println 程式。

```groovy
println 'Hello Geb'
```

執行結果。

```groovy
groovy:000> println "Hello Geb"
Hello Geb
===> null
```

使用 Grapes。

```groovy
import `groovy.grape.Grab`
```

引用 Geb 套件。

```groovy
Grape.grab(group: 'org.gebish', module: 'geb-core', version: '0.9.3')
```

引用 Selenium WebDriver 套件（FirefoxDriver）。

```groovy
Grape.grab(group: 'org.seleniumhq.selenium', module: 'selenium-firefox-driver', version: '2.43.1')
```

建立 Browser 物件

```groovy
browser = new geb.Browser()
```

開啟 Google 搜尋首頁。

```groovy
browser.go 'http://google.com'
```

執行後，Geb 會開啟一個 Firefox 瀏覽器視窗，然後打開 Google 搜尋首頁。

利用瀏覽器的除錯工具，找到表單的 `<form />` 標籤，觀察什麼可以用來當「唯一的選擇器」。

```html
<form class="tsf" action="/search" id="tsf" method="GET"
      name="f" onsubmit="return q.value!=''" role="search">
```

以這段表單 HTML 代碼的 `<form />` 元素，可用的選擇器（selector）。

* form.tsf
* form#tsf
* form[name=f]

使用 `id` 比較能確保找到正確的 DOM 節點（node）。

```groovy
form = browser.find('form#tsf')
```

使用 `size()` 方法，可以得知 `find()` 是否找到目標節點。以此例而言，執行 `form.size()` 會得到回傳值「`1`」。

```groovy
form.size()
```

Google 首頁輸入搜尋關鍵字的文字方塊，其 `<input />`標籤的原始碼如下。

```html
<input class="lst lst-tbb sbibps" id="lst-ib" maxlength="2048"
       name="q" autocomplete="off" title="搜尋" type="text" value="">
```

由於這個 `<input />`是 `<form />` 的表單欄位，Geb 提供很簡單的方式可以存取欄位內容。

```groovy
form.q = 'jcconf 2014'
```

這段程式碼執行後，可以看到瀏覽器在關鍵字的欄位貼入「jcconf 2014」文字內容。

接下來觀察「Google 搜尋」按鈕的原始碼，得知利用「btnK」可以找到此按鈕的 DOM 節點，使用選擇器「`input[name=btnK]`」。

```html
<input value="Google 搜尋" name="btnK" jsaction="sf.chk" type="submit">
```

利用 `find()` 方法找到此節點。

```groovy
btnK = form.find('input[name=btnK]')
```

按下搜尋按鈕。

```groovy
btnK.click()
```

可以看到瀏覽器顯示搜尋結果。

觀察搜尋結果的 HTML 原始碼，可以得知搜尋結果的網頁連結，被放在 `<h3 class="r"` 的節點下。

```html
<h3 class="r">
    <a ... href="http://twjug.kktix.cc/events/jcconf2014-cfp">
        JCConf Taiwan 2014 Call for Papers
    </a>
```

把搜尋結果的每個連結取出，並顯示網頁標題文字。

```groovy
browser.find('h3.r a').each {
    println it.text()
}
```

這段程式碼，示範如何將 Google 搜尋結果，轉換成 Markdown 格式的內容輸出。

```groovy
b.find('h3.r a').each {
    def title = it.text()
    def href = it.attr('href')
    println "* [${title}](${href})"
}
```

