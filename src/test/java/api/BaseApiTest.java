package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;
    protected Logger log;

    @BeforeEach
    public void setSpecs(){
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setBaseUri("https://api.novaposhta.ua/v2.0/json/")
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
