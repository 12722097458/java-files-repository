1.什么是Spring Batch

> SpringBatch是一个基于Spring、轻量级的批处理框架。不是调度框架，可以结合quartz一起使用。

2.主要组成

SpringBatch实现批处理是基于任务（Job）的，一个Job的实现是由一个或多个Step. Step的一种实现方式可以分为数据读取，数据处理，数据输出三部分（ItemReader，ItemProcessor和ItemWriter）。

Job是由Joblauncher来进行启动，而执行的任务信息将会通过Job Repository持久化到数据库中。

![image-20220322210223286](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220322210223286.png)

Job

（1）启动一个简单的job，看一下效果。通过注解@Bean进行注入，配置类上加入@EnableBatchProcessing注解，注释掉@EnableBatchAdmin，spring.batch.job.enabled=true 。启动项目，观察日志，发现日志打印了，job执行了。

helloWorldJob，当前是项目启动就自动运行，是通过Job Launcher来实现的。

（2）也可以通过前台进行调用。在我们平时工作项目CRC中，是通过Spring Batch Admin前台页面来进行job调用以及状态查看的。

我们可以将其整合进来，通过案例查看效果。job/excusion以及数据表信息展示



总结两种配置方式：

注解 and XML配置文件

注解上面就是，配置文件的话是我们credit项目中使用最多的。

在jobs的目录下的配置文中，通过对step，job 的基本配置，可以实现对象的注入。

例子:helloJobWithXml

对应的代码中是将参数进行了打印。启动项目，查看效果。





刚才展示的两个job的step都是通过TaskLet接口来实现的，还有一种chunk方式可以了解一下

Step的组成分为两种，一个是

（1）tasklet

（2）chunk：演示一下



Flow

多个Step放一起可能比较混乱，出现了flow,可以对其进行分组。

```xml
<batch:job id="flowJobDemo">
    <batch:flow id="startFlowDemo" parent="flowDemo"/>
</batch:job>

<batch:flow id="flowDemo">
    <batch:step id="flowStep01" next="flowStep02">
        <batch:tasklet ref="myStep"/>
    </batch:step>
    <batch:step id="flowStep02">
        <batch:tasklet ref="myStep2"/>
    </batch:step>
</batch:flow>
```

Split实现并发执行

split块中必须包括至少两个flow块，同时需要配置task-executor才能正确实现并发。

```xml
<bean id="taskExecutor" class="org.springframework.core.task.SimpleAsyncTaskExecutor"/>

<batch:job id="splitJobDemo">
    <batch:split id="split01" next="afterSplitStep" task-executor="taskExecutor">
        <batch:flow>
            <batch:step id="split01-step01">
                <batch:tasklet ref="myStep"/>
            </batch:step>
        </batch:flow>
        <batch:flow>
            <batch:step id="split01-step02">
                <batch:tasklet ref="myStep2"/>
            </batch:step>
        </batch:flow>
    </batch:split>

    <batch:step id="afterSplitStep">
        <batch:tasklet ref="myStep3"/>
    </batch:step>
</batch:job>
```



决策器decider的使用

Decider可以认为是一个step, 当前step走完，下一步去哪里，看具体的返回值了。

```xml
<batch:job id="deciderJob">
        <batch:step id="first" next="decision">
            <tasklet ref="myStep" />
        </batch:step>

        <batch:decision id="decision" decider="decider">
            <batch:next on="SKIP" to="endStep" />
            <batch:next on="CONTINUE" to="nextStep" />
        </batch:decision>

        <batch:step id="nextStep" next="endStep">
            <batch:tasklet ref="myStep2"/>
        </batch:step>

        <batch:step id="endStep">
            <batch:tasklet ref="myStep3" />
        </batch:step>
    </batch:job>

    <bean id="decider" class="com.ityj.batch.decider.MyJobExecutionDecider" />
```

```java
@Slf4j
public class MyJobExecutionDecider  implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        Map<String, JobParameter> parameters = jobExecution.getJobParameters().getParameters();
        log.info("Job Parameter is: {}", parameters);

        if (parameters.containsKey("skip")) {
            return new FlowExecutionStatus("SKIP");
        }
        return new FlowExecutionStatus("CONTINUE");
    }
}
```

job嵌套:job里有step，step里有job

![image-20220323211255403](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220323211255403.png)

Listener

1. JobExecutionListener

   ```java
   @Slf4j
   public class MyJobExecutionListener implements JobExecutionListener {
       @Override
       public void beforeJob(JobExecution jobExecution) {
           log.info("Before job:{}", jobExecution.getJobInstance().getJobName());
           ExecutionContext executionContext = jobExecution.getExecutionContext();
           // pass data through executionContext
           executionContext.put("jobData", "jobListener");
       }
   
       @Override
       public void afterJob(JobExecution jobExecution) {
   
           log.info("After job, status is:{}", jobExecution.getExitStatus());
   
       }
   }
   
   <batch:job id="flowJobDemo">
           <batch:listeners>
               <batch:listener ref="myJobListener"/>
           </batch:listeners>
           <batch:flow id="startFlowDemo" parent="flowDemo"/>
       </batch:job>
   ```

