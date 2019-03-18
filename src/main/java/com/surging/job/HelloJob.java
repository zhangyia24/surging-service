package com.surging.job;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
public class HelloJob {
    private String jobName;
    public HelloJob() {
    }
    public HelloJob(String jobName) {
        this.jobName=jobName;
    }

    public void hello(String jobName) throws InterruptedException {
        for (int i=0;i<=100;i++){
            System.out.println("正在执行: "+jobName+"____"+i);
            Thread.sleep(200);
        }

    }
}
