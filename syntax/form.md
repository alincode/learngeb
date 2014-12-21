# 表單處理

操作 HTML 的表單元件，是 Geb 程式重要的功能，測試表單功能是否正常運作，也是自動化測試需要關心的部分。

使用 Geb 存取表單欄位，可以有兩種寫法。

第一種是直接存取表單的某個欄位。

```groovy
$('input', name: 'username')
```

另外一種方式則是利用表單的「捷徑（shortcuts）」來存取欄位。

```groovy
$('form').username
```

因為表單元件的互動很常見，所以 Geb 提供「捷徑」的語法，讓程式碼看起來更簡潔。

通常我們偏好這個寫法，它看起來更簡潔，更符合 Groovy 程式寫作的風格。

先取得 Form Element，就可以對它所屬的欄位進行操作。

```groovy
def form = $('form')

form.username = 'manager'
form.password = 'xxxxx'
```

搭配 `with` 使用，「填寫表單」的程式碼，語意看起來更明確。

```groovy
$('form').with {
    username = 'superadmin'
    password = 'xxxxx'
}
```

常見的 HTML 表單元件如 input、select 等，以下是更詳細的介紹。

## 文字欄位（Text fields）

可以使用 `value()` 存取欄位內容。

**HTML Source Code**

```html
<form>
    <input type="text" name="address" />
</form>
```

**Geb Sample Code**

設定文字欄位內容。

```groovy
// 1.
$('input', name: 'address').value('台北市中正區重慶南路一段')

// 2.
$('form').address = '台北市中正區重慶南路一段'
```

取得值。

```groovy
// 1.
$('input', name: 'address').value()

// 2.
$('form').address
```

若要檢查文字欄位的內容可以這樣做。

```groovy
assert $('input', name: 'address').value() == '台北市中正區重慶南路一段'
assert $('form').address.startsWith('台北市')
assert $('form').address == '台北市中正區重慶南路一段'
```

## 核取方塊（Checkbox）

單選的 HTML 表單的核取方塊，只有選或不選兩種情況。

**HTML Source Code**

```html
<form>
    <!-- donate -->
    <input type="checkbox" name="donate"/>
</form>
```

**Geb Sample Code**

模擬滑鼠點擊。

```groovy
$('checkbox', name: 'donate').click()

// Or with shortcut
$('form').donate().click()
```

利用 `value()` 取得及設定勾選與否的狀態（boolean）。

```groovy
// boolean
def isDonate = $('checkbox', name: 'donate').value()

$('checkbox', name: 'donate').value(true)
$('checkbox', name: 'donate').value(false)
```

使用表單捷徑的語法範例。

```groovy
$('form').donate().click()
$('form').donate = true // Or false
isDonate = $('form').donate
```

## 多選核取方塊（Multi-select Checkbox）

用於可複選的核取方塊。

**HTML Source Code**

```html
<form>
    <!-- hobbies -->
    <input type="checkbox" name="hobbies" value="music" />
    <input type="checkbox" name="hobbies" value="chicken" />
</form>
```

**Geb Sample Code**

// return value

```groovy
// 全部選取
$('form').hobbies = true

// 全部取消
$('form').hobbies = false

$('checkbox', name: 'hobbies').value('chicken')
$('form').hobbies = 'chicken'
```

取得值

```groovy
$('form').hobbies
```

判斷式

```groovy
// 沒有一個欄位被勾選
assert $('input', name: 'hobbies').value() == false

// 檢查 chicken 是否被勾選
assert $('form').hobbies.contains('chicken')
```

## 選項按鈕（Radio）

HTML 表單中的單選按鈕。

**HTML Source Code**

```html
<form>
    <input type="radio" name="sex" value="male" />
    <input type="radio" name="sex" value="female" />
</form>
```
**Geb Sample Code**

設定值。

```groovy
$('form').sex = "female"
```

取得值。

```groovy
$('form').sex
```

判斷式。

```groovy
assert $('form').sex == 'female'
```

## 檔案欄位

**HTML Source Code**

```html
<form>
    <input type="file" name="csvFile" />
</form>
```

**Geb Sample Code**

設定值

```groovy
$('form').csvFile = '/tmp/data.csv'

// Or
$('input', name : 'csvFile').value('/tmp/data.csv')
```

## 下拉式選單（Drop-down Select）

**HTML Source Code**

```html
<form>
    <select name="artist">
        <option value="1">Alin</option>
        <option value="2">Kyle</option>
        <option value="3">Sean</option>
    </select>
</form>
```

**Geb Sample Code**

設定值

```groovy
$('input', name: 'artist').value('1') // Or 'Alin'

// Or in shortcut style
$('form').artist = 'Alin'
$('form').artist = 1 // same as '1'
```

取得值。

```groovy
$('form').artist
```

判斷式。

```groovy
assert $('form').artist == '1'
```
