package io.justride;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;

@SpringBootApplication
@EnableBinding(PodBinding.class)
@RestController
public class PodprocessApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodprocessApplication.class, args);
    }

    //This will be replaced by PCC
    public static List<MapMarker> mapMarkers= new ArrayList<MapMarker>();

    @Component("PodListener")
    public static class PodListener {

        Logger logger = LoggerFactory.getLogger(PodListener.class);

        private MapMarker mapMarker;

        @Autowired
        public PodListener() {
        }

        @StreamListener(PodBinding.PODS_IN)
        public void process(PodEvent podEvent) {
            logger.info(podEvent.toString());

            if(podEvent.getSpeed().doubleValue() > 70) {
                logger.info("OVERSPEEDING uuid={}", podEvent.getUuid());
                //PCC lookup call will be made to find policy info ro POD UUID
                // MapMarker is based on BFF (backend for frontend) design, a complete event POJO
                // call locationToAddress to use real address
                mapMarker = new MapMarker(podEvent.getLatitude().doubleValue(),
                        podEvent.getLongitude().doubleValue(),
                        podEvent.getSpeed().doubleValue(),
                        68.0,
                        "some address",
                        "P987",
                        "Jane",
                        "Female",
                        42,
                        "E89 L30");
                mapMarkers.add(mapMarker);
            }
        }
    }

    @GetMapping("/mapMarkers")
    public List<MapMarker> getMapMarkers() {
        return mapMarkers;
    }

    @GetMapping("locationToAddress")
    public String locationToAddress() {
        //Generate your API key on https://opencagedata.com
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("YOUR_opencagedata_KEY");

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(41.40015, 2.15765);
        request.setLanguage("es"); // prioritize results in a specific language using an IETF format language code
        request.setNoDedupe(true); // don't return duplicate results
        request.setLimit(5); // only return the first 5 results (default is 10)
        request.setNoAnnotations(true); // exclude additional info such as calling code, timezone, and currency
        request.setMinConfidence(3); // restrict to results with a confidence rating of at least 3 (out of 10)

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);

        String formattedAddress = response.getResults().get(0).getFormatted();

        return formattedAddress;

    }

}
