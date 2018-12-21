package com.example.autoapi.util;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUtil {
    private static final String SIGNATURE = "signature";

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);


    /**
     *
     * @param map
     * @return
     */
    public static String buildSignSrc(TreeMap<String, String> map) {
        StringBuilder signSrcSb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (SIGNATURE.equals(entry.getKey())) {
                continue;
            }
            signSrcSb.append(entry.getKey()).append("=");

            if (StringUtils.isNotBlank(entry.getValue())) {
                signSrcSb.append(entry.getValue());
            }
            signSrcSb.append("&");
        }

        return StringUtils.substring(signSrcSb.toString(), 0, signSrcSb.length() - 1);
    }


    /**
     *settleapi 专用
     * @param map
     * @return
     */
    public static String buildSignSrcSettle(TreeMap<String, String> map) {
        StringBuilder signSrcSb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (SIGNATURE.equals(entry.getKey())) {
                continue;
            }
            signSrcSb.append(entry.getKey());
            if (StringUtils.isNotBlank(entry.getValue())) {
                signSrcSb.append(entry.getValue());
            }
        }

        return  signSrcSb.toString();
    }




    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param key  密钥
     *             编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String charset) throws Exception {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    /**
     *
     * @param text
     * @param key
     * @param charset
     * @return
     * @throws Exception
     */
    public static String signSettle(String text, String key, String charset) throws Exception {
        text = key + text + key;
        return DigestUtils.md5Hex(getContentBytes(text, charset)).toUpperCase();
    }


    public static String sign(String text, String charset) throws Exception {
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key  密钥
     *             编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String charset) throws Exception {
        text = text + key;
        logger.info("mysigntext:{}", text);
        String mysign = DigestUtils.md5Hex(getContentBytes(text, charset));
        logger.info("mysign:{}", mysign);
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
