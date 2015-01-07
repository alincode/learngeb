# Maven

是一個自動化建置工具

## 核心概念

***POM (Project Object Model)***

以POM為基本最小基本單位，預設了大多數的建構配置，可透過XML語法去覆蓋或新增配置。

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1</version>
</project>
```

***Maven plugin***

Maven本質上是一個插件框架，它的核心並不執行任何具體的建構任務，所有任務都交給插件來完成，像編譯原始碼是由 `maven-compiler-plugin` 完成的。

***Maven 生命週期***

生命週期指項目的建構過程，是有特定具順序一系列的階段 (phase)，而一個階段就是建構過程中的一個步驟。

默認打包 ejb / ejb3 / jar / par / rar / war的建構生命週期
* process-resources 預 設resources:resources
* compile 預設 compiler:compile
* process-test-resources 預 設resources:testResources
* test-compile 預設 compiler:testCompile
* test 預設 surefire:test
* package 預設
    * ejb:ejb or ejb3:ejb3 or jar:jar or par:par or rar:rar or war:war
* install 預設 install:install
* deploy 預設 deploy:deploy

***Maven 依賴管理***

一個專案不會只用到自己的Jar檔，也會運用到其他Jar檔，Maven提供了很好的機制，去管理這些Jar之間錯綜複雜的關係。

***Maven repository***

在還沒有一個共用的 repository 之前，我們需要去每個官方網站下載 Jar 檔，但有集中管理的 `Maven repository` 後，你只要設定好 repository  的位置，大多可以直接從 repository，就可取得。

## 基本元素
* project：根元素，允許任何其他元素嵌入其中
* modelVersion：宣告這個POM檔，是符合哪個規格的版本。
* groupId：類似於 package 的功能，作為專案的群組識別名稱。
* artifactId：專案名稱
* version：專案版本
* properties：自定屬性
* dependencies：
* build：建構專案必要的資訊，
* repositories
