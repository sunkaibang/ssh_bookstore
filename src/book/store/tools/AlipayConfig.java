package book.store.tools;

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
	public static String app_id = "2016091800539739";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpgRYZw8eEGdxzYLs3DR01SSGX/RrAa949Yt8OpcL4dvNPa8cusjiEUPlSNnb7iF9e8Wt2xVx9ymWZVfsuo4vhEVxVY40FyAfplj4RLXEEVNNhODx7uZmMQJqxqL9qUa2B2t5qUGP42xYB8XjZE4k5GalivV3aXqSX87tFNOnpmtIgRx9TnVOV5VJofjizgiJW+pWcNkOzmxVGQUUGig77xbAJpb6l2prYFuL1PzkO6KL5q2r1XY2WIAjHLhvyTcDPA2HBUDppZFrdjPogLgDE0Pnvre4V5Kos37+mEhCDCAm9t6uHoZnFny5tM6l7AbaIRFXjEXMGkLLK6KjiBxiZAgMBAAECggEAFsPxlfBHgaR/iTd6rYAjZh62VlV2HFXgeVooHy2ALCK9tvMAw3LISG0frz0Fae788CF4j3EeZP6j+p4jqDXq+YV9YzKFpDsjZOn62A/EWBKUeUA2T1+0EbQzkpzZaIwjw4cDzD+Tzb6PDB/EXVNUYMjjiRFp44RGkun3QupCaE9cdscjdl+uPLesiDU4YLMylVE0DsCqne2VgiQp/POntiGwfFlU2Q589pouqSpjS8zBvJHw/kBz6QId+AJUWXarSwkuShX1V0iahgX8ZJUK8BQM7zeNvG+1Dfn4I2cozTcnebudZmyPbWTe6vl0G28AYcq7+T9oOrxgI9rY3KTNyQKBgQDsTXzbkDNZwB2XJcQx0ce0jIzlki1gAt42nWnIZGGKWFhZlFqB0cQ90HtipcpkDj7b7gVoWXkQNFtrXmGT8gkjJ+rvaud1SYEA6F50y6XlLbvgdMjncl8Ls2UNy42ENG8hnFGoUIlTVZPPHMQyq/Ewh66jJfCcAM5GFEOnN8s/CwKBgQC3oiw2sIi6tegJcVOw+BPL82KnX5RlY5mxtAUHcw1VhyyxyCGcvLkPdZIoUfw8MC92Yp6xkxEolnfNzU48nt06bPil5XBKWkp9bB8m9tamO0N9FgQeUEyDJP0a+4UQzd8YngWnBFCWMCqob8cI2CouYJaxolSgDIwvoTW1iX6dawKBgCVfV/jtxIFUVhm6ml36vKf8dqg0R0LhET34BWvxHFAiCs8YHHxdrbjNCUafy/OpIR3tdiN7yx7Z+P9xLOOWKwXJYWm+MZ8mIeX/2jiEoNcvNYCZV9DrEMcr9zaIU/htxom9TFjfa+inJzphZVJSSVIQk3kwHe6+XeFNgSmoNFfjAoGBAJeU5M5YNuV41wtEYwNIJH9Mbvkeas6uwuXvZd9aV31sUbaHL0fEeTaLpJRn6MB+Pg+JJNoMPjO00Twu5xAfSxq5bR70IBcgJTEXGTf+tf0OGo2cqNWlDyOKWuv1B0hxyGD3wE63vxXauLvFWRSx60/RJ1OGXjinYujGOCGcV8n1AoGAZPosKp+KO8mD2v3CXdNX0jguqwQrJqESDXy9l4LNm8hyNOYd406Y7wdB0jSsTRAMN6ON8EXbhpuBnZ9MsSsS0c8+BEDX8Pf0ZRw2AvJ8MtibbFo/j7kthdEP6ON+X/5djVTuSCzZ8dKDVCx+HSKc79sqgxSEht9+cKppV37z7AA=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu2xejkYdQaLR/Bwj23lT+PTsid8MdiiuK65LrSSiys/BwBoOHK89NRfRxyXHdKesu222i/TAOdt1w0GKfOU6c59ImQdfwLgRpl+32lgE05Mo3q8QfX+qj2uED8VgEzgDcWr4MzIPKmNqgwnH9pkYKn2ynMM26cXCNcvhX78ND3KmQuaGpubVzG4hj723R4ivY9p0xrvKD2Kqj96z5fp193mpoxx6XveGww8kZh7YGEk/lT/1kaJk4M/CQla7mXy4wiXtzKMqdU+Qhpt0+Rb5Gc3OqBGUE+HhpIRtiYBqiMlkkY1b6J6MPQ4LyHaofLD+pa1B7GCwHo2gBzhqw7ULYQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://jtu2di.natappfree.cc/ssh_bookstore/order_returnUrl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://jtu2di.natappfree.cc/ssh_bookstore/order_returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "g:\\";


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

