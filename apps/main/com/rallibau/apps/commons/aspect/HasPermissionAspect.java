package com.rallibau.apps.commons.aspect;

import com.rallibau.shared.domain.authentication.HasPermission;
import com.rallibau.shared.domain.authentication.SessionInfo;
import com.rallibau.shared.domain.authentication.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class HasPermissionAspect {

    @Autowired
    private SessionInfo sessionInfo;


    @Around("@within(com.rallibau.shared.domain.authentication.HasPermission) || " +
            "@annotation(com.rallibau.shared.domain.authentication.HasPermission)")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            Method method = methodSignature.getMethod();
            HasPermission hasPermission = method.getAnnotation(HasPermission.class);

            System.out.println("!!!!!!!!!!!!!!!! " + sessionInfo.logedUserId() + hasPermission.value() + "   " + hasPermission.aggregate());
        } else {
            throw new BadCredentialsException("The user does not have the proper permissions");
        }
        return proceedingJoinPoint.proceed();
    }


}
