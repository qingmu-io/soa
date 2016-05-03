package io.goudai.soa.sys.web.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.restful.util.BeanUtil;
import io.goudai.soa.sys.api.croe.SysSoaManger;
import io.goudai.soa.sys.web.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * 
 * @author liuyi
 *
 */
@RequestMapping("/sys/v1")
@Controller
public class UserResource {

	private final String	USERSERVICE	= "userService";
	private final String	LOGIN		= "login";
	private final String	PAGE		= "page";
	private final String	INSERT		= "insert";
	private final String	UPDATE		= "update";
	private final String	DELETE		= "delete";

	@Reference(version = "1.0.0", interfaceClass = SysSoaManger.class, timeout = 2000, check = true, lazy = false)
	private SysSoaManger	soaManger;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public SoaContext login(String username, String password) {
		final SoaContext context = SoaContext.newSoaContext(USERSERVICE, LOGIN);
		context.addAttr("username", username);
		context.addAttr("password", password);
		return soaManger.invokeNoTx(context);
	}

	@ResponseBody
	@RequestMapping(value="/users/{page}",method=RequestMethod.GET)
	public SoaContext page(@PathVariable("page") int page,
			HttpServletRequest request) {
		final Map<String, String[]> parameterMap = request.getParameterMap();
		final SoaContext context = SoaContext.newSoaContext(USERSERVICE, PAGE);
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
			context.addAttr(entry.getKey(), entry.getValue()[0]);
		return soaManger.invokeNoTx(context);
	}
	
	@ResponseBody
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public SoaContext insert(User user) throws Exception {
		final Map<String, Object> attr = BeanUtil.getInstance().objectToMap(user);
		final SoaContext context = SoaContext.newSoaContext(USERSERVICE, INSERT);
		context.setAttr(attr);
		return soaManger.invoke(context);
	}
	
	@ResponseBody
	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
	public SoaContext insert(@PathVariable("id")int id){
		final SoaContext context = SoaContext.newSoaContext(USERSERVICE, DELETE);
		context.addAttr("id", id);
		return soaManger.invoke(context);
	}
	@ResponseBody
	@RequestMapping(value="/users",method=RequestMethod.PUT)
	public SoaContext update(User user) throws Exception {
		final Map<String, Object> attr = BeanUtil.getInstance().objectToMap(user);
		final SoaContext context = SoaContext.newSoaContext(USERSERVICE, UPDATE);
		context.setAttr(attr);
		return soaManger.invoke(context);
	}
	
	
}