2. StepExecutionListener

   ```java
   @Slf4j
   public class MyStepExecutionListener implements StepExecutionListener {
       @Override
       public void beforeStep(StepExecution stepExecution) {
           log.info("beforeStep:{}...", stepExecution.getStepName());
       }
   
       @Override
       public ExitStatus afterStep(StepExecution stepExecution) {
           log.info("afterStep:{}...", stepExecution.getStepName());
           return ExitStatus.COMPLETED;
       }
   }
   
   <batch:flow id="flowDemo">
           <batch:step id="flowStep01" next="flowStep02">
               <batch:tasklet ref="myStep"/>
               <batch:listeners>
                   <batch:listener ref="myStepListener"/>
               </batch:listeners>
           </batch:step>
           <batch:step id="flowStep02">
               <batch:tasklet ref="myStep2"/>
           </batch:step>
       </batch:flow>
   ```



内存数据库H2怎么查看，通过什么客户端？怎么看端口？

http://localhost:8080/h2-console url访问

quartz

> 基于java实现的任务调度框架，执行相关任务。

jobScheduler

jobDetail

jobTrigger

```java
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job01-myjob", "group01-job")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Trigger01", "group01-trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
```





3.应用

工作中应用



**Restartable** - 通常，当作业正在运行时，我们尝试再次启动它，这被视为**restart** ，它将再次启动。 为避免这种情况，您需要将**restartable**值设置为**false** ，如下所示。

```
<job id = "jobid" restartable = "false" >
</job>

如果出异常，点了restart按钮或者重新执行任务。如果false就不允许。true是默认值，可以
The job was not able to restart.
```







joblistener  steplistener有啥用

xml的参数解释

step step 间返回的状态有什么用

```
ExitStatus
```

HSQL是什么？



@EnableBatchProcessing注解可以为JobRepository提供自动配置。

## Spring Batch的特点

以下是Spring Batch的显着特征 -

- **Flexibility** - Spring Batch应用程序非常灵活。 您只需更改XML文件即可更改应用程序中的处理顺序。
- **Maintainability** - Spring Batch应用程序易于维护。 Spring Batch作业包括步骤，每个步骤都可以解耦，测试和更新，而不会影响其他步骤。
- **Scalability** - 使用分割技术，您可以扩展Spring Batch应用程序。 这些技巧可以让你 -
  - 并行执行作业的步骤。
  - 并行执行单个线程。
- **Reliability** - 如果发生任何故障，您可以通过解除步骤来从正好停止的位置重新启动作业。
- **Support for multiple file formats** - Spring Batch支持大量读取器和编写器，如XML，平面文件，CSV，MYSQL，Hibernate，JDBC，Mongo，Neo4j等。
- **Multiple ways to launch a job** - 您可以使用Web应用程序，Java程序，命令行等启动Spring Batch作业。

除此之外，Spring Batch应用程序支持 -

- 失败后自动重试。
- 在批处理执行期间和完成批处理之后跟踪状态和统计信息。
- 运行并发作业。
- 日志记录，资源管理，跳过和重新启动处理等服务。



docs

```url
https://docs.spring.io/spring-batch-admin/trunk/reference/reference.xhtml
https://iowiki.com/spring_batch/spring_batch_discussion.html
https://www.bookstack.cn/read/SpringBatchReferenceCN/README.md

https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#configureStep
```



```java
public enum BatchStatus {STARTING, STARTED, STOPPING, 
			STOPPED, FAILED, COMPLETED, ABANDONED }

ExitStatus
{UNKNOWN, EXECUTING, COMPLETED, NOOP, FAILED, STOPPED}

```





#### 　　1.job标签共有6个属性，分别是：

```
<batch:job id="" job-repository="" incrementer="" restartable="" parent="" abstract="true"></batch:job>
```

 

　　　　id：Job名称，作业的唯一标识。在整个跑批程序运行上下文中不允许重复。
　　　　job-repository：指定作业仓库。定义该Job运行期间使用的Job仓库，默认使用名字为jobRepository的Bean。
　　　　incrementer：作业参数递增器。只有在org.springframework.batch.core.launch.JobOperator 的 startNextInstance方法中使用。
　　　　restartable：作业是否可以重启。默认是true，表示支持重启。当设置为true时，只有当JobInstance为FAILED状态时才可以重启。
　　　　parent：指定该作业的父类作业。指定当前Job的父Job，Job可以从其他Job继承。通常在父Job中定义共有的属性。
　　　　abstract：定义作业是否是抽象的，默认是true，抽象的，不能被实例化。　　

#### 　　2.job标签的子元素

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
<batch:job id="" job-repository="" incrementer="" restartable="" parent="" abstract="true">
　　<batch:step id="" allow-start-if-complete="" next="" parent=""></batch:step>
　　<batch:split id="" next="" task-executor=""></batch:split>
　　<batch:flow parent="" id=""></batch:flow>
　　<batch:decision decider="" id=""></batch:decision>
　　<batch:listeners></batch:listeners>
　　<batch:validator ref=""></batch:validator>
　　<batch:description></batch:description>
</batch:job>
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

 

　　　　step：定义Job的作业步 。

　　　　split：定义并行作业步Step。

　　　　flow：引用独立配置的作业步流程。

　　　　decision：定义作业步执行的条件判断器，用于判断后续执行的作业步。

　　　　listeners：定义作业Job执行时的拦截器。

　　　　validator：定义作业参数检验器。也就是JobParameters的验证器。

　　　　description：描述该作业