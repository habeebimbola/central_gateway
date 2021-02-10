package com.centralgateway.task;

import com.centralgateway.task.arithmetic.ArithmeticCalculatorImpl;
import com.centralgateway.task.arithmetic.ProductCalculator;
import com.centralgateway.task.service.CalculatorService;
import com.centralgateway.task.service.DVDActorService;
import com.centralgateway.task.service.Multiplicator;
import com.centralgateway.task.service.RaisePower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@PropertySource("classpath:global.properties")
//@ConfigurationProperties()
@Primary
public class SMSController {
private final Logger logger = LoggerFactory.getLogger(SMSController.class);
    private final RestTemplate restTemplate;
    private final DVDActorService dvdActorService;

    @Autowired
    private SMSDeliveryStatus deliveryStatus;

    @Autowired
    private ArithmeticCalculatorImpl  arithmeticCalculator;

    @Autowired
    private ProductCalculator productCalculator;

    @Value("${user.firstname:DarlingDaughter}")
    private String firstname;

    @Value("${user.password}")
    private String password;

    @Value("${user.age:5}")
    private int age;

    @Autowired
    public SMSController(RestTemplate restTemplate, DVDActorService dvdActorService, ArithmeticCalculatorImpl arithmeticCalculator, ProductCalculator productCalculator) {
        this.restTemplate = restTemplate;
        this.dvdActorService = dvdActorService;
        this.arithmeticCalculator = arithmeticCalculator;
        this.productCalculator = productCalculator;

        Multiplicator multiplicator = (Multiplicator)this.arithmeticCalculator;
        CalculatorService calculatorService = (CalculatorService)this.arithmeticCalculator;
        RaisePower raisePower = (RaisePower)this.arithmeticCalculator;

        RaisePower productPower = (RaisePower)this.productCalculator;
        CalculatorService productCalculatorService = (CalculatorService) this.productCalculator;


        logger.info("Aspect Introduction: {} {} {}",multiplicator.multiply(5,10),
                                                    calculatorService.subtract(1000, 200),
                                                    raisePower.power(6, 3 ));

        logger.info("WildCards for PointCut Introduction! {} {} {}", productPower.power(8,3) ,
                productPower.power(12, 2 ),productCalculatorService.subtract(500, 150));
    }

    @PostMapping(path = "/sendSMS", consumes = "application/json")
    public SMSResponse sendSMS(@RequestBody SMSMessage smsMessage){
        ResponseEntity<SMSResponse> responseEntity =  this.restTemplate.postForEntity(URI.create("https://test-api.fidelitybank.ng/CardControlAPI/api/messaging/sendsms"),smsMessage, SMSResponse.class );

        this.deliveryStatus.smsDeliveryStatus(responseEntity.getBody());
        logger.info("Response Status Code: "+responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @PostMapping(path ="/actor", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DVDActor> getDVDActor(@RequestBody DVDActor dvdActor ){
        logger.info("Reading..."+this.firstname+" "+this.password+" "+this.age);
        ResponseEntity<DVDActor> actorResponseEntity = ResponseEntity.ok(this.dvdActorService.getActor(dvdActor.getFirstName()));
        return actorResponseEntity;
    }

//    @Scheduled(fixedDelay = 50000)
    public void repeated(){
        logger.info("This Line is executed repeatedly!");
    }
}
