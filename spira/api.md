# API

**實作的基本流程**

* Test Case 需要先在平台建立完成，然後 Test Case Step 可以透過API動態新增與更新。

```
IImportExport soap = connectSpiraSoapProxyServer();
soap.testCaseAddStep(remoteTestStep, testCaseId);
```

* 接下來建立所有 Test Case，所對應的Test Case Run，它會回傳一個 ArrayOfRemoteManualTestRun。

```
ArrayOfRemoteManualTestRun arrayOfRemoteManualTestRun = soap.testRunCreateFromTestCases(arrayOfint, releaseId);
```

* 然後再接著更新裡面的 Test Case Run 與 Test Run Step

```
remoteManualTestRun.setExecutionStatusId(testRun.executionStatusId);
List<RemoteTestRunStep> steps = remoteManualTestRun.getTestRunSteps().getValue()
                    .getRemoteTestRunStep();
for(RemoteTestRunStep remoteTestRunStep : steps){
    remoteTestRunStep.setExecutionStatusId(step.executionStatusId);
}
```

* 全部資料都設定好後，再把整個Array存回去。

```
soap.testRunSave(arrayOfRemoteManualTestRun, SpiraUtils.convertDatesJava2Xml(new Date()));
```
