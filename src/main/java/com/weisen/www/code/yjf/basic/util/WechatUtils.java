package com.weisen.www.code.yjf.basic.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.*;

/**
 * @author 作者:lanzhengjun
 * @createDate 创建时间：2019年3月12日 下午3:45:57
 */
public class WechatUtils {
    // 微信公众平台appid,H5支付,jsonAPi支付
//    public static String WX_PUBLIC_APPID = "wxaa40fcd1d1c549c2";
    //测试1
    public static String WX_PUBLIC_APPID = "wx5450b0124166c23d";
    //测试2
//    public static String WX_PUBLIC_APPID = "wx68e39844515478d9";
    //测试3
//    public static String WX_PUBLIC_APPID = "wxb1690517095bb9da";
    // 微信公众平台	AppSecret
    public static String WX_PUBLIC_KEY = "c45736ce2d0852b53c9874a5e33159b1";
    // 微信开放平台APPID APP支付
    public static String WX_OPEN_APPID = "wx66cb03334f396adf";
    /**
     * 微信支付API秘钥
     */
//    public static String WX_KEY = "d2fe1eedf0cf8f46f3f441f00ba13ac9";
        //最新线上API秘钥
    public static String WX_KEY = "f97ca1bbcf5549d7b92ae74f041fyuan";
    // 微信商户id
    public static String MCHID = "1549399071";

    /**
     * 微信交易类型:公众号支付
     */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    public static final String TRADE_TYPE_APP = "APP";

    public static final String TRADE_TYPE_MWEB = "MWEB";
    /**
     * WEB
     */
    public static final String WEB = "WEB";
    /**
     * 返回成功字符串
     */
    public static final String RETURN_SUCCESS = "SUCCESS";
    /**
     * 支付地址(包涵回调地址)
     */
    public static final String PAY_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2bef02f0ed84edfc&redirect_uri=http%3a%2f%2fwxpay.pes-soft.com%2fwxpay%2fm%2fweChat%2funifiedOrder&response_type=code&scope=snsapi_base#wechat_redirect";
    /**
     * 微信统一下单url
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 微信查询订单
     */
    public static final String CHECK_ORDER = "https://api.mch.weixin.qq.com/pay/orderquery";
    /**
     * 微信申请退款url
     */
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 微信支付通知url
     */
    public static final String NOTIFY_URL = "http://appsrv.booymp.com/web/pay/weisen/order/pay/wechat-refund-notify";
    /**
     * 支付成功跳转地址
     */
    public static final String RETURN_URL = "http://upick.booymp.com";
    /**
     * 证书位置
     */
    public static final String CERT_PATH = "H:/Ws/pes-wxpay/src/main/webapp/cert/apiclient_cert.p12";

    // 拼装微信支付需要的字段
    public static String packagePayPara(String openId, String payBody, String tradeNo, String ip, String price,
                                        String payType) {
        SortedMap<String, String> para = new TreeMap<String, String>();
        String result = "";
        try {
            if (WechatUtils.TRADE_TYPE_APP.equals(payType)) { // 如果是app支付
                para.put("appid", WechatUtils.WX_OPEN_APPID);
//					para.put("pay_key", WechatUtils.WX_OPEN_KEY);
            } else if (WechatUtils.TRADE_TYPE_JSAPI.equals(payType)) { // 如果是jsapi支付（公众号支付）
                para.put("appid", WechatUtils.WX_PUBLIC_APPID);
//					para.put("pay_key", WechatUtils.WX_PUBLIC_KEY);
                para.put("openid", openId);// 用户openid
            } else if (WechatUtils.TRADE_TYPE_MWEB.equals(payType)) { // 如果是h5支付
                para.put("appid", WechatUtils.WX_PUBLIC_APPID);
//					para.put("pay_key", WechatUtils.WX_PUBLIC_KEY);
            }
            para.put("body", payBody);// 支付详情
            para.put("mch_id", WechatUtils.MCHID);// 商户id
            para.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));// 随机支付串
            para.put("notify_url", WechatUtils.NOTIFY_URL);// 回调地址
            para.put("out_trade_no", tradeNo);// 订单号
            para.put("spbill_create_ip", ip);// ip
            para.put("total_fee", new BigDecimal(price).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString());// 价格
            para.put("trade_type", payType);// 支付的方式 公众号支付
            para.put("sign", WechatUtils.createSign(para));// sign 加密
            result = WechatUtils.arrayToXml(para);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Map<String, String> checkOrderByWechat(String tradeNo) {
        SortedMap<String, String> para = new TreeMap<String, String>();
        Map<String, String> map = new HashMap<>();
        para.put("appid", WX_OPEN_APPID);
        para.put("mch_id", MCHID);
        para.put("out_trade_no", tradeNo);
        para.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
        para.put("sign", createSign(para));
        para.put("sign_type", "MD5");
        String xml = arrayToXml(para);
        Map<String, String> resultMap = null;
        try {
            CloseableHttpResponse response = WechatUtils.Post(CHECK_ORDER, xml, false);
            resultMap = WechatUtils.parseXml(response.getEntity().getContent());
            EntityUtils.consume(response.getEntity());
            response.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String return_code = resultMap.get("return_code");
        String result_code = resultMap.get("result_code");
        System.out.println("return_code=" + return_code);
        System.out.println("result_code=+" + result_code);
        if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
            System.out.println("成功调用");
            map.put("total_fee", resultMap.get("total_fee"));
            map.put("trade_state", resultMap.get("trade_state"));
            return map;
        }
        return map;
    }


