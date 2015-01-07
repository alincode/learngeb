# Geb + Spock + Maven

將常更新的版號設定為自定變數  `propertie`，設定專案要使用的相依 `dependency` Jar 檔，例如 Groovy、Geb、Spock、Selenium 等，修改 `build` 的預設設定，針對 Geb 設定做細部調整，額外新增一個 oos snapshots 的 ` repository`，來取得在官方 repository 中無法拿到的一些 jar 檔。

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
