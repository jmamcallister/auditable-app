package org.amcallister.auditableapp.audit;

import org.amcallister.auditableapp.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    @Autowired
    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @Around("@annotation(auditableAnnotation)")
    public Object audit(ProceedingJoinPoint joinPoint, Auditable auditableAnnotation) throws Throwable {
        boolean ok = false;
        try {
            Object o = joinPoint.proceed();
            ok = true;
            return o;
        } finally {
            //TODO add @AfterThrows advice
            if (ok) {
                auditService.auditEvent(auditableAnnotation.auditActionType());
            } else {
                auditService.auditErrorEvent(auditableAnnotation.auditActionType());
            }
        }
    }
}
