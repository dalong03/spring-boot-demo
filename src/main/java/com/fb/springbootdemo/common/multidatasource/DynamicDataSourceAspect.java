package com.fb.springbootdemo.common.multidatasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fb.springbootdemo.common.multidatasource.DataSourceContextHolder;
import com.fb.springbootdemo.common.multidatasource.DynamicDataSource;
import com.fb.springbootdemo.common.multidatasource.DataSource;

/**
 * 多数据源切换的aop
 *
 */
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    private Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut(value = "@annotation(com.fb.springbootdemo.common.multidatasource.DataSource)")
    private void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        Method currentMethod = methodSignature.getMethod();

        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        if (datasource != null && StringUtils.hasText(datasource.value())) {
            DataSourceContextHolder.setDataSourceType(datasource.value());
            log.debug("设置数据源为：" + datasource.value());
        } else {
            DataSourceContextHolder.setDataSourceType(DynamicDataSource.DATASOURCE1);
            log.debug("设置数据源为：" + DynamicDataSource.DATASOURCE1);
        }

        try {
            return point.proceed();
        } finally {
            log.debug("清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
        }
    }
 

    /**
     * aop的顺序要早于spring的事务
     */
    @Override
    public int getOrder() {
        return 1;
    }

}
