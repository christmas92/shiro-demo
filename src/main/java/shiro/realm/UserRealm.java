package shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shiro.constant.Constants;
import shiro.model.User;
import shiro.service.IUserService;

/**
 * @author chenyifei
 * @date 2017-05-22 11:17:43
 */
@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.listRoles(username));
		authorizationInfo.setStringPermissions(userService.listPermissions(username));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		if (Constants.USER_LOCKED.equals(user.getState())) {
			throw new LockedAccountException();
		}	
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt
                getName()  //realm name
        );
		return authenticationInfo;
	}

}
