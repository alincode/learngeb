## 測試結果

在範例中，我們使用了maven surefire，Jenkins內建呈現此報表套件，所以我們連設定都不需要，就可以看到親和力十足的報表。

* pom.xml

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven.surefire.version}</version>
    <configuration>
        <!-- 略 -->
    </configuration>
</plugin>
```

### 趨勢圖
需在建構歷程有兩次以上的記錄時，才會出現。

![趨勢圖](report_trend.png)

### 彙整測試結果
![彙整測試結果](report_summary.png)

### 測試結果之套件
![測試結果之套件](report_test_set.png)

### 測試結果之類別
![測試結果之類別](report_test_case.png)

### 測試結果之類別步驟
![測試結果之類別步驟](report_step.png)

### 錯誤訊息
![錯誤訊息](fail_message.png)
