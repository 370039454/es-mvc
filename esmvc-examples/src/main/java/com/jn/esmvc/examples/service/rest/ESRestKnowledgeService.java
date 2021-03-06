package com.jn.esmvc.examples.service.rest;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.impl.ESModelServiceImpl;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ESRestKnowledgeService extends ESModelServiceImpl<KnowledgeESModel> implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        setClient(client).setModelClass(KnowledgeESModel.class);
    }

    @Autowired
    @Qualifier("esRestClient")
    public ESRestKnowledgeService setClient(ESRestClient client) {
        super.setClient(client);
        return this;
    }

    @Override
    @Autowired
    @Qualifier("scrollContextCache")
    public void setScrollCache(ScrollContextCache scrollContextCache) {
        super.setScrollCache(scrollContextCache);
        setScrollDuration(scrollContextCache.getExpireInSeconds() * 1000 + 5 * 1000);
    }
}
