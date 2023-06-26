package com.springboot.project.drools.spring.app.service;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    public StatelessKieSession createStatelessKieSession(String drlString, String targetPath) {
        KieHelper kieHelper = new KieHelper();
        Resource drlResource = ResourceFactory.newByteArrayResource(drlString.getBytes());
        drlResource.setTargetPath(targetPath);
        kieHelper.addResource(drlResource, ResourceType.DRL);
        return kieHelper.build().newStatelessKieSession();
    }

}
