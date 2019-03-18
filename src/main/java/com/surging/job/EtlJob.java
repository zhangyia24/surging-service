package com.surging.job;

import org.quartz.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
    /**
     * Job 的实例要到该执行它们的时候才会实例化出来。每次 Job 被执行，一个新的 Job 实例会被创建。
     * 其中暗含的意思就是你的 Job 不必担心线程安全性，因为同一时刻仅有一个线程去执行给定 Job 类的实例，甚至是并发执行同一 Job 也是如此。
     * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一个任务
     * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
     */
@DisallowConcurrentExecution
public class EtlJob implements InterruptableJob,Serializable {

        // has the job been interrupted?
        private boolean _interrupted = false;

        // job name
        private JobKey _jobKey = null;

        @Override
        public void execute(JobExecutionContext jobExecutionContext)
                throws JobExecutionException {

            _jobKey = jobExecutionContext.getJobDetail().getKey();
            System.out.println("---- " + _jobKey + " executing at " + new Date());

            try {
                // main job loop... see the JavaDOC for InterruptableJob for discussion...
                // do some work... in this example we are 'simulating' work by sleeping... :)
                String name=_jobKey.getName();
                HelloJob helloJob=new HelloJob();
                helloJob.hello(name);

                    // periodically check if we've been interrupted...
                    if(_interrupted) {
                        System.out.println("--- " + _jobKey + "  -- Interrupted... bailing out!");
                        return; // could also choose to throw a JobExecutionException
                        // if that made for sense based on the particular
                        // job's responsibilities/behaviors
                    }
                }catch (Exception e){
                System.out.println(e);
            } finally {
                System.out.println("---- " + _jobKey + " completed at " + new Date());
            }
        }

        @Override
        public void interrupt() throws UnableToInterruptJobException {
            System.out.println("---" + _jobKey + "  -- INTERRUPTING --");
            _interrupted = true;
        }

    }
