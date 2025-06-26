package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101400684531";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAcUHY1vAlQLiosF/1Lm3BjztqKSZBctzPuWljgGOdhxXMCJsjvl1AyjvcRDQ+YyQQk5Dmi69IghJdjfFLhPwll3W8aMvWylWuie4P+dO7J7ZzJBRzOJaESzAz2eRu7AcKIOlERt8G6fDveZyfT/HNiIx1x7O6ho6hJSAIYWwMuBu3v0Mn5gaEm5YDcTeJhCUfPkRdYqMv3eJKmZ5qgn6u+nUKUdJXQhSfuk1O4gZ1dRuIIQCUulYWl3Por/vL5Cz/WJhPDraRYnIQ6KZEJ8NTmymjTfunAx6nKJw7yPkpOOXK0jlYGXNSZDCvzDZB968vrAHl4Xn+L3iTKOgA+9InAgMBAAECggEAGkOOfGGPH375g3z1UjRZOyAnvRCHAxESt+Mo+rCxLfAGc/86gfeWJ/5yeRr1N+Cb6kvScGIVyJTYXor80T1zNdifuZQQQm6DULphlKC4O+fl9etlxx3GIW0igSfH/yqCUf7i1BRN3KMPolhSv3zga+/FEPxx8u060xa9Uxlgt8TOXx8MXsNHV3C+CnXAHvopz2j9dmzfimTXa1nzVrL3CiEI1jrDRHNK9qsRdThCCEt0ksYCKTDR2BpNlkpRi9rTnp2tNpRJYxphgQB46FpXuwzAWQ1ZzTEIKNtPkems5oa9KM4wfctSm8l6/VjbVedPANeyN7Ss8qvyWj3OnsV0WQKBgQDbMtoerIO8BBT4cVmSF9r9bS0OsU7XcyIMlzPk5fJ7hI1HvVoL8LZmnfpL6+sFpRAOhIynPNCG81DcTyIAMEH8IuhDWskGhLwwE2+VyZLZlrSixr1XU9WF/3G0BrRIAOcJ26umeM0I04X3oyhxfMmOWoOCEEelgKbUjY0S5SiQAwKBgQCWAbTXMdMmXUxXjdsAiFSlsDfRwVoJ1g6couKgWMDiCgueSkgwecpxxpFy6RfClbhN8N3SEiY7PlKm8oTzQbBzp/AdjOpHvUL3tcx4U4yjy0uEXOLOkgyn66yVUJ9sBQsWm712ivFmv9EVjyea1/cc/XPIiG02QM6iQsYNhy7WDQKBgHcKI8h6ucP2l4jgzdr4F5Z/nvOWuRFqVjhuOOF7O3d24d7u5yMK45PRAWgPw/+5x4GK3HPKJBjVtLkgRH/DtWH/dxF1ESz4EtctBvFameSO4qRvt83gGlp+AjrZVTixPMduw8ZRXlAHx35X3hPbx/4GC9srii1IM9HE2bNEPgYPAoGAEMHGCtgFB18avRkaoteX5R5nTeY0ga7I2RSVDmZ7vYRzwc34fHnOdKFC9Dw3rEP/X62Nif7/CFUw9HdZBLeM146mExb/wN3SkktbuQFUT6PRHsslhv/nrAZ2tANam72AfJkPNCPXTawV1ju0p+aCM1nYykj7MjlRG+0rpK06PS0CgYEAqotQLYPZfau/OsgNIpIXuQrd2K2FEfThHk5oFSlgJ6x90fVsumdaskQsNGK+nvajO3Hz2HssQb6psSLcOhTMSPTnarFwriUnBgcUxlY1lY0y/uvFRAh9YzVRXH8UbE6UOlLC28EarZBC5GMspq/cVwTvAEaq0fBIvA2fpQ3IHMI=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwE9AmQ8vCir3joLoRHCtOpRGT23+NMYtsSG5R3cK+P4rkWQ5lH6ZIcTUOcjDGMpmbrUP2nHqZo5eHa7+rUKzkgnIUhgL4TkpTk3oBFofbT+UVajcdstYTfu46bF4tmH3QD9iqZ4Q368kAAUbyeshWyQlAhBZORVFGBo1I9BgXp0Lh1xjHjS4K0WCP6oisKbpQ0Z1HbC64nPVgxg07g5xlwaeOrAkWbOTbL/y+Oe2tDnuUhObWP+VLKrDt07C/QpcKNVWe6cM+asAUiV6wu2etdrerg7eVoqgXSq0gdegy//3WJafz9jq+eQOxsLTb7DJPh9p7iV648PjuugmlRZhAwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:9090/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:9090/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";

    // 支付宝网关
    // 开发环境https://openapi.alipaydev.com/gateway.do
    // 实际环境https://openapi.alipay.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

