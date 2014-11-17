## 常見驗證條件
* 值是否相同？
* 特定元素存不存在？
* 直接詢問後端API

### 值是否相同？
```
<html>
<body>
	<div>aaa</div>
</body>
</html>

assert $('div').text() == 'aaa'
```

### 特定元素存不存在？
```
<html>
<body>
	<div></div>
	<div></div>
</body>
</html>

assert $('div').size() == 2
```

### 直接詢問後端API

```
go('/rest/instances/'+ id + '/usage?knot=1')
def result = $('pre').text().substring(1, $('pre').text().length()-1)
def info = new JsonSlurper().parseText(result)

assert info.cpu == 1
assert info.networkOut == 0
assert info.networkIn == 0
```

## 常見錯誤

```
geb.error.UnresolvablePropertyException: Unable to resolve searchModule as content for Page, or as a property on its Navigator context. Is searchModule a class you forgot to import?
	at geb.content.PageContentSupport.propertyMissing(PageContentSupport.groovy:64)
	at geb.Browser.propertyMissing(Browser.groovy:210)
	at geb.spock.GebSpec.propertyMissing(GebSpec.groovy:58)
```
* to XXXPage
* import
* element名稱打錯
