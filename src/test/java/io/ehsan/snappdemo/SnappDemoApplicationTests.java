import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultControllerTest {

    private DefaultController defaultController;
	private CustomFlagHealthIndicator customFlagHealthIndicator;

    @Mock
    private CustomFlagHealthIndicator customFlagHealthIndicator;

    @BeforeEach
    public void setUp() {
        defaultController = new DefaultController(customFlagHealthIndicator);
    }

	@BeforeEach
    public void setUp() {
        customFlagHealthIndicator = new CustomFlagHealthIndicator();
    }

    @Test
    public void testChangeHealthStatus() {
        ResponseEntity<Void> expectedResponse = ResponseEntity.ok(null);
        when(customFlagHealthIndicator.setEverythingIsOk(true)).thenReturn(true);

        ResponseEntity<Void> response = defaultController.changeHealthStatus(true);

        assertEquals(expectedResponse, response);
        verify(customFlagHealthIndicator).setEverythingIsOk(true);
    }

    @Test
    public void testIncreaseMemory() {
        int megabytes = 2;
        int sleep = 5;
        String expectedResponse = "2 MB of memory allocated for 5 s.";

        ResponseEntity<String> response = defaultController.increaseMemory(megabytes, sleep);

        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testHealthIndicatorUp() {
        Health health = customFlagHealthIndicator.health();
        assertEquals(HealthStatus.UP, health.getStatus());
    }

    @Test
    public void testHealthIndicatorDown() {
        customFlagHealthIndicator.setEverythingIsOk(false);

        Health health = customFlagHealthIndicator.health();
        assertEquals(HealthStatus.DOWN, health.getStatus());
    }

    @Test
    public void testSetEverythingIsOk() {
        customFlagHealthIndicator.setEverythingIsOk(false);

        Health health = customFlagHealthIndicator.health();
        assertEquals(HealthStatus.DOWN, health.getStatus());

        customFlagHealthIndicator.setEverythingIsOk(true);

        health = customFlagHealthIndicator.health();
        assertEquals(HealthStatus.UP, health.getStatus());
    }
}