    //统一下单
    public static Map<String, String> unifiedOrder(String xml) {
        Map<String, String> resultMap = null;
        try {
            CloseableHttpResponse response = WechatUtils.Post(WechatUtils.UNIFIED_ORDER_URL, xml, false);
            resultMap = WechatUtils.parseXml(response.getEntity().getContent());
            EntityUtils.consume(response.getEntity());
            response.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }


    public static String filterEmoji(String source) {
        if (source != null && source.length() > 0) {
            return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return source;
        }
    }

    /**
     * 发送post请求
     *
     * @param url          请求地址
     * @param outputEntity 发送内容
     * @param isLoadCert   是否加载证书
     */
    public static CloseableHttpResponse Post(String url, String outputEntity, boolean isLoadCert) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        System.out.println(System.getProperty("file.encoding"));
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(outputEntity, "UTF-8"));
        if (isLoadCert) {
            // 加载含有证书的http请求
            return HttpClients.custom().setSSLSocketFactory(initCert()).build().execute(httpPost);
        } else {
            return HttpClients.custom().build().execute(httpPost);
        }
    }

    /**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert() throws Exception {
        FileInputStream instream = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        instream = new FileInputStream(new File(CERT_PATH));
        keyStore.load(instream, MCHID.toCharArray());

        if (null != instream) {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCHID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        return sslsf;
    }

    /**
     * 解析微信服务器发来的请求
     *
     * @param inputStream
     * @return 微信返回的参数集合
     */
    public static Map<String, String> parseXml(InputStream inputStream) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        try {
            // 获取request输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素所有节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element element : elementList) {
                map.put(element.getName(), element.getText());
            }
            // 释放资源
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 作用：产生随机字符串，不长于32位
     */
    public static String createNoncestr(int length) {

        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        String str = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(chars.length());
            str += chars.substring(index, index + 1);
        }
        return str;
    }

    /**
     * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param para   请求要素
     * @param sort   是否需要根据key值作升序排列
     * @param encode 是否需要URL编码
     * @return 拼接成的字符串
     */
    public static String formatBizQueryParaMap(Map<String, String> para, boolean sort, boolean encode) {
        List<String> keys = new ArrayList<String>(para.keySet());

        if (sort)
            Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = para.get(key);

            if (encode) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                sb.append(key).append("=").append(value);
            } else {
                sb.append(key).append("&").append(value).append("&");
            }
        }
        return sb.toString();
    }

    public static String createSign(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + WechatUtils.WX_KEY);
        System.out.println("签名：" + sb.toString());
        String sign = MD5.MD5(sb.toString()).toUpperCase();
        return sign;
    }


    /**
     * 作用：map转xml
     */
    public static String arrayToXml(Map<String, String> paramMap) {
        String xml = "<xml>";
        for (String key : paramMap.keySet()) {//
            //值是否只有字母和数字
            if (paramMap.get(key).matches("^[\\da-zA-Z]*$")) {
                xml += "<" + key + ">" + paramMap.get(key) + "</" + key + ">";
            } else {
                xml += "<" + key + "><![CDATA[" + paramMap.get(key) + "]]></" + key + ">";
            }
        }
        xml += "</xml>";
        return xml;
    }

    /**
     * xml 转  map
     *
     * @param xml
     * @return
     */
    public static Map<String, String> xmltoMap(String xml) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                String val = elm.getText();
                val = val.replace("<![CDATA[", "");
                val = val.replace("]]>", "");
                map.put(elm.getName(), val);
                elm = null;
            }
            node = null;
            nodeElement = null;
            document = null;
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取ip地址
     * getRemoteHost
     *
     * @param request
     * @return
     * @author sqq
     * @since JDK 1.8
     */
    public static String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
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
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date
     * @return
     */
    public static int getTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.valueOf(timestamp);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }

}
