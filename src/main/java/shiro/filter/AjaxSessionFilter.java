package shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @author chenyifei
 * @date 2017-05-27
 */
public class AjaxSessionFilter extends UserFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = WebUtils.toHttp(request);
		String xmlHttpRequest = req.getHeader("X-Requested-With");
		if (xmlHttpRequest != null)
			if (xmlHttpRequest.equalsIgnoreCase("XMLHttpRequest")) {
				HttpServletResponse res = WebUtils.toHttp(response);
				res.sendError(401);
				return false;
			}

		return super.onAccessDenied(request, response);
	}
}
