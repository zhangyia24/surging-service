package com.surging.entity;


import org.springframework.stereotype.Component;

/**
 * 初始化一个测试Demo任务
 * 创建者 科帮网
 * 创建时间	2018年4月3日
 */
@Component
public class TaskRunner {

//	private final static Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);
//
//	@Autowired
//    private IJobService jobService;
//	@Autowired
//    @Qualifier("Scheduler")
//    private Scheduler scheduler;
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//
//    public void run(ApplicationArguments var) throws Exception{
//    	Long count = jobService.listQuartzEntity(null);
//    	if(count==0){
//    		LOGGER.info("初始化测试任务");
//    		QuartzEntity quartz = new QuartzEntity();
//    		quartz.setJobName("test02");
//    		quartz.setJobGroup("test");
//    		quartz.setDescription("测试任务");
//    		quartz.setJobClassName("com.zdm.job.ChickenJob");
//    		quartz.setCronExpression("0/20 * * * * ?");
//   	        Class cls = Class.forName(quartz.getJobClassName()) ;
//   	        cls.newInstance();
//   	        //构建job信息
//   	        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
//   	        		quartz.getJobGroup())
//   	        		.withDescription(quartz.getDescription()).build();
//   	        // 触发时间点
//   	        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
//   	        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+quartz.getJobName(), quartz.getJobGroup())
//   	                .startNow().withSchedule(cronScheduleBuilder).build();
//   	        //交由Scheduler安排触发
//   	        scheduler.scheduleJob(job, trigger);
//    	}
//    }

}