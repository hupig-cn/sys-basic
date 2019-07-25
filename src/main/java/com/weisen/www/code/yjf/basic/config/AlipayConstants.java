package com.weisen.www.code.yjf.basic.config;

public class AlipayConstants {

    private AlipayConstants () {}
    //沙箱url
    public static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    public static final String FORMAT = "json";
    //APPid
    //线上
    public static final String APP_ID = "2019061965597545";
    //沙箱
//    public static final String APP_ID = "2016101100661893";
    //APP私钥
    //线上
    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6UNqfYYEcwUyI\n" + 
    		"TgI23Ravne4tD/w8Rm6XeIFaRx7qeo6VdmQdULLRFH+viLlu6hHYuNPiu1zF2CHK\n" + 
    		"nl/E6neo5i/QPv69Ff43uT/UOfNnJ9BaH4/dMsrmJJEse6LwjxCVlUCIejd8TLbY\n" + 
    		"pQoV4F4Z909VQex9Xyfewu/v1bKysNMEnd9TPpPUW4O6ZtOJkVkPmPfss3aWFfMb\n" + 
    		"nhYF/CfPxr2hCEJLzwGlsd4F06y7Ei/5jd54K0dENAOHe4bY0x5N7GeHLKWLz/i9\n" + 
    		"LRIfd6f6sQdyOrnZ5sFX7G0oK9AwnQ9fptGarZ4w2gtlHFhswaoyQ1MqgUOJuHPy\n" + 
    		"7OBMp4cHAgMBAAECggEBAJ7CbsgIqZ/7Bl/POQvO8JnMlTtIt8IUsf/X60EmAS4E\n" + 
    		"KKMrMrvYG8cVFIhalMfyJCrrs4Er7i2XsZOZyDlfIX+GRY/JXf95UhnhfIY2qzVn\n" + 
    		"M57X7hqqQUJCPoINCBa7kYIkn8yrd8QvCk8osqNZgFXX6n3XotBh2PN0YEeZbJ/d\n" + 
    		"nXkVMA+rrIvIdAzMH6UMXWi//s7186ArW4b9BaK9Nms2Ocm1Yy9rehuIdg8KcS4d\n" + 
    		"cmiLMm4qKzC+O6+pDxKzi31cyfg3Ra7mrxu0yr4RXTtaNmkkz1ztC1Jyjx4UBkxA\n" + 
    		"jBPmRygjfmNrrOwRq5L7ec9DvvL5Hf4QYZPgot1DM4ECgYEA+BRv5PAUXf6qrzO2\n" + 
    		"/t7IYCeDOCA802byoE7u6g5TBiDB6ZcaQf8a6KR3KP6NMoJcJrE6FGxRDp+9MdzI\n" + 
    		"nNG/ANZoXRyy1lqDB2AOI2TqPaQdj83GqJU4T83PXei9Srnoubz0yses2Vl5PyFf\n" + 
    		"tT2u3uXduPgNS5HXRZmimdqsnNECgYEAwEOeP8Bj7vvdBcqWUn0lDGS7Z+WZhT+u\n" + 
    		"s+s2ZxrMm6PGg+hEYbglGbATxVUFYXuOE/a4oMOQ54/m7Z8pYCw3iCC5+OZRHH2U\n" + 
    		"dygJSK0Jpn94Oqg7/b/+l66v0fHoXhyVmbyAuSzXVaI11U5JdzWRqaytH7/1LwAx\n" + 
    		"kjpmnH25fFcCgYAKLMxqF26+bDOT/AsFaR3O09AN/USBZzeMW7Bw6SV+mtlZO1lY\n" + 
    		"W54+7H/2rnocGDyvWZWIQOkSMHsSEHwp8nBjGGgvaX3sTMoTA12RFhKFdLXBsC71\n" + 
    		"uykUHNzkfSdGaVmiRJ3Fs2rjf0f5zuSeCmmhusvdSk9BSjoKIMMKjG2IAQKBgDBK\n" + 
    		"i5v9qdwj/N7whJNRhZW4U24EOGxEk4/zW2XIUG4jU8Bb/ZkB1KfAFycwdLqIeyY+\n" + 
    		"iR8wH9xZw6oZmR3j3Ure1XdT+jmKvVi0gKh1dgAQ8lThXrgcmkjlAppcBX5l9Zl4\n" + 
    		"xGg+gBOtO5JJKKC9nsmcapuX7nCQvuK4sn9KAOXfAoGBAMRWTtjiHD/Mk/zJv2NY\n" + 
    		"kw4RRWz5/S6WmznvOrIN5ZuFe1eNGnAsPOJSkJKgEpzXCGpzeS8182QivL6+VHOW\n" + 
    		"mQ6+F0ELYij5AQjz9PXCIa5vcFK9STZo/L0+Ey0oEih0Jilm04pJj/d5l/k0j3bo\n" + 
    		"0/qNMLyo7kH0XFbJAw47DuIa";

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
