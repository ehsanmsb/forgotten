package io.ehsan.snappdemo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomFlagHealthIndicator implements HealthIndicator {

    private boolean everythingIsOk = true;

    @Override
    public Health health() {
        return everythingIsOk ?
                Health.up().build() :
                Health.down().build();
    }

    public void setEverythingIsOk(boolean everythingIsOk) {
        this.everythingIsOk = everythingIsOk;
    }
}
