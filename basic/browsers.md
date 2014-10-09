## 瀏覽器基本操作指令

`Browser.drive` 開啟一個 DSL 程式區塊，在這個區塊中可以使用 Geb 提供的指令操作瀏覽器。在本節中介紹的瀏覽器操作指令，都必須撰寫在 `drive` 的區塊中。

```
Browser.drive {
    // Geb DSL here!!!
}
```

使用「`go`」命令瀏覽器開啟一個網址，這個範例以 CodeData 網站為例。

```
    go 'http://www.codedata.com.tw/'
```

在開啟一個網頁畫面時，Geb 會等待網頁主要內容都載入完畢，然後才執行下一行指令。為了確保指定的 URL 被正確打開，而且載入的網頁是我們期望的頁面，最基本的檢核就是先比對網頁標題。在 DSL 區塊中使用 title 變數可以取得網頁標題，它是一個字串（String）型態的變數資料。

例如 CodeData 首頁的標題以「CodeData」文字結尾，我們使用字串的 `endsWith` 方法就能檢查載入頁面的標題內容。

```
    println title
    assert title.endsWith('CodeData')
```

如果希望 Geb 的任務完成後，就關閉瀏覽器視窗結束程式，可以使用「`close()`」指令。但是在 Groovy Console 下執行 Geb 程式時，並不建議將瀏覽器視窗關閉，因為這麼做會使得 Selenium WebDriver 無法再次執行任務，需要重新打開 Groovy Console 才能繼續執行 Geb 程式。

```
    close()
```

另一個結束指令為「`quit()`」，它不僅關閉瀏覽器視窗，也會終止 WebDriver 的執行。

```
    quit()
```

對於排程執行或是在終端機下執行的 Geb 程式，需要將瀏覽器視窗關閉，才能讓 Groovy 程式停止執行返回 Shell。

```
Browser.drive {
    go 'http://www.codedata.com.tw/'

    // 執行一些自動化操作

    close()
}
```
