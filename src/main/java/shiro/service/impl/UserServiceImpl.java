package shiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shiro.mapper.UserMapper;
import shiro.model.User;
import shiro.service.IUserService;

/**
 * @author chenyifei
 * @date 2017-05-22
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see shiro.service.UserService#getByUsername(java.lang.String)
	 */
	@Override
	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public Set<String> listRoles(String username) {
		return userMapper.listRoles(username);
	}

	@Override
	public Set<String> listPermissions(String username) {
		return userMapper.listPermissions(username);
	}

}
