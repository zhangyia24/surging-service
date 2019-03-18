package com.surging.controller;

import com.alibaba.fastjson.JSON;
import com.surging.entity.QuartzEntity;
import com.surging.entity.Result;
import com.surging.entity.quartzEntity.QrtzTriggers;
import com.surging.service.IQrtzTriggerService;
import com.surging.tools.EmptyUtil;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
@RestController
@RequestMapping(value="/qrtz")
public class QrtzController {
    	private final static Logger LOGGER = LoggerFactory.getLogger(QrtzController.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    IQrtzTriggerService qrtzTriggerService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="查看任务列表")
    @PostMapping(value = "/list")
    public String qrtzList(){
        LOGGER.info("查看任务列表");
        List<QrtzTriggers> qrtzTriggers=qrtzTriggerService.listQrtzTrigger();
        if(qrtzTriggers!=null){
            return Result.ok(JSON.toJSONString(qrtzTriggers));
        }

//        List<QrtzTriggers> qrtzTriggersList = JSON.parseArray(qrtzTriggersJson,QrtzTriggers.class);
        return Result.ok("列表为空");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(value="查看任务列表")
    @PostMapping(value = "/add")
    public String qrtzAdd(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobGroup") String jobGroup,
                          @RequestParam(value = "jobDescription") String jobDescription,
                          @RequestParam(value = "cronExpression") String cronExpression){
        LOGGER.info("新增任务");
        String msg=EmptyUtil.isEmpty(jobName,jobGroup,cronExpression);

        if(msg!=""){
            return Result.error(msg);
        }
        try {
            //获取Scheduler实例、废弃、使用自动注入的scheduler、否则spring的service将无法注入
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //如果是修改  展示旧的 任务
            LOGGER.info("初始化测试任务");
            QuartzEntity quartz = new QuartzEntity();
            quartz.setJobName(jobName);
            quartz.setJobGroup(jobGroup);
            quartz.setDescription(jobDescription);
            quartz.setJobClassName("com.surging.job.EtlJob");
            quartz.setCronExpression(cronExpression);
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        }catch (Exception e){
            if(e.toString().contains("org.quartz.ObjectAlreadyExistsException")){
                return Result.error("添加失败，job标识重复");
            }

        }
        return msg;
    }
	@PostMapping("/pause")
	public  String pause(@RequestParam(value = "jobName") String jobName,
                         @RequestParam(value = "jobGroup") String jobGroup) {
		LOGGER.info("停止任务");
		try {
		     JobKey key = new JobKey(jobName,jobGroup);
		     scheduler.pauseJob(key);
		     scheduler.interrupt(key);
		} catch (SchedulerException e) {
			 e.printStackTrace();
			 return Result.error("停止任务失败");
		}
		return Result.ok("停止任务成功");
	}
	@PostMapping("/resume")
	public  String resume(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobGroup") String jobGroup) {
		LOGGER.info("恢复任务");
		try {
		     JobKey key = new JobKey(jobName,jobGroup);
		     scheduler.resumeJob(key);
		} catch (SchedulerException e) {
			 e.printStackTrace();
            return Result.error("恢复任务失败");
        }
        return Result.ok("恢复任务成功!");
	}
	@ApiOperation(value="移除任务")
	@PostMapping("/remove")
	public  String remove(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobGroup") String jobGroup) {
		try {

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            System.out.println("removeJob:"+ JobKey.jobKey(jobName));
        } catch (Exception e) {
        	e.printStackTrace();
            return Result .error("移除任务失败");
        }
		return Result.ok("移除任务成功");
	}
}
