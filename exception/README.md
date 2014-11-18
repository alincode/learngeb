# 常見錯誤

## UnresolvablePropertyException

```
geb.error.UnresolvablePropertyException: Unable to resolve searchModule as content for Page, or as a property on its Navigator context. Is searchModule a class you forgot to import?
	at geb.content.PageContentSupport.propertyMissing(PageContentSupport.groovy:64)
	at geb.Browser.propertyMissing(Browser.groovy:210)
	at geb.spock.GebSpec.propertyMissing(GebSpec.groovy:58)
```

* 忘了把用到的其他類別import進來
* element名稱打錯
