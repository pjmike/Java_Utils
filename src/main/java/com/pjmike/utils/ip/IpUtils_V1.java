package com.pjmike.utils.ip;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * <p>
 *  在没有反向代理的情况下获取IP地址
 *  1. x-forwarded-for: 表示HTTP请求端真实IP，格式为：X-Forwarded-For:client1,proxy1,proxy2，一般情况下，第一个ip为客户端真实ip，后面的为经过的代理服务器ip。现在大部分的代理都会加上这个请求头
 *  2. Proxy-Client-IP/WL- Proxy-Client-IP： 这个一般是经过apache http服务器的请求才会有，用apache http做代理时一般会加上Proxy-Client-IP请求头，而WL-Proxy-Client-IP是他的weblogic插件加上的头
 *  3. Remote_Addr: 表示客户端的IP，不是由客户端提供的，而是由服务器根据客户端IP指定的，如果中间使用了代理服务器，那么服务器就会把Remote_Addr设置为代理服务器的IP
 * </p>
 *
 * @author: pjmike
 * @create: 2019/11/14
 */
public class IpUtils_V1 {
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
