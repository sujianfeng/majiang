package com.game.majiang.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("deprecation")
public class CloudUtil {
    private static Logger logger = Logger.getLogger(CloudUtil.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    /**
     * 获取http请求中的数据流
     *
     * @param request
     * @return 数据流
     */
    public static String getRequestString(HttpServletRequest request) {
        String dataString = null;
        try {
            StringBuffer requestString = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(request.getInputStream(), "UTF-8"));
            String tmpString = "";
            while ((tmpString = reader.readLine()) != null) {
                requestString.append(tmpString);
            }
            reader.close();
            dataString = requestString.toString().trim();
        } catch (IOException e) {
            logger.error("exception:" + e.getMessage());
            e.printStackTrace();
        }
        return dataString;
    }

    /**
     * 判断请求是否超时
     *
     * @param timestamp 请求时间戳
     * @return false:未超时 true:超时
     */
    public static boolean isOverTime(String timestamp) {
        try {
            long request_timestamp = Long.parseLong(timestamp);
            logger.info("request_time:"
                    + new Date(request_timestamp).toLocaleString());
            long current_timestamp = System.currentTimeMillis();
            logger.info("current_time:"
                    + new Date(current_timestamp).toLocaleString());
            long diff_timestamp = 5 * 60 * 1000;
            // if (request_timestamp > current_timestamp) {
            // logger.info("illegal request_time");
            // return true;
            // }

            long timediff = (current_timestamp - request_timestamp) > 0 ? (current_timestamp - request_timestamp)
                    : (request_timestamp - current_timestamp);

            logger.info("timediff:" + timediff);
            if (timediff > diff_timestamp) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("exception:" + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 按照字典升序拼接连接字符串
     *
     * @param params 需要拼接的字符串map
     * @return 拼接后的字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {

            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 校验同方参数加密
     *
     * @param map
     * @param key
     * @return
     */
    public static String signParam(Map<String, String> map, String key) {
        logger.info("签名key:" + key);
        String linkString = createLinkString(map);
        String source = linkString + "&" + key;
        // String source = linkString + "&app_secret=" + key;
        logger.info("加密前字符串:" + source);
        String signString = MD5Tool.md5Encryption(source);
        logger.info("加密后字符串:" + signString);
        return signString;
    }

    /**
     * 创建订单号
     *
     * @return 基于当前日期的订单号
     */
    public static String getOrderId() {
        StringBuffer result = new StringBuffer();
        result.append(sdf.format(new Date()));
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            result.append(r.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 获取几个月后的日期
     *
     * @param date     初始日期
     * @param duration 月份间隔
     * @return 几个月后的日期
     */
    public static Date getMonthLater(Date date, int duration) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, duration);// 日期加duration个月
        Date laterDate = rightNow.getTime();
        return laterDate;
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return 客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String internerIp = request.getRemoteAddr();
        if (StringUtils.isBlank(internerIp)) {
            internerIp = request.getRemoteHost();
        }
        return internerIp;
    }

    /**
     * 输出json到客户端
     *
     * @param response
     * @param outputjson
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeJsonResult(HttpServletResponse response,
                                       JSONObject outputjson) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                response.getOutputStream(), "utf-8"));
        out.print(outputjson);
        out.flush();
        out.close();
    }

    /**
     * 输出字符串到客户端
     *
     * @param response
     * @param result
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeStringResult(HttpServletResponse response,
                                         String result) throws IOException {
        logger.info("写入到客户端的数据：" + result);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                response.getOutputStream(), "utf-8"));
        out.print(result);
        out.flush();
        out.close();
    }


    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 1;
        }
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 检查参数是否完整
     *
     * @param params
     * @return
     */
    public static boolean checkParams(String... params) {

        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回保存信息
     *
     * @param bool
     * @param msg
     */
    public static JSONObject buildAjaxOperationJson(boolean bool, String msg) {
        JSONObject resultJson = new JSONObject();
        if (bool) {
            resultJson.put("isCorrect", 1);
            resultJson.put("msg", msg);
        } else {
            resultJson.put("isCorrect", 2);
            resultJson.put("msg", msg);
        }
        return resultJson;
    }

    /**
     * 获取运营商名称
     *
     * @param oid
     * @return
     */
    public static String getOName(int oid) {
        String oname = "移动";
        switch (oid) {
            case 1:
                oname = "移动";
                break;
            case 2:
                oname = "联通";
                break;
            case 3:
                oname = "电信";
                break;
            case 4:
                oname = "支付宝";
                break;
            case 5:
                oname = "微信";
                break;
            case 6:
                oname = "宽带";
                break;
            default:
        }
        return oname;
    }

    /**
     * 记录查询条件
     *
     * @param condition
     */
    public static void logCondition(String[] condition) {
        StringBuffer conditionBuff = new StringBuffer();
        for (String con : condition) {
            conditionBuff.append(con + ",");
        }
        logger.info("查询条件:" + conditionBuff);
    }


    /**
     * 创建免校验ssl连接
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }

                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 输入错误信息
     * @param resultMsg
     * @return
     */
    public static String buildErrorResponse(String resultMsg) {
        try {
            JSONObject json = new JSONObject();
            json.put("returnCode", 0);
            json.put("resultCode", 0);
            json.put("resultMsg", resultMsg);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
