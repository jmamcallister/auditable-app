package org.amcallister.auditableapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/{arg1}/{arg2}")
    public String getResource(@PathVariable("arg1") String arg1, @PathVariable("arg2") String arg2) {
        return appService.myService(arg1, arg2);
    }
}
