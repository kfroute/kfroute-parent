package com.melinkr.micro.auth.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.melinkr.kfrouteWeb.shiro.remote.RemoteServiceInterface;

import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;

/**
 * @author: zhangyl
 * @time: 2015-07-12 20:43
 * @version: 1.0
 * 将shiro中的认证成功的用户添加到request中，方便业务的进一步使用,获取通过CurrentUserMethodArgumentResolver实现
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private RemoteServiceInterface remoteService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String loginNo = (String)SecurityUtils.getSubject().getPrincipal();
        LoginMsg loginMsg = remoteService.findLoginMsgByLoginNo(loginNo);
        request.setAttribute(Constants.CURRENT_USER, loginMsg);
        return true;
    }
}
