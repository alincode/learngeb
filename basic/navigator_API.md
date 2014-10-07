## Geb Navigator API ##

在撰寫瀏覽器自動化程式時，需要大量的 DOM 操作，正確找到一個 Element 才能對它做進一步的存取。在 Front-end 開發框架中，jQuery 已經提供一個多數 Web 開發者都已經熟悉的 Selector 機制，我們可以透過 Id 或 Class name 輕鬆找到一個目標的 Element。

以下是一些 jQuery 常見的 Selector 程式碼使用範例。

```
// jQuery Selector
$('div.errorMessage');
$('input#firstName');
$('button.confirm');
```

Geb 提供類似 jQuery Selector 的 Navigator API，讓開發者輕鬆找到一個 DOM 的 Element 物件。

尋找一個 `<div>` 及 `<div class="main">` 的語法，`$` 函數將會傳回一個 `Navigator` 物件。

```
// Geb Navigator
$("div")
$("div.main")
```

也可以利用 ID 尋找 Element。

```
$("#iframe1")
```

如果找到條件相符的 Element 有多項，則可以在第二個參數指定 0 取出第一個 Element；使用 `.first()` 也同樣可以傳回第一個項目。

```
$("div", 0)
$("div.main", 0)
$("div").first()
```

如果要找到 `<div title="section">` 的 Element，也可以這樣做。

```
$("div", title: "section")
$("div", 0, title: "section")
```

Navigator 找到的 Element 也可以進一步找到其上層，或繼續往它包含的子 Element 做搜尋。

```
$("table.books", 0).parent()
$("div.contents").find("table", cellspacing: '0')
```
