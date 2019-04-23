package io.justride;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(PodBinding.class)
public class PodpublishApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodpublishApplication.class, args);
    }

    Logger logger = LoggerFactory.getLogger(PodpublishApplication.class);

    @Autowired
    PodBinding podBinding;

    @GetMapping("/{uuid}")
    public void publish(@PathVariable Long uuid, @RequestParam Double lat, @RequestParam Double lon, @RequestParam Double speed) {
        logger.info("uuid={} | latitude={} | longitude={} | speed={}", uuid, lat, lon, speed);
        PodEvent podEvent = new PodEvent(uuid, lat, lon, speed);
        logger.info(podEvent.toString());
        MessageChannel podOut = podBinding.podsChannel();
        podOut.send(new GenericMessage<PodEvent>(podEvent));
    }

}
