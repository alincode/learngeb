## 表單欄位


### 文字欄位

#### html
```
<form>
    <input type="text" name="address"/>
</form>
```

#### geb

設定值
```
$("input", name: "address").value("台北市XX區")
$("form").address = "台北市XX區"
```

取得值
```
$("input", name: "address").value()
```

判斷式
```
assert $("input", name: "address").value() == "台北市XX區"
assert $("form").address == "台北市XX區"
```
### 單選欄位(select)

#### html
```
<form>
    <select name="artist">
        <option value="1">Alin</option>
        <option value="2">lyhcode</option>
        <option value="3">Sean</option>
    </select>
</form>
```
#### geb

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

### 多選欄位(multiple select)

#### html
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
#### geb

// TODO: 待捕

### checkbox
HTML 表單中的 一個選擇框

#### html

```
<input type="checkbox" name="hobbies" value="music" />
<input type="checkbox" name="hobbies" value="chicken" />

<input type="checkbox" name="donate"/>
```

#### geb

設定值
```
// 全部選取
$('form').hobbies = true

// 全部取消
$('form').hobbies = false

$("checkbox", name: "hobbies").value("chicken")
$('form').hobbies = "chicken"

$("checkbox", name: "donate").value("true")
$("checkbox", name: "donate").value("false")
```

取得值
```
$('form').hobbies
```

判斷式
```
// 沒有一個欄位被勾選
assert $("input", name: "hobbies").value() == false

// 值包括chicken
$('form').hobbies.contains("chicken")
```

### radio
HTML 表單中的單選元素

#### html
```
<input type="radio" name="sex" value="male">
<input type="radio" name="sex" value="female">
```
#### geb

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

### 檔案欄位

#### html
```
<input type="file" name="csvFile">
```

#### geb

設定值
```
$("form").csvFile = "/profile/data.csv"
$('input', name : 'csvFile').value('/profile/data.csv')
```
