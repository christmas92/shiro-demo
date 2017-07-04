package shiro.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import shiro.model.User;

/**
 * @author chenyifei
 * @date 2017-05-22
 */
public interface UserMapper {

	User getByUsername(@Param("username") String username);

	Set<String> listRoles(@Param("username") String username);

	Set<String> listPermissions(@Param("username") String username);
}
