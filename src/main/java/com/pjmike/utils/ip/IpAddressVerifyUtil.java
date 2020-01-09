package com.pjmike.utils.ip;

import org.apache.commons.lang.StringUtils;

/**
 * @description: ipv4地址验证
 * <p>
 * 参考： https://time.geekbang.org/column/article/177448
 * </p>
 * @author: pjmike
 * @create: 2020/01/09
 */
public class IpAddressVerifyUtil {
    public static boolean isValidIpAddress(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }
}
