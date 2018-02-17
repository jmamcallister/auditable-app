package org.amcallister.auditableapp;

import org.amcallister.auditableapp.audit.AuditActionType;
import org.amcallister.auditableapp.audit.Auditable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private Logger LOGGER = LoggerFactory.getLogger(AppService.class);

    @Auditable(auditActionType = AuditActionType.ACTION_TYPE_ONE)
    public String myService(String arg1, String arg2) {
        LOGGER.info("Normal log statement");
        if ("THROW".equalsIgnoreCase(arg1)) {
            throw new RuntimeException("thrown exception message");
        }
        return arg1 + " " + arg2;
    }
}
