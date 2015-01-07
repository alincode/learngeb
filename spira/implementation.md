# 實作

**SpiraTestCase**

自定 Spock Annotation 來聯結 SpiraTestCase 的 Test Case ID

```java
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@org.spockframework.runtime.extension.ExtensionAnnotation(SpockExtension.class)
public @interface SpiraTestCase {
    public int id() default 0;
}
```

**SpiraTestSet**

自定 Spock Annotation 來聯結 SpiraTestCase 的 Test Set ID

```java
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@org.spockframework.runtime.extension.ExtensionAnnotation(SpockExtension.class)
public @interface SpiraTestSet {
    public int id() default 0;
}
```

**SpockExtension**

```java
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;

public class SpockExtension extends AbstractAnnotationDrivenExtension {

    public void visitSpecAnnotation(java.lang.annotation.Annotation annotation, org.spockframework.runtime.model.SpecInfo spec) { /* compiled code */ }

    private void sortFeaturesInDeclarationOrder(org.spockframework.runtime.model.SpecInfo spec) { /* compiled code */ }

    private void includeFeaturesBeforeLastIncludedFeature(org.spockframework.runtime.model.SpecInfo spec) { /* compiled code */ }

    private void skipFeaturesAfterFirstFailingFeature(org.spockframework.runtime.model.SpecInfo spec) { /* compiled code */ }
}
```

**SpiraIntegrationListener**

```java
public class SpiraIntegrationListener extends RunListener{
    private Integer getSpiraTestCaseId(Description description){
        Class<?> obj = description.getTestClass();

        if (obj.isAnnotationPresent(SpiraTestCase.class)) {
            Annotation annotation = obj.getAnnotation(SpiraTestCase.class);
            SpiraTestCase spiraTestCase = (com.inflectra.spiratest.addons.junitextension.spock.SpiraTestCase) annotation;
            return spiraTestCase.id();
        }
        return 0;
    }
}
```

**pom.xml**

關閉了預設的 `maven-surefire-plugin` listeners，改設定為指定自行實作的 CustomerListener。

```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.9</version>
        <configuration>
          <includes>
              <include>*Spec.*</include>
          </includes>
          <systemPropertyVariables>
            <geb.env>chrome</geb.env>
            <geb.build.baseUrl>http://127.0.0.1</geb.build.baseUrl>
            <geb.build.reportsDir>target/test-reports/geb</geb.build.reportsDir>
          </systemPropertyVariables>
            <properties>
                <property>
                    <name>usedefaultlisteners</name>
                    <value>false</value>
                </property>
                <property>
                    <name>listener</name>
                    <value>com.inflectra.spiratest.addons.junitextension.SpiraIntegrationListener</value>
                </property>
            </properties>
        </configuration>
      </plugin>
```

**GebReportingSpec**

於每個類別上設定所屬的 Annotation

```groovy
@Stepwise
@SpiraTestCase(id=123)
@SpiraTestSet(id=1)
class FaqSpec extends GebReportingSpec
```

## 虛擬碼

主要整合邏輯

```java
updateTestCaseSteps();

ArrayOfRemoteManualTestRun arrayOfRemoteManualTestRun = spiraExecute.createArrayOfRemoteManualTestRun(testCaseStepMap.keySet());

for (RemoteManualTestRun remoteManualTestRun : arrayOfRemoteManualTestRun.getRemoteManualTestRun()) {
    Integer testCaseId = remoteManualTestRun.getTestCaseId();
    TestRun testRun = testRunMap.get(testCaseId);
    spiraExecute.updateRemoteManualTestRun(remoteManualTestRun, testRun, testCaseStepMap.get(testCaseId));

}
spiraExecute.testRunSave(arrayOfRemoteManualTestRun);
```
