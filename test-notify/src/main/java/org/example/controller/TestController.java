package org.example.controller;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.example.model.NotifyPaymentData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestController
@Slf4j
public class TestController {


    @PostMapping("/testNotify")
    public void testNotify(HttpServletRequest request, HttpServletResponse response, @RequestBody NotifyPaymentData data) {
        log.info("获取回调数据:[{}]", data);

        PrintWriter w = null;
        try {
            w = response.getWriter();
            w.print("SUCCESS");
            w.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (w != null) {
                w.close();
                w = null;
            }
        }

    }
}
