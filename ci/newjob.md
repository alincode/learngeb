## 新增作業

### 設定
* 設定作業名稱：auto-test
* 設定原始碼管理：SVN or Git

![jenkins set step1](jenkins1.png)
* 建構
    * Root POM: pom.xml
    * Goal 及選項: clean -Dgeb.env=phantomjs test

![jenkins set step2](jenkins2.png)

### 執行
* 點擊工作專案的"馬上建置"
