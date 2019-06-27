package com.weisen.www.code.yjf.basic.config;

public class AlipayConstants {

    private AlipayConstants () {}

    public static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    public static final String FORMAT = "json";

    public static final String APP_ID = "2019061965597545";

    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCLVdCrn6BPzn/05I57J2lcnac0jIlKbXSfdEAJTApM6f7hQudq+vWbECnT6KJwQJpIve19rFGy3CgP94ostSJU/bv4yGWlMxxx7XQ3eV2BXowj2mnD35Xlbrl0Q0UJNV3kPmRs3QvFQZ0YWcRpP9avprVhUpK9f6bsn76WYTLlHaBxX4Bjfixl13bfgfWZQ+ooZchnwMwyIlXVH9vdd7d6k+cph9EtZnyBGf8CUTOmpSNpUOk+BmU8cR5bHv/bnirYJmUYjNxZHC7J+TqrkRz/HbkPxXr5iqc6sZfMvx5URaHSeN1MKuEUYhmzMNSaHa9kF7gSkeuKOaB7HZ1j2ENNAgMBAAECggEAQ0okRl4Gq8QMY5ZlgPBdn867S1N7kigeJEueCV9hshdnch4/aJjeOA6sRJyaph9s4yhmI0UvB3PvHRbs8f5gIC+RseoFvKxcVmDYuVmxYKLqhv4aCfBWkcDRa2Z5VqMhPvMqyLBP2OmBFptzkWi1I4sdGW0pVL7rPGdBQZqbW64jF2gPfQ6u08M0yZJMnhhCwlT1fEQL19TLvaaguUzDaVLnnmAxXABsoT/M5iawOFYTlQFaNGXL5FYxwD2nm8Ko3nxupn8+sL7c4Hkb5TtvAgMxtfx5fjiB3ECiCQ8oI4JErmU71PzNnVTtxQFIHJ9DbTqZACmzT3GsBiRryq+JuQKBgQDmkpNGs3iC+eSH9diaTQGMd6OD+5zXmseMAjhq47lLmRO4OecOCQFZBc+GUGM5lqHRX36cZTUirt/EGLq57k/40LYGt6qDDc/3DB4ip4L6ZhVLwjeRDKR7x7Il1EEx2CQcwhiUmR8TDcf+YAz+QGUB4QE6DYKAAhskMiZtAuVmkwKBgQCas3dzckX7c4khs7gHD7fR3lC9MIQcOcg2bqLt0EUzrnW92VJOaGeXppv4PADJntpYTmd1OgIpFuShd3kYFhXYl/xKOwwTYKOC9B0HLEKkvubH+zZWB7yUAsIduUNFzvxkZbnvSaXzLuDOpYlrX4iyVCyvZtw9kFQ6xRqRh0b6nwKBgQC+7CItihDBC6FTf5wS8K7WnH52nFNcKJsRcQk8YUKeuaudgby0X3qRN4GGq2vuVK+7ikJxyFA+XuWFICEGMrJfyprfZxg3GqnY3Fzbvy9ynjMGH1UZs4lDwbDfKCmv11TXx1TJPx1/DotLNEeuBK5e5uy4IFWuH5BP4YL2A07nJwKBgHpP+tmkOACfUIT8+ztaChQ8mcl7WniWlt2yi89LI+vAvAbmlFS5523XpOmXR2gh2LCJDPN6ccra9tlTBMMucwdU43wtQ1buyVj4eRr6u5ZV+VGUJVnPtCGyLJgjqjS/en5Gj8PJjxO+tK+t4gCrAAE+3DWyNd/XBUGSJXd6ZXwxAoGBAIUeYi8B7DOqkeMFfZQ99kba3TVlBO47IeNv77ExWgGFkVPBt0u/xG7h0qcUwtw4mH3JBTboidrlSxJ+82fspjTybcDhxR+bqeWoUiZA1INF7dshBE3aPsVjBu5oKC7azSXALsQNRbEwr/9YjHqcY3FusKCP5xuOUusw5lQk++7k";

    public static final String CHARSET = "utf-8";

    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoNq9VjM1JuXtO0DyqINqZdGxUg7s/0EPeUC/ez3KOZlpJ3ekWhwsh0V5KAOG2QmUJ6uJUR8pBRjqBEZzgDAAVWw30e+lT9OT6r6kmmb5BqLfUROU+DCUSyeGy8JIkTR/u5eoTHnP4jOTwLZNks89F29Ju1076As9y5c+Z6fh1LxxM/zYZQkjCk58kYEWJjIKmE3Ds0OU+yYaNLrjdLdRAlmPp+u2vLj00Iw7PH8ADx4aDT1Dt6vrqADv75IALbAtY/jgiq6QYuyFY6FzRL6qA+glrIH172Nru8O/i9Qei2J885kDtnEzS3TRYtR/uvHSjgdQlUBZLcAKdJ8GDWyGoQIDAQAB";

    public static final String SIGN_TYPE = "RSA2";

    public static final String TIMEOUT_VALUE = "2m";

    public static final String PRODUCT_H5_CODE = "QUICK_WAP_WAY";

    public static final String RETURN_URL = "http://kalle2017.iok.la/api/public/alipay/return";

    //测试支付完成后返回地址
    public static final String NOTIFY_URL = "http://kalle2017.iok.la/api/public/alipay/notify";
}
