package ru.madeira.onlinelibrarymanagementsystem.aop;

import liquibase.pro.packaged.B;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookActionsLog;
import ru.madeira.onlinelibrarymanagementsystem.service.BookActionsLogService;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class AppLoggingAspect {

    private BookActionsLogService bookActionsLogService;

    @Autowired
    public void setBookActionsLogService(BookActionsLogService bookActionsLogService) {
        this.bookActionsLogService = bookActionsLogService;
    }

    @Pointcut("within(ru.madeira.onlinelibrarymanagementsystem.service..*)")
    public void serviceMethods() {
    }

    @Around("serviceMethods()")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        log.info("The execution of the method " + joinPoint.getSignature() + " begins");
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        log.info("Execution of " + joinPoint.getSignature().getName() + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return result;
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void exceptionLogging(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    @Pointcut("within(ru.madeira.onlinelibrarymanagementsystem.exception.GlobalExceptionHandler)")
    public void exceptionHandlerMethods() {
    }

    @Before("exceptionHandlerMethods()")
    public void exceptionHandlingLogging(JoinPoint joinPoint) {
        log.info("Exception handler method " + joinPoint.getSignature().getName() + "worked.");
    }

    @Pointcut("within(ru.madeira.onlinelibrarymanagementsystem.controller..*)")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void controllerMethodsParameters(JoinPoint joinPoint) {
        BookActionsLog bookActionsLog = new BookActionsLog();
        bookActionsLog.setMethodArgs(joinPoint.getArgs());
        bookActionsLog.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
        bookActionsLog.setMethodName(joinPoint.getSignature().getName());
        bookActionsLogService.saveLog(bookActionsLog);
        log.info("User: + " + SecurityContextHolder.getContext().getAuthentication() + " calling a controller method: " + joinPoint.getSignature().getName());
    }
}
