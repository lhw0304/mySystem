package com.mode.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.dao.ApiLogDAO;
import com.mode.security.AuthenticationUser;

/**
 * Created by Lei on 3/23/16.
 */
@Aspect
@Component
public class ApiCountingAspect {

    @Pointcut(value = " execution(* com.mode.api.*.*(..))")
    public void servicePointcut(){}

    // TODO: 4/7/16 The below code will execute after api returning. This part of code is used to
    // count the number of the api usage. Do the implementation later.
    @AfterReturning(value = "servicePointcut()", returning = "val")
    public void apiCountingAdvice(Object val) throws Exception{
        /*  This aspect executes after service returning.
            In this aspect, we can implement api counting code in the future.
            For now, leave it empty here.
            */

    }
}
