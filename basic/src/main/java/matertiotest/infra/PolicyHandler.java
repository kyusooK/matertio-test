package matertiotest.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import matertiotest.config.kafka.KafkaProcessor;
import matertiotest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Produced'"
    )
    public void wheneverProduced_DecreaseProduct(@Payload Produced produced) {
        Produced event = produced;
        System.out.println(
            "\n\n##### listener DecreaseProduct : " + produced + "\n\n"
        );
        // REST Request Sample

        // productService.getProduct(/** mapping value needed */);

        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
