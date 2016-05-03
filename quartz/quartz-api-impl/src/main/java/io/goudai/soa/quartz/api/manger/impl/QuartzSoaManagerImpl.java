package io.goudai.soa.quartz.api.manger.impl;

import io.goudai.soa.core.impl.DefaultSoaManager;
import io.goudai.soa.quartz.api.manger.QuartzSoaManager;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0.0",timeout=2000)
public class QuartzSoaManagerImpl extends DefaultSoaManager implements QuartzSoaManager {}
