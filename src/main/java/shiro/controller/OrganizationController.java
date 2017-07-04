package shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenyifei
 * @date 2017-06-29
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {

	@RequiresPermissions(value={"organization:add"})
	@GetMapping("add")
	@ResponseBody
	public Object add() {
		return true;
	}
	
	@RequiresPermissions(value={"organization:edit"})
	@GetMapping("edit")
	@ResponseBody
	public Object edit() {
		return true;
	}
}
