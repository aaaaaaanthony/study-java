package ssl;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class Demo {
    public static void main(String[] args) throws Exception {
        // 创建信任管理器工厂
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        // 初始化信任管理器工厂，加载受信任的根证书
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream trustStoreStream = Demo.class.getResourceAsStream("./baidu.com.cer")) {
            trustStore.load(trustStoreStream, "truststorePassword".toCharArray());
        }
        trustManagerFactory.init(trustStore);

        // 创建SSL上下文
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        // 创建SSL套接字工厂
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();

        // 创建SSL套接字
        try (SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket("baidu.com", 443)) {
            // 打开SSL握手
            sslSocket.startHandshake();

            // 获取服务器证书链
            X509Certificate[] serverCertificates = (X509Certificate[]) sslSocket.getSession().getPeerCertificates();

            // 在这里可以进行进一步的证书验证
            // 例如，检查证书有效期、颁发者、域名等

            for (X509Certificate cert : serverCertificates) {
                System.out.println("Subject: " + cert.getSubjectX500Principal());
                System.out.println("Issuer: " + cert.getIssuerX500Principal());
                System.out.println("Valid until: " + cert.getNotAfter());
                // 其他验证逻辑
            }
        }
    }
}
