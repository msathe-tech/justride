package io.justride;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(PodBinding.class)
public class PodprocessApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodprocessApplication.class, args);
    }

    @Component("PodListener")
    public static class PodListener{

        Logger logger = LoggerFactory.getLogger(PodListener.class);

        @Autowired
        public PodListener() {
        }

        @StreamListener(PodBinding.PODS_IN)
        public void process(PodEvent podEvent) {
            logger.info(podEvent.toString());

            if(podEvent.getSpeed().doubleValue() > 70) {
                logger.info("OVERSPEEDING uuid={}", podEvent.getUuid());
            }
        }
    }

}
