package io.ehsan.snappdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;
import java.util.concurrent.CompletableFuture;

@RestController
public class DefaultController {

    private static final Stack<byte[]> memoryBuffers = new Stack<>();

    private final CustomFlagHealthIndicator customFlagHealthIndicator;

    public DefaultController(CustomFlagHealthIndicator customFlagHealthIndicator) {
        this.customFlagHealthIndicator = customFlagHealthIndicator;
    }

    @PostMapping("/change-health-status")
    public ResponseEntity<Void> changeHealthStatus(@RequestParam boolean status) {
        customFlagHealthIndicator.setEverythingIsOk(status);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/increase-memory")
    public ResponseEntity<String> increaseMemory(
            @RequestParam int megabytes,
            @RequestParam int sleep) {

        var x = new byte[megabytes * 1024 * 1024];
        memoryBuffers.push(x);
        x[1024* 10] = 0xf;

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(sleep * 1000L);
                memoryBuffers.pop();
                System.gc();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok(megabytes + " MB of memory allocated for " + sleep + " s.");
    }
}