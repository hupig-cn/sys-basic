package com.weisen.www.code.yjf.basic.config;

public class AlipayConstants {

    private AlipayConstants () {}

    public static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    public static final String FORMAT = "json";
    //APPid
    public static final String APP_ID = "2019031963563747";
    //APP私钥
    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmfuDI0cnrPs7n\n" + 
    		"Mv7erx+GBwFb1xWjGadJvTHgX9honBAw57DzrWtGmNBNWO9JbwzbnrZlT7EHtZVm\n" + 
    		"XtvynBU8b4iDfcmXVkwS+sn5314nIcGCpwHE2OC4SGtmAaXSRPzQx1Ntbm6KwQ07\n" + 
    		"OMuQ0j0tp8TbnBL2xi9NA2HqYXFVXNpUPCb3okmcMeTBZkC0Gf4Lkk44ulOrZoWW\n" + 
    		"zrPjsq3HxGZNCLxBm7QL4YGMoRQvK5UWPcCpMxVe9VGi4RKb7DxhlHZQL9YgFqmd\n" + 
    		"c70pCaJ3y9P/lYFg5np08sJbt8qX8txi+QeIuPanHwt8fcjgifBWB+/UYCAbUnpe\n" + 
    		"YYrZk7uBAgMBAAECggEAMe+L669AKFZGKAGbf552jfQ77BeuXaSsD+m2s90LQxTL\n" + 
    		"pl9XKz6wFhRvJUOvQQ8VhCkt+2TTa8SCcpmwzisSE3ZAM2/ero0rc+XT/iWIT6RI\n" + 
    		"La95UFjbzWmrDIUh/tbCHLwrwX6FmQgdt81pAJ6Nysuut08wQONf/HEvROTE4Rzk\n" + 
    		"VKqkf4SuULs5UPA1spYIic9zs16O5hShNNrbAQBH4VgjMSyAHjquGfC/c+0G4l7K\n" + 
    		"KtrxtxQHuwKqpnyoyMFOfp0F4YaJng1zU4NikqnQbyMtGXkb673QuxZD1vFUJ/P8\n" + 
    		"Dymf8KkUEjs6M8DKURaC13WBXvcXpFQJAAeklX84AQKBgQDVo/ZlvUAj7snAo/v+\n" + 
    		"og8P8CjZwr8YOZ51kS3ASz0jYzHge+BgIeQP6MeOC7B38oTdFYaRgq+iTVCb5sBV\n" + 
    		"1yXpGOjPIBULF0o4vcODrW744jCa2xbOgkhZG6ALKhqiVjSTkQfav9mysN+qMqJj\n" + 
    		"iC3wknw0G5lU4JFIhNOJqKP/0QKBgQDHgeudC5X1eFpcbbow8lHYH+RvHD4YEBuH\n" + 
    		"nXznJX4Sp6v9nvDpr3dMWOEl+dk8E1E43fP5DGP/vaIitG05T9gMOdRVan+23ZIt\n" + 
    		"+EtkOh1HbEybcHlrOfi4Gmd2vDntDxqmjU9FqCR92X/r4JWCE+T15FygrYSLu2JP\n" + 
    		"fdt2pyUcsQKBgQDE2INycLLFqQJqJm5My0N3mv/4zTDIAGcZyn/OO0UE8yQEw/8U\n" + 
    		"dIWvhvs/zVN24ZU8I9ZPAowTzoskarPzI/7BTPtNxTC+fGId7/NSw52JYtgIBzB3\n" + 
    		"bKF+wS5wuwW5aBaflTUYgEb1F3WuW2M2tEYn8MDSPk/EXfpfSEuk/ibF0QKBgQCe\n" + 
    		"UQTNfLIlNrpN+mz0BnbYEIbPfRPxOGcAOQMy0de3qgKbohL+/dSnayhPYHL4b0PW\n" + 
    		"4pqGGUUJXkxZSeAayLdjDEjo9zgdvroPbKc3A77UPKaV+qz/X+N1BM7xs/Aro/68\n" + 
    		"CArN9d3vWDdg0LkTngHk9rBieQ9javFarz8GayDSIQKBgHrMpKt/v/gqDZ9BnOIn\n" + 
    		"M+rno47cbC8oiM/x7zIuCq4WPqhYf2qIp8tZnDKVF2HIrwctNoVFg+agSuE+mwJS\n" + 
    		"j2UQKB5NeQcq5GCoFRr76cGe1bWaNoc0WbF8452Duibr1p7TqjfvjpRJq/4PbjWr\n" + 
    		"cxPyuyxucf70xiyOeexEp+Bb";

    public static final String CHARSET = "utf-8";
    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoSIPAiFxxyyG/77+BrARuv6vzzqSvLbAw1tr5oH7grr/MZzfLpulV8J9od0GwDSCVa0XepYC4F/VWNLL4YvZvowvAU7Nx12M+UrXdnEVP1grILJGvtm8GLqy1qI/NXcjtP+78yzZRRVmcx9K3/WXEyjrCpGMbBmquEIBSXHBwbDWPm+AKxAWatFU9ZR1F82+KK3RNr4b/kfWdSOqCmDb0hc//uvYscPydI8MLvZC3EOCgyGEGvUV2AlllUIqOjkassn7nJnzC7YnNe2UPEOj8wlaVOe3KK+Adoi+a4vPUeAZYw65qpPMcCx/d7ig8L7JvvYWHby4BPZ3IkOpSM3l+QIDAQAB";

    public static final String SIGN_TYPE = "RSA2";

    public static final String TIMEOUT_VALUE = "2m";

    public static final String PRODUCT_H5_CODE = "QUICK_WAP_WAY";

    public static final String RETURN_URL = "http://app.yuanscore.com:8081";
    //测试支付完成后返回地址
    public static final String NOTIFY_URL = "http://app.yuanscore.com:9090/api/public/notify";
}
