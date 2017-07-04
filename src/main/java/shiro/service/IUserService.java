package shiro.service;

import java.util.Set;

import shiro.model.User;

/**
 * @author chenyifei
 * @date 2017-05-22
 */
public interface IUserService {

	User getByUsername(String username);

	Set<String> listRoles(String username);

	Set<String> listPermissions(String username);

}
