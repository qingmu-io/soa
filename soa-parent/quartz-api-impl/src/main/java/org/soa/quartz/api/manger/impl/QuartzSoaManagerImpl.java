package org.soa.quartz.api.manger.impl;

import org.soa.core.impl.SoaManagerImpl;
import org.soa.quartz.api.manger.QuartzSoaManager;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0.0",timeout=2000)
public class QuartzSoaManagerImpl extends SoaManagerImpl implements QuartzSoaManager {}
