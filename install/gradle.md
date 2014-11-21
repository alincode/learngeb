# 安裝 Gradle

Gradle 是專案自動化建置（build automation）工具，使用 Groovy DSL 語法定義建置流程，提供 Dependency Management 並以 Maven Repository 為 JARs 檔案來源。

這些教學可以幫助您更快認識 Gradle。

* 《[認識 Gradle](http://www.codedata.com.tw/java/understanding-gradle-1-ant/)》由 Java 社群知名的 qrtt1 作者撰寫。
* 《[Gradle User Guide](http://www.gradle.org/docs/current/userguide/userguide.html)》官方文件。

取得最新版的 Gradle

The latest release of Gradle is 2.2, released on 10th November 2014.

http://gradle.org/downloads

安裝提示：下載及解壓縮 gradle-2.2-bin.zip 檔案，並正確設定環境變數的 PATH。

Ubuntu Linux 可以使用 apt-get 安裝（但我們仍建議您考慮 GVM 安裝方式）。

```bash
sudo apt-get install gradle
```

使用 gvm 安裝（**推薦的方式**），適合 Linux、Mac OS X、Cygwin。

```bash
curl -s get.gvmtool.net | bash
```

安裝 Gradle 最新版。

```bash
gvm install gradle
```

檢查 Gradle 版本。

```bash
gradle --version
```

建議使用 Gradle 1.8 以上。
