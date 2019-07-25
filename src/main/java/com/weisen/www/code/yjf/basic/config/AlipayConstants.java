package com.weisen.www.code.yjf.basic.config;

public class AlipayConstants {

    private AlipayConstants () {}
    //沙箱url
    public static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    public static final String FORMAT = "json";
    //APPid
    //线上
    public static final String APP_ID = "2019062565651444";
    //沙箱
//    public static final String APP_ID = "2016101100661893";
    //APP私钥
    //线上
    public static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDtdbi6YuS7eKLD\n" + 
    		"oR+nUsnDbmx8jhETCSCDwNCwQvYGTS9fbvW3Pw6+K9F12hpCjMcpPrutmkh5wOgm\n" + 
    		"xL2+RiWabZAfDPPESQztKr6JEnwh41UJdmxUM/FBaB+Sn7N3nOUa47YHcgqWk+wu\n" + 
    		"1Y8lK2ukKfWO3v+naWORo6uCGgZVlgqtiqXxQY5T3CO0I0VX0tGGLvgTL32LtKRt\n" + 
    		"i04wQkF49SiPj6T3K0/dvWfiqB1aAH31DeXvaTjThEUGkclmaF5mg/mWMwFHzyOA\n" + 
    		"opkM/5JYL9bj6e8dmtaxjlLWlQLNz2xyKVLqAOw0V0prRo1Gi5q0olW8KLREYHZP\n" + 
    		"pc6NKITRAgMBAAECggEAV9brTa9Vc2/trbZFkkzSrAOGCBE5ibQfnf1OdVVf0vbs\n" + 
    		"Zj4SkQW3e2uUISdb/XA8ICq/nDltQ/SzK9wtYFxsfpK29HWDGwH20Si1sNjwqEyC\n" + 
    		"4Ww4KHHKdeqBe2iuJ7oXGfpQFLL/7bGsdaq0OAi22dRXrNEa+x/GIFLOF+NuomuX\n" + 
    		"AFSky62yndJdlYTYW2j4KYveYqjT7luII3gPUqrH/EZYkRc+0ee9XXcYXT4hyVfP\n" + 
    		"2XqvZo7NOLbzrOjF0x/FMUgZLUVV/BUz4qN1PLSX2ZFZva/6cmFS2ZGoLya8y7DI\n" + 
    		"W33VWtC3EaxndK7xGAxTqVy8Zzi5G0LYbENgFAhmXQKBgQD2lvI06xSjOXWp1Cq8\n" + 
    		"hWUUnlhreNDmXhmYvyOHKRej+7S0gIyQHaAcXD2xRG7OWR50lTJRgClnKp6p+x2a\n" + 
    		"PGllPWVWwco99lpqEreZjikddmMY4oViupmKujPXZuHHvZ0LEPq7l5m5k2OZlMIu\n" + 
    		"1F/JwS99vMCuRx2gZZFWepPw4wKBgQD2hZUKOLhC/zrxyukAm+0lU1YERWSXwckk\n" + 
    		"7fvI/AOnRFozH0P2I/PQU5c+Bawn+De3ZWKpUbYnY49L9CTMgWHXQ2ZaYBlG63/l\n" + 
    		"mjvNc3orR2Sb+JFaG6ULWi1B40Gbvmp+kFmyl0MzZ/IFXinU/WWiR7jF20JM7guE\n" + 
    		"M678ehpluwKBgB9uyl0Ygj70Oh114CS71FC2horckbRr1lN0OfEmuc67bSN9J23P\n" + 
    		"ePNkhrCsCnvfPFScGBfvgFeYRTVhvzpD7SNsmhhfB4qydoAC9shqKK+KwcMQTPRy\n" + 
    		"2NOoPKd+g9VUREp+1ZdIEd5n/VA8eCxEAhnTuFwMzXrpscsaZ1Zgta8FAoGAPqM6\n" + 
    		"d1pqFzvask0oXus/mlhOZs9q+9RVU3jUnQDLvNh1QsAS+Jux+6EBsNSoJY/AOG0e\n" + 
    		"YH9OlTuK0XW7U+sb/SI3A4efZJG58ZD62P0acZy2R5sk84xKp1PGZBNmPDhpvrbW\n" + 
    		"07XzcUBcElkWr/Oiu1UV8dAATKO/cVvgfm5X/rkCgYEAi5362N7GU9KNe+Ux+Pe6\n" + 
    		"f4kR916qsnCDdKAdL0YRHjP6wm2YBQ9qSnudmxD7ue17BiGuUqtIylYedYroedsi\n" + 
    		"HEVRtCu5hHzajO1G6k33J/LNyZrY1xe8fbAn8kB/Cjdxd+zjp6Br533EK0PeeVeL\n" + 
    		"WxjR8k9qb2HK4T6z3zWSLD4=";

    public static final String CHARSET = "utf-8";
    //支付宝公钥
    //线上
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoNq9VjM1JuXtO0DyqINqZdGxUg7s/0EPeUC/ez3KOZlpJ3ekWhwsh0V5KAOG2QmUJ6uJUR8pBRjqBEZzgDAAVWw30e+lT9OT6r6kmmb5BqLfUROU+DCUSyeGy8JIkTR/u5eoTHnP4jOTwLZNks89F29Ju1076As9y5c+Z6fh1LxxM/zYZQkjCk58kYEWJjIKmE3Ds0OU+yYaNLrjdLdRAlmPp+u2vLj00Iw7PH8ADx4aDT1Dt6vrqADv75IALbAtY/jgiq6QYuyFY6FzRL6qA+glrIH172Nru8O/i9Qei2J885kDtnEzS3TRYtR/uvHSjgdQlUBZLcAKdJ8GDWyGoQIDAQAB";

    public static final String SIGN_TYPE = "RSA2";

    public static final String TIMEOUT_VALUE = "2m";

    public static final String PRODUCT_H5_CODE = "QUICK_WAP_WAY";

    public static final String RETURN_URL = "http://kalle2017.iok.la/api/public/alipay/return";

    //测试支付完成后返回地址
    public static final String NOTIFY_URL = "http://selfcode.wicp.io:8088/api/public/alipay/notify";
}
