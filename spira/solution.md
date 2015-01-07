# 整合解決方案

我們在 Geb 實作類別中指定變數 TestSetId 、 TestCaseId，來達到與 Spira Test 的對照關聯，並透過 Spira Test  API ，將資料匯入 Spira Test 中，目前使用的架構是 Geb + Spock，但 Spira Test  並沒有提供整合 Spock 的 API ，但有提供 JUnit 的。

變通的辦法是，Maven surefire 會產出符合 JUnit 的報表格式，而 Spira Test 有提供 [Extension For JUnit Automated Testing Framework](http://www.inflectra.com/Downloads/JUnitExtension.zip)，雖然並沒有完全符合我的需求，但其他部分可自行實作補足。

## 匯入第三方Jar檔

* 下載 [Extension For JUnit Automated Testing Framework jar](http://www.inflectra.com/Downloads/JUnitExtension.zip)
* 將 jar 檔，安裝到 local maven repository

```bash
mvn install:install-file -Dfile=/tmp/JUnitExtension.jar -DgroupId=com.inflectra.spiratest -DartifactId=junitextension -Dversion=3.0.0 -Dpackaging=jar
```

## Step

雖然 Geb 也是支援 Junit，但 Junit 並沒有 Step，而 Spock 有`@Stepwise`，所以我最終採用了 Spock ，但產出的 surefire 中，依然不會帶有 Step 的架構在，它還是把每個 Step 視為獨立的 Test Case，所以我重新改寫了 SpiraTestExecute 跟 SpiraTestListener，這兩個class。

## 資料轉換流程
Spock -> 透過 Maven surefire 產出 Junit 報表 -> 匯入至 Spira Test
