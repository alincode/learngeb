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

一個專案不會只用到自己的Jar檔，也會運用到其他Jar檔，Maven提供了很好的機制，去管理這些錯綜複雜的關係。

***Maven repository***

在還沒有一個共用的 repository 之前，我們需要去每個官方網站下載 Jar 檔，但有集中管理的 `Maven repository` 後，你只要設定好 repository 的位置，大多可以直接從 repository 就可取得。

## 基本元素
* project：根元素，允許任何其他元素嵌入其中
* modelVersion：宣告這個POM檔，是符合哪個規格的版本。
* groupId：類似於 package 的功能，作為專案的群組識別名稱。
* artifactId：專案名稱
* version：專案版本
* properties：自定屬性
* dependencies：
* build：建構專案必要的資訊，
* repositories：

## 完成範例

將常更新的版號設定為自定變數 (properties)，設定專案要使用的相依 Jar 檔，例如 Groovy、Geb、Spock、Selenium 等，修改 build 的預設設定，額外新增一個 oos snapshots 的 repository，來取得在官方 repository 中無法拿到的一些 jar 檔。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>idv.alin</groupId>
    <artifactId>GebTutorial</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <geb.version>0.10.0</geb.version>
        <selenium.version>2.44.0</selenium.version>
        <phantomjs.driver.version>1.1.0</phantomjs.driver.version>
        <spock.version>0.7-groovy-2.0</spock.version>
        <junit.version>4.8.1</junit.version>
        <groovy.version>2.3.6</groovy.version>
        <maven.surefire.version>2.17</maven.surefire.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy-all</artifactId>
            <version>${groovy.version}</version>
        </dependency>

        <!-- spock -->
        <dependency>
            <groupId>org.spockframework</groupId>
            <artifactId>spock-core</artifactId>
            <version>${spock.version}</version>
        </dependency>

        <!-- Geb -->
        <dependency>
            <groupId>org.gebish</groupId>
            <artifactId>geb-core</artifactId>
            <version>${geb.version}</version>
        </dependency>

        <dependency>
            <groupId>org.gebish</groupId>
            <artifactId>geb-spock</artifactId>
            <version>${geb.version}</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-support</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-server</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- browsers -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-firefox-driver</artifactId>
            <version>${selenium.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>${selenium.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-htmlunit-driver</artifactId>
            <version>${selenium.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.github.detro.ghostdriver</groupId>
            <artifactId>phantomjsdriver</artifactId>
            <version>${phantomjs.driver.version}</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.version}</version>
                <configuration>
                    <includes>
                        <include>*Spec.*</include>
                        <include>*/*Spec.*</include>
                    </includes>
                    <systemPropertyVariables>
                        <geb.env>chrome</geb.env>
                        <geb.build.reportsDir>target/test-reports/geb</geb.build.reportsDir>
                    </systemPropertyVariables>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.codehaus.gmaven</groupId>
                <artifactId>gmaven-plugin</artifactId>
                <version>1.5</version>
                <configuration>
                    <providerSelection>2.0</providerSelection>
                    <source/>
                    <debug>false</debug>
                    <verbose>true</verbose>
                    <stacktrace>true</stacktrace>
                    <defaultScriptExtension>.groovy</defaultScriptExtension>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>oos snapshots</id>
            <url>http://oss.sonatype.org/content/repositories/snapshots</url>
            <layout>default</layout>
        </repository>
    </repositories>
</project>
```
