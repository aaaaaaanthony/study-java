package ssl;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class Demo2 {
    public static void main(String[] args) throws Exception {
        // 创建一个SSL上下文，不验证服务器证书
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

        // 创建连接
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);

        // 现在可以使用HTTPS进行请求，而不需要验证服务器证书
        // 这个连接将忽略证书验证，仅用于示例，请不要在生产环境中使用
    }
}

