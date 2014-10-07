## iframe

### Page 物件
```
class DashboardPage extends Page{

    static content = {
        firewallPolicyFrame(page: FirewallPolicyPage){$('iframe')}
    }
}
```

### GebReportingSpec
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
