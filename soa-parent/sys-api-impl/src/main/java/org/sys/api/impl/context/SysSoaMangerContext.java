package org.sys.api.impl.context;

import org.soa.core.impl.SoaManagerImpl;
import org.sys.api.croe.SysSoaManger;

import com.alibaba.dubbo.config.annotation.Service;
/**
 * 
 * @author liuyi
 *
 */
@Service(version="1.0.0",timeout=2000)
public class SysSoaMangerContext extends SoaManagerImpl implements SysSoaManger {
}
