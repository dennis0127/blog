package com.yyyow.blog.common.config;

import cn.hutool.core.util.IdUtil;
import com.yyyow.blog.common.utils.DateUtils;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 链路调用追踪
 */
@Data
@Accessors(chain = true)
@ToString
public class Context {
    // 追踪 id
    private  @NotNull String traceId = IdUtil.simpleUUID();
    // 追踪深度 调用的层数
    private Integer deep;
    // 追踪的时间
    private Integer startTime;
    // 追踪结束的时间
    private Integer endTime;

    // 背景上下文
    public static Context backGround(){
        Context ctx = new Context();
        ctx.deep = 0;
        ctx.traceId = IdUtil.simpleUUID();
        ctx.startTime = DateUtils.getSecondTimestamp(new Date());
        ctx.endTime = ctx.startTime;
        return ctx;
    }
}
