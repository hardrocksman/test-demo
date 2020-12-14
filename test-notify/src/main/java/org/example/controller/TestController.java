package org.example.controller;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.example.model.NotifyPaymentData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.corba.Bridge;
import sun.misc.Unsafe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class TestController {

    private AtomicInteger value = new AtomicInteger(1);

    private Integer vv = 1;

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

    @PostMapping("/incredy")
    public ResponseEntity<String> incredy(HttpServletRequest request, HttpServletResponse response) {

        return ResponseEntity.of(Optional.of(String.valueOf(value.incrementAndGet())));
    }

    @PostMapping("/incredy2")
    public ResponseEntity<String> incredy2(HttpServletRequest request, HttpServletResponse response) {
        int c = 0;
        synchronized (vv) {
            c = vv + 1;
        }
        return ResponseEntity.of(Optional.of(String.valueOf(c)));
    }
}
