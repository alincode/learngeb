# 表單處理

使用 Geb 存取表單欄位，有兩種寫法。

第一種是直接存取表單的某個欄位。

```groovy
$('input', name: 'username')
```

另一種方式則是利用表單的捷徑（shortcuts）來存取欄位。

```groovy
$('form').username
```

因為表單元件的互動很常見，所以 Geb 提供捷徑的語法，讓程式碼看起來更簡潔。

搭配 `with` 使用，「填寫表單」的程式碼，語意看起來更明確。

```groovy
$('form').with {
    username = 'superadmin'
    password = 'xxxx'
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

設定欄位內容

```groovy
$('input', name: 'address').value('台北市中正區重慶南路一段')
```

或

```groovy
$('form').address = '台北市中正區重慶南路一段'
```

取得值

```groovy
$('input', name: 'address').value()
```

或

```groovy
$('form').address
```

判斷式

```groovy
assert $('input', name: 'address').value() == '台北市中正區重慶南路一段'
assert $('form').address.startsWith('台北市')
assert $('form').address == '台北市中正區重慶南路一段'
```

## 核取方塊（Checkbox）

HTML 表單的核取方塊。

**HTML Source Code**

```html
<!--hobbies-->
<input type="checkbox" name="hobbies" value="music" />
<input type="checkbox" name="hobbies" value="chicken" />

<!--donate-->
<input type="checkbox" name="donate"/>
```

**Geb Sample Code**

設定值

```groovy
// 全部選取
$('form').hobbies = true

// 全部取消
$('form').hobbies = false

$('checkbox', name: 'hobbies').value('chicken')
$('form').hobbies = 'chicken'

$("checkbox", name: "donate").value("true")
$("checkbox", name: "donate").value("false")
```

取得值

```groovy
$('form').hobbies
```

判斷式

```groovy
// 沒有一個欄位被勾選
assert $('input', name: 'hobbies').value() == false

// 值包括 chicken
assert $('form').hobbies.contains('chicken')
```

### radio
HTML 表單中的單選元素

**HTML Source Code**
```
<input type="radio" name="sex" value="male">
<input type="radio" name="sex" value="female">
```
**Geb Sample Code**

設定值
```
$("form").sex = "female"
```

取得值
```
$("form").sex
```

判斷式
```
assert $("form").sex == "female"
```

## 檔案欄位

**HTML Source Code**
```
<input type="file" name="csvFile">
```

**Geb Sample Code**

設定值
```
$("form").csvFile = "/profile/data.csv"
$('input', name : 'csvFile').value('/profile/data.csv')
```

## 單選欄位(select)

**HTML Source Code**
```
<form>
    <select name="artist">
        <option value="1">Alin</option>
        <option value="2">lyhcode</option>
        <option value="3">Sean</option>
    </select>
</form>
```
**Geb Sample Code**

設定值
```
$("input", name: "artist").value("1")
$("form").artist = "Alin"
$("form").artist = "1"
$("form").artist = 1
```

取得值
```
$("form").artist
```

判斷式
```
assert $("form").artist == "1"
```

## 多選欄位(multiple select)

**HTML Source Code**
```
<form>
    <select name="meaf" multiple>
        <option value="1">beef</option>
        <option value="2">chicken</option>
        <option value="3">pork</option>
        <option value="4">fish</option>
        <option value="5">duck</option>
    </select>
</form>
```
**Geb Sample Code**

// TODO: 待捕
