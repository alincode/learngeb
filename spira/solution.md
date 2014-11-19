# 整合解決方案

我們需要TestSetId、TestCaseId 這兩個變數，才能透過 Spira Test 提供的API，將資料匯入Spira Test中，目前使用的架構是Geb + Spock，但 Spira Test 並沒有提供整合Spock的API，但有提供JUnit的。

變通的辦法是，Maven surefire會產出符合JUnit的報表格式，而Spira Test有提供[Extension For JUnit Automated Testing Framework](http://www.inflectra.com/Downloads/JUnitExtension.zip)，雖然並沒有完全符合我的需求，但其他部分可自行實作補足。

## 匯入第三方Jar檔

* 下載 [Extension For JUnit Automated Testing Framework jar](http://www.inflectra.com/Downloads/JUnitExtension.zip)
* 將jar檔，安裝到local maven repository

```bash
mvn install:install-file -Dfile=/tmp/JUnitExtension.jar -DgroupId=com.inflectra.spiratest -DartifactId=junitextension -Dversion=3.0.0 -Dpackaging=jar
```

## Step

雖然Geb也是支援Junit，但Junit並沒有Step，而Spock有```@Stepwise```，所以我最終採用了Spock，但產出的surefire中，依然不會帶有Step的架構在，它還是把每個Step視為獨立的Test Case，所以我重新改寫了SpiraTestExecute跟SpiraTestListener，這兩個class。

## 資料轉換流程
Spock -> 透過Maven surefire產出Junit報表 -> 匯入至Spira Test
