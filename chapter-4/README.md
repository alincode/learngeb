# 控制元素的使用語法 #


## 單選欄位(select)
```
<select name="artist">
    <option value="1">Alin</option>
    <option value="2">lyhcode</option>
    <option value="3">Sean</option>
</select>
```
```
$("form").artist = "1"
$("form").artist = 2
$("form").artist = "Alin" 
```

## 多選欄位(multiple select)
```
<select name="meaf" multiple>
    <option value="1">beef</option>
    <option value="2">chicken</option>
    <option value="3">pork</option>
    <option value="4">fish</option>
    <option value="5">duck</option>
</select>
```

```
$("form").meaf = ["2", "3"]
$("form").meaf = [1, 4, 5] 
$("form").meaf = ["beef", "fish"] 
```

## checkbox
```
<input type="checkbox" name="meaf" value="beef" />
<input type="checkbox" name="meaf" value="chicken" />

$("checkbox", name: "meaf").value("chicken")
```

```
<input type="checkbox" name="donate"/>

$("checkbox", name: "donate").value("true")
$("checkbox", name: "donate").value("false")

```

## radio
```
<input type="radio" name="meaf" value="beef">

$("form").meaf = "beef"
```

## 設定日期時間
```
new Date().format('yyyy-MM-dd HH-mm')
```

## 上傳檔案
```
<input type="file" name="csvFile">

$("form").csvFile = "/profile/data.csv"
```

## iframe
```
class DashboardPage extends Page{

    static content = {
        firewallPolicyFrame(page: FirewallPolicyPage){$('iframe')}
    }
}
```

```
class FirewallFunctionSpec extends GebReportingSpec{

    def "create policy"(){
        when:
        withFrame(firewallPolicyFrame) {
            createPolicy()
        }
        firewallPolicyFrameOkButton.click()

        then:
        $('.item').size() > 0
    }
}	   
```
// TODO: 範例需改善

## 實際完整範例
// TODO: 待補
