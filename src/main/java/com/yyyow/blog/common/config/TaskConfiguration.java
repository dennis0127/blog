package com.yyyow.blog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 */
@Configuration
@EnableAsync
public class TaskConfiguration {
    @Bean("taskExecutor")
    public Executor teskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程创建时候初始化线程数
        executor.setCorePoolSize(2);
        //最大线程数
        executor.setMaxPoolSize(4);
        //缓冲队列
        executor.setQueueCapacity(99);
        // 允许线程空闲时间秒
        executor.setKeepAliveSeconds(60);
        //线程池名的前缀
        executor.setThreadNamePrefix("taskExecutor-");
        //这里采用CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在execute方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
