package api;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class NovaPoshtaAPITest extends BaseApiTest {
    String API_key = "nnnnnnnnnnnnn";
    Map<String, Object> reqBody = new HashMap<>();

    @BeforeMethod
    public void setReqBody() {
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("FindByString", "Київ");
        methodProperties.put("Limit", 5);
        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "getSettlements");
        reqBody.put("methodProperties", methodProperties);
    }

    @Test
    public void validateResponseCommonValuesTest() {
        List<Address> addressList = given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .extract()
                .body().jsonPath().getList("data", Address.class);
        addressList.forEach(x -> {
            Assert.assertTrue(
                    x.getSettlementType().contains("563ced10-f210-11e3-8c4a-0050568002cf") ||
                            x.getSettlementType().contains("563ced13-f210-11e3-8c4a-0050568002cf")
            );
            Assert.assertTrue(
                    x.getSettlementTypeDescription().contains("село") ||
                            x.getSettlementTypeDescription().contains("місто")
            );
            Assert.assertTrue(
                    x.getSettlementTypeDescriptionRu().contains("село") ||
                            x.getSettlementTypeDescriptionRu().contains("город")
            );
            Assert.assertTrue(
                    x.getSettlementTypeDescriptionTranslit().contains("misto") ||
                            x.getSettlementTypeDescriptionTranslit().contains("selo")
            );
            Assert.assertTrue(
                    x.getDelivery1().contains("1") ||
                            x.getDelivery1().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery2().contains("1") ||
                            x.getDelivery2().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery3().contains("1") ||
                            x.getDelivery3().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery4().contains("1") ||
                            x.getDelivery4().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery5().contains("1") ||
                            x.getDelivery5().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery6().contains("1") ||
                            x.getDelivery6().contains("0") ||
                            x.getDelivery6().isEmpty()
            );
            Assert.assertTrue(
                    x.getDelivery7().contains("1") ||
                            x.getDelivery7().contains("0") ||
                            x.getDelivery7().isEmpty()
            );
            Assert.assertEquals(1, x.getSpecialCashCheck());
            Assert.assertTrue(
                    x.getWarehouse().contains("0") ||
                            x.getWarehouse().contains("1")
            );
        });
    }

    @Test
    public void checkRefTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Ref", hasItems(
                        "0db2df4b-4b3a-11e4-ab6d-005056801329",
                        "e718a680-4b33-11e4-ab6d-005056801329",
                        "0df25497-4b3a-11e4-ab6d-005056801329",
                        "0dd153b3-4b3a-11e4-ab6d-005056801329",
                        "0d94f546-4b3a-11e4-ab6d-005056801329"
                ));
    }

    @Test
    public void checkLatitudeLongitudeTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Latitude", hasItems(
                        "47.866313000000000",
                        "50.450418000000000",
                        "49.431219900000000",
                        "46.217119000000000",
                        "47.329178000000000"
                ))
                .body("data.Longitude", hasItems(
                        "31.019312000000000",
                        "30.523541000000000",
                        "24.046327100000000",
                        "32.530332000000000",
                        "32.628653000000000"
                ));
    }

    @Test
    public void checkDescriptionDataTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Description", hasItems(
                        "Київ",
                        "Київ",
                        "Київець",
                        "Київка",
                        "Київське"))
                .body("data.DescriptionRu", hasItems(
                        "Червоный Киев",
                        "Киев",
                        "Киевец",
                        "Киевка",
                        "Киевское"))
                .body("data.DescriptionTranslit", hasItems(
                        "Kyiv",
                        "Kyiv",
                        "Kyivets",
                        "Kyivka",
                        "Kyivske"
                ));
    }

    @Test
    public void checkRegionDataTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Region", hasItems(
                        "e4ace09a-4b33-11e4-ab6d-005056801329",
                        "",
                        "e4ad1885-4b33-11e4-ab6d-005056801329",
                        "e4aa6b08-4b33-11e4-ab6d-005056801329",
                        "e4ace61f-4b33-11e4-ab6d-005056801329"
                ))
                .body("data.RegionsDescription", hasItems(
                        "Доманівський р-н",
                        "",
                        "Миколаївський р-н",
                        "Голопристанський р-н",
                        "Баштанський р-н"
                ))
                .body("data.RegionsDescriptionRu", hasItems(
                        "Доманевский р-н",
                        "",
                        "Николаевский р-н",
                        "Голопристанский р-н",
                        "Баштанский р-н"
                ))
                .body("data.RegionsDescriptionTranslit", hasItems(
                        "Domanivskyi",
                        "",
                        "Mykolaivskyi",
                        "Holoprystanskyi",
                        "Bashtanskyi"
                ));
    }

    @Test
    public void checkAreaDataTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Area", hasItems(
                        "dcaaddd7-4b33-11e4-ab6d-005056801329",
                        "dcaadb64-4b33-11e4-ab6d-005056801329",
                        "dcaadd3a-4b33-11e4-ab6d-005056801329",
                        "dcaae44b-4b33-11e4-ab6d-005056801329",
                        "dcaaddd7-4b33-11e4-ab6d-005056801329"
                ))
                .body("data.AreaDescription", hasItems(
                        "Миколаївська область",
                        "Київська",
                        "Львівська область",
                        "Херсонська область",
                        "Миколаївська область"
                ))
                .body("data.AreaDescriptionRu", hasItems(
                        "Николаевская область",
                        "Киевская область",
                        "Львовская область",
                        "Херсонская область",
                        "Николаевская область"
                ))
                .body("data.AreaDescriptionTranslit", hasItems(
                        "Mykolaivska",
                        "Kyivska",
                        "Lvivska",
                        "Khersonska",
                        "Mykolaivska"
                ));
    }

    @Test
    public void checkIndexDataTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Index1", hasItems(
                        "56419",
                        "01001",
                        "81646",
                        "75663",
                        "56103"))
                .body("data.Index2", hasItems(
                        "56419",
                        "04215",
                        "81646",
                        "75663",
                        "56103"))
                .body("data.IndexCOATSU1", hasItems(
                        "4822780907",
                        "8000000000",
                        "4623084201",
                        "6522382503",
                        "4820683902"
                ));
    }

    @Test
    public void checkDeliveryDataTest() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data.Delivery1", hasItems("", "1", "1", "", ""))
                .body("data.Delivery2", hasItems("", "1", "1", "", ""))
                .body("data.Delivery3", hasItems("", "1", "1", "", ""))
                .body("data.Delivery4", hasItems("", "1", "1", "", ""))
                .body("data.Delivery5", hasItems("", "1", "1", "", ""))
                .body("data.Delivery6", hasItems("", "1", "0", "", ""))
                .body("data.Delivery7", hasItems("", "1", "0", "", ""));
    }
}