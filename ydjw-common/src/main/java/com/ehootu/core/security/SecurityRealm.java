package com.ehootu.core.security;

import com.ehootu.sys.service.ShiroService;
import com.ehootu.user.model.Police;
import com.ehootu.user.service.PoliceService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * 用户身份验证,授权 Realm 组件
 * 
 *
 **/
public class SecurityRealm extends AuthorizingRealm {
	@Autowired
	private PoliceService policeService;
	@Autowired
	private ShiroService shiroService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> strings = shiroService.getUserPermissions(1);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(strings);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = String.valueOf(token.getPrincipal());
		String password = new String((char[]) token.getCredentials());

		// 通过数据库进行验证
		final Police authentication = policeService.login(new Police(username, password));
		if (authentication == null) {
			throw new AuthenticationException("用户名或密码错误.");
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());

		/*
        // 单一用户在线控制
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session:sessions) {
            if (username.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                log.info("用户："+username+" 其他浏览器登录, 清空原有session 信息。" );
                session.setTimeout(0);//设置session立即失效，即将其踢出系统
                break;
            }
        }*/

		return authenticationInfo;
	}

}
