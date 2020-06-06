package com.test.network;

import com.test.log.L;
import com.test.network.request.BaseRequest;
import com.test.utils.Conversion;
import com.test.utils.Utils;
import com.test.view.MessageBox;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class OKHTTP {
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3,TimeUnit.SECONDS).build();
    private static X509TrustManager trustManager;
    private static final String HOSTNAME = "www.test.com";
    static
    {
        if(Utils.isProdEnvironment())
        {
            try {
                trustManager = trustManagerForCertificates(OKHTTP.class.getResourceAsStream("/key/pem.pem"));
                client = new OkHttpClient.Builder()
                        .connectTimeout(1500, TimeUnit.MILLISECONDS)
                        .sslSocketFactory(createSSLSocketFactory(), trustManager)
                        .hostnameVerifier((hostname, sslSession) -> {
                            if (HOSTNAME.equals(hostname)) {
                                return true;
                            } else {
                                HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
                                return verifier.verify(hostname, sslSession);
                            }
                        })
                        .readTimeout(10, TimeUnit.SECONDS).build();
            } catch (GeneralSecurityException e) {
                MessageBox.show("HTTPS未知安全性问题");
                L.error(e);
            }
        }
    }

    public static Object send(BaseRequest content)
    {
        Call call = client.newCall(new Request.Builder().url(content.getUrl()).post(content.getBody()).build());
        try
        {
            ResponseBody body = call.execute().body();
            if(body != null)
                return content.handleResult(Conversion.stringToReturnCode(body.string()));
        }
        catch (IOException e)
        {
            L.error("Reseponse body is null");
            MessageBox.show("服务器无法连通,响应为空");
        }
        return null;
    }

    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException
    {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        char[] password = HOSTNAME.toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager))
        {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            MessageBox.show("ssl套接字创建失败");
            L.error(e);
        }
        return ssfFactory;
    }
}
