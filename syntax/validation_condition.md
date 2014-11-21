# 常見驗證條件
* 值是否相同？
* 特定元素存不存在？
* 直接詢問後端API
* 補充常見實作錯誤

## 值是否相同？

**HTML Source Code**

```
<html>
<body>
	<div>aaa</div>
</body>
</html>
```

**Geb Sample Code**

```
assert $('div').text() == 'aaa'
```

## 特定元素存不存在？

**HTML Source Code**
```
<html>
<body>
	<div></div>
	<div></div>
</body>
</html>
```

**Geb Sample Code**

```
assert $('div').size() == 2
```

## 直接詢問後端API

**Geb Sample Code**

```
go('/rest/instances/'+ id + '/usage?knot=1')
def result = $('pre').text().substring(1, $('pre').text().length()-1)
def info = new JsonSlurper().parseText(result)

assert info.cpu == 1
assert info.networkOut == 0
assert info.networkIn == 0
```
