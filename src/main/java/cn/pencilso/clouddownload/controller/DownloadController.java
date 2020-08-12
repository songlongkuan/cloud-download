package cn.pencilso.clouddownload.controller;

import cn.pencilso.clouddownload.tookit.IOUtils;
import kotlin.Pair;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author pencilso
 * @time 2020/8/12 2:02 下午
 */
@Controller
@RequestMapping("/api")
public class DownloadController {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping("download/{link}")
    public void downloadFile(@PathVariable("link") String linkEncode) throws IOException {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();


        byte[] decode = Base64.getDecoder().decode(linkEncode);
        String linkUrl = new String(decode);
        Request request = new Request.Builder().url(linkUrl).build();
        Response execute = null;
        InputStream inputStream = null;
        try {
            execute = okHttpClient.newCall(request).execute();
            inputStream = execute.body().byteStream();
            Headers headers = execute.headers();
            for (Pair<? extends String, ? extends String> header : headers) {
                httpServletResponse.addHeader(header.getFirst(), header.getSecond());
            }
            byte[] bytes = new byte[1024];
            int len;
            IOUtils.copy(inputStream, outputStream);

        } catch (Exception ex) {
            IOUtils.closeQuietly(inputStream, outputStream);
            try {
                execute.close();
            } catch (Exception exception) {
            }
        }


    }
}
