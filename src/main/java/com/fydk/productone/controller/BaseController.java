package com.fydk.productone.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import com.fydk.productone.common.StringUtils;

/**
 * 
 * @Description : BaseController
 * @time 创建时间 : 2018年6月6日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class BaseController {
	
    @Autowired
    protected HttpServletRequest request;
    
    @Autowired
    protected HttpServletResponse response;

//	@Autowired
//	FreeMarkerConfigurer freeMarkerConfigurer;
    
    /**
     * 
     * @Description : 获得请求用户ID
     * @return
     */
    public Long getUserId() {
        try {
            String userId = request.getHeader("x-userId-header");
            if (StringUtils.isEmpty(userId)) {
                userId = request.getParameter("userId");
            }
            if (StringUtils.isEmpty(userId)) {
                return null;
            }else {
                return Long.valueOf(userId);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @Description : 获得请求用户帐号
     * @return
     */
    public String getUserAccount() {
        try {
            String userAccount = request.getHeader("x-userAccount-header");
            if (StringUtils.isEmpty(userAccount)) {
                userAccount = request.getParameter("userAccount");
            }
            if (StringUtils.isEmpty(userAccount)) {
                return "";
            }else {
                return URLDecoder.decode(userAccount, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     *
     * @Description : 获得请求用户名
     * @return
     */
    public String getUserName() {
        try {
            try {
                String userName = request.getHeader("x-userName-header");
                if (StringUtils.isEmpty(userName)) {
                    userName = request.getParameter("userName");
                }
                if (StringUtils.isEmpty(userName)) {
                    return "";
                }else {
                    return URLDecoder.decode(userName, "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 
     * @Description : 获得请求用户公司ID
     * @return
     */
    public Long getCompanyId() {
        try {
            String companyId = request.getHeader("x-companyId-header");
            if (StringUtils.isEmpty(companyId)) {
                companyId = request.getParameter("companyId");
            }
            if (StringUtils.isEmpty(companyId)) {
                return null;
            }else {
                return Long.valueOf(companyId);
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * @Description : 获得请求用户对象
     * @return 
     */
    public Object getUser() {
        return request.getAttribute("user");
    }
    

	
	/**
	 * 
	 * @Description : 返回WEB-INF的Path
	 * @return 
	 */
	private String getWebInfoPath() {
		String webinfoPath = "";
		try {
			webinfoPath = ResourceUtils.getURL("classpath:").getPath();
			if (webinfoPath.endsWith("/classes/")) {
				webinfoPath = webinfoPath.substring(0, webinfoPath.length() - 9);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(StringUtils.isBlank(webinfoPath)){
			throw new RuntimeException("获取WEB-INF路径失败");
		}
		
		return webinfoPath;
	}

    /**
     * 
     * @Description : 编码
     * @param str
     * @return
     */
    public String encode(String str) {
        if (str != null) {
            try {
                str = java.net.URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }

        return str;
    }

    /**
     * 
     * @Description : 解码
     * @param str
     * @return
     */
    public String decode(String str) {
        if (str != null) {
            try {
                str = java.net.URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }

        return str;
    }
	
}
