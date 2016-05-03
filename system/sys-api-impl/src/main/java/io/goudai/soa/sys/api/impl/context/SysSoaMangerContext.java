package io.goudai.soa.sys.api.impl.context;

import com.alibaba.dubbo.config.annotation.Service;
import io.goudai.soa.core.impl.DefaultSoaManager;
import io.goudai.soa.sys.api.croe.SysSoaManger;

/**
 * @author freeman
 */
@Service(version = "1.0.0", timeout = 2000)
public class SysSoaMangerContext extends DefaultSoaManager implements SysSoaManger {
}
