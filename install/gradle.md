# 安裝 Gradle

Gradle 是專案自動化建置（build automation）工具，使用 Groovy DSL 語法定義建置流程，提供 Dependency Management 並以 Maven Repository 為 JARs 檔案來源。

這些教學可以幫助您更快認識 Gradle。

* 《[認識 Gradle](http://www.codedata.com.tw/java/understanding-gradle-1-ant/)》由 Java 社群知名的 qrtt1 作者撰寫。
* 《[Gradle User Guide](http://www.gradle.org/docs/current/userguide/userguide.html)》官方文件。

取得最新版的 Gradle

The latest release of Gradle is 2.2, released on 10th November 2014.

http://gradle.org/downloads

推薦：使用 gvm 安裝。

```bash
gvm install gradle
```

檢查 Gradle 版本。

```bash
gradle --version
```

建議使用 Gradle 1.8 以上。
