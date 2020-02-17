package com.pjmike.id;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @description: 随机ID生成器
 * @author: pjmike
 * @create: 2020/02/17
 */
public class RandomIdGenerator implements IdGenerator {
    @Override
    public String generate() throws IdGenerationFailureException {
        String substrOfHostName;
        try {
            // 获取 hostname
            substrOfHostName = getLastFiledOfHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerationFailureException("...", e);
        }
        // 时间戳
        long currentTimeMillis = System.currentTimeMillis();
        // 随机字符串
        String randomString = generateRandomAlphameric(8);
        String id = String.format("%s-%d-%s", substrOfHostName, currentTimeMillis, randomString);
        return id;
    }

    private String getLastFiledOfHostName() throws UnknownHostException {
        String substrOfHostName = null;
        String hostName = InetAddress.getLocalHost().getHostName();
        if (StringUtils.isEmpty(hostName)) {
            throw new UnknownHostException("hostname is empty");
        }
        substrOfHostName = getLastSubstrSplittedByDot(hostName);
        return substrOfHostName;
    }

    /**
     * 获取主机名字
     *
     * @param hostName
     * @return
     */
    private String getLastSubstrSplittedByDot(String hostName) {
        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("hostname is empty");
        }
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length - 1];
        return substrOfHostName;
    }

    /**
     * 随机数，由数字.字母组成
     *
     * @param length
     * @return
     */
    private String generateRandomAlphameric(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("..");
        }

        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }
}
