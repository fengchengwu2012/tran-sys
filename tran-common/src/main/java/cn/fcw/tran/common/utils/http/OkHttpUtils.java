package cn.fcw.tran.common.utils.http;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fengchengwu
 * @Date: 2019/3/22 17:37
 * @Description: ${TODO}
 */
@Component
public class OkHttpUtils {

    /**
     * 使用代理请求
     * @param ip
     * @param port
     * @return
     */
    private static OkHttpClient getProxyClient(String ip, int port) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.connectTimeout(10, TimeUnit.SECONDS).
                proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port)))
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return client;
    }

    /**
     * 使用代理同步请求
     * @param ip
     * @param port
     * @param url
     * @return
     * @throws IOException
     */
    public static String syncProxySend(String ip, int port, String url) throws IOException {
        OkHttpClient proxyClient = getProxyClient(ip, port);
        Request request = new Request.Builder().url(url).get()
                .addHeader("Connection" , "keep-alive")
                .addHeader("Cache-Control" , "max-age=0")
                .addHeader("User-Agent" , "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134")
                .addHeader("Accept" , "*/*")
                .addHeader("Cookie" , "add cookies here")
                .build();
        Response response = proxyClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }


    /**
     * 表单请求
     *  FormBody.Builder formBuilder = new FormBody.Builder();
     *  formBuilder.add("app_id", map.get("app_id"));
     *  FormBody formBody=formBuilder.build();
     * @param url
     * @param formBody
     * @return
     */
    public static String  httpSyncFormSend(String url,FormBody formBody) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(30, TimeUnit.SECONDS).
                readTimeout(30, TimeUnit.SECONDS).
                writeTimeout(30, TimeUnit.SECONDS).
                retryOnConnectionFailure(true).
                build();
        Request requestBody = new Request.Builder().url(url).post(formBody).build();
        Response response = client.newCall(requestBody).execute();
        return   response.body().string();
    }

    /**
     *文件下载
     * @param url     下载地址
     * @param dir     存储目录
     * @param appName app名称
     * @return
     */
    public static File download(String url, String dir, String appName) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        File file = null;
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            file = is2out(response.body().byteStream(), dir, appName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("app下载失败" + e.toString());
        }
        return file;
    }

    private static File is2out(InputStream is, String destFileDir, String appName) {
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        File dir = new File(destFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, appName);
        try {
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
