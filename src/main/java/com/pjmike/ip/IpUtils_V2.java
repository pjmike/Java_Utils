package com.pjmike.ip;


import org.apache.commons.lang.text.StrTokenizer;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * <p>
 *    系统使用了反向代理的时候，一般使用request.getRemoteAddr()获取的是反向代理在内网中的IP地址，
 *    所以在反向代理中会将X-Forward-For替换为remote_addr,这样request.getRemoteAddr()获取的就是公网IP地址
 *    Nginx部分：
 *    location  ~  ^/static {
 *       proxy_pass  ....;
 *       proxy_set_header X-Forward-For $remote_addr ;
 *   }
 *
 *   代码出自： https://mp.weixin.qq.com/s/RnkqxMJfw7azYBUphKlS1A
 *   TODO 代码待后续进行验证
 *
 * </p>
 *
 * @author: pjmike
 * @create: 2019/11/14
 */
public class IpUtils_V2 {
    public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    public static final Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");

    public static String longToIpV4(long longIp) {
        int octet3 = (int) ((longIp >> 24) % 256);
        int octet2 = (int) ((longIp >> 16) % 256);
        int octet1 = (int) ((longIp >> 8) % 256);
        int octet0 = (int) ((longIp) % 256);
        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
    }

    public static long ipV4ToLong(String ip) {
        String[] octets = ip.split("\\.");
        return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
                + (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
    }

    public static boolean isIPv4Private(String ip) {
        long longIp = ipV4ToLong(ip);
        return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255"))
                || (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
                || longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255");
    }

    public static boolean isIPv4Valid(String ip) {
        return pattern.matcher(ip).matches();
    }

    public static String getIpFromRequest(HttpServletRequest request) {
        String ip;
        boolean found = false;
        if ((ip = request.getHeader("x-forwarded-for")) != null) {
            StrTokenizer tokenizer = new StrTokenizer(ip, ",");
            while (tokenizer.hasNext()) {
                ip = tokenizer.nextToken().trim();
                if (isIPv4Valid(ip) && !isIPv4Private(ip)) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
