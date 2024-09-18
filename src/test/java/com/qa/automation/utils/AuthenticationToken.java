package com.qa.automation.utils;

import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationToken extends BaseFunctions{

    public static String getBearerTokenValue(String authenticationHost) throws Exception {
        final EncryptionUtil encryptionUtilInstance =
                new EncryptionUtil(PropFileHandler.readProperty("companyDomainCode"));
        final String encryptedpassword =
                encryptionUtilInstance.encrypt(PropFileHandler.readProperty("password1"));
        RestAssured.baseURI = authenticationHost
                + PropFileHandler.readAPIJsonFile("Authentication", "AccessToken");;
        RequestSpecification request = RestAssured.given().urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader()).and()
                .header("authorization", "Bearer")
                .param("username", PropFileHandler.readProperty("adminusername"))
                .param("password", encryptedpassword)
                .param("grant_type", PropFileHandler.readProperty("grant_type"))
                .param("loginType", 1)
                .param("companyDomainCode", PropFileHandler.readProperty("companyDomainCode"))
                .param("isEncrypted", 1);
        Response response = request.post();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String access_token = "INVALID";
        try {
            access_token = jsonPathEvaluator.get("access_token");
        } catch (Exception e) {
            System.out.println("ERROR: Access Token NOT Found!! Exception: " + e);
        }
        System.out.println(access_token);
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(access_token);
        return access_token;
    
    }
   
}
