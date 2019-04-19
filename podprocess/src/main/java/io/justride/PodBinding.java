package io.justride;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface PodBinding {

    String PODS_IN = "pods";

    @Input(PODS_IN)
    MessageChannel podsChannel();
}
