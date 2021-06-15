package com.macro.mall.portal.interceptor;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

@Component
public class ImgInterceptor implements HandlerInterceptor {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;
    @Value("${minio.localEndPoint}")
    private String LENDPOINT;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        // get object given the bucket and object name
        String requestURI = request.getRequestURI();

        requestURI = URLDecoder.decode(requestURI, "UTF-8");
        //获取对象资源类型用于传输内容
        String[] split = requestURI.split("\\.");
        String type = split[split.length - 1];
        String type1 = type.toLowerCase();


        //截取对象名称
        String str =  "/" + BUCKET_NAME + "/";
        String objectName = requestURI.substring(str.length());
        ServletOutputStream out = null;
        BufferedInputStream imgStream = null;
        try (InputStream stream = minioClient.getObject(BUCKET_NAME, objectName)) {
            // Read data from stream
            imgStream = new BufferedInputStream(stream);
            out = response.getOutputStream();
            String contentType = null;
            //构造相应头
            if (type1.equalsIgnoreCase("mp4")) {
                contentType = "video/" +type1;
            } else {
                contentType = "image/" + type1;
            }
            response.setHeader("Content-Type", contentType);//http://192.168.199.253:9000/img/20200811/mp39948414_1446715448021_2.jpeg
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = imgStream.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } finally {                //关闭输入输出流
            if (imgStream != null) {
                imgStream.close();
            }
            if (out != null) {
                out.close();
            }
            return false;
        }
    }
}
