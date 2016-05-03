package io.goudai.soa.sys.api;

import io.goudai.soa.common.context.SoaContext;

public interface UserService {

    SoaContext login(SoaContext context);

    SoaContext insert(SoaContext context);

    SoaContext page(SoaContext context);

}
