package io.justride;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface PodBinding {
    String POD_SINK = "pods";

    @Output(POD_SINK)
    MessageChannel podsChannel();
}
