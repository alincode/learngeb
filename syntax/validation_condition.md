# 常見驗證條件
* 值是否相同？
* 特定元素存不存在？
* 直接詢問後端API
* 補充常見實作錯誤

## 值是否相同？

**HTML Source Code**

```html
<html>
<body>
	<span>about text</span>
</body>
</html>
```

**Geb Sample Code**

```groovy
assert $('span').text() == 'about text'
```

## 特定元素存不存在？

**HTML Source Code**
```html
<html>
<body>
	<div></div>
	<div></div>
</body>
</html>
```

**Geb Sample Code**

```groovy
assert $('div').size() == 2
```

## 直接詢問後端API

**Geb Sample Code**

```groovy
    URL apiUrl = new URL('http://0.0.0.0:1234/myname.json')
    def data = new JsonSlurper().parseText(apiUrl.text)
    assert data.info.firstName == 'Liou'
```
