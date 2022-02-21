import Data.Params;
import Data.SpecData;
import api.Register;
import api.SuccessReg;
import api.UserData;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {


    @Test
    public void checkAvatarAndId() {
        SpecData.installSpec(SpecData.requestSpec(Params.URL), SpecData.responseSpec200Ok());
        List<UserData> users = given()
                .when()
                .get( "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
     //   users.stream().forEach(x-> x.getAvatar().contains(x.getId().toString()));
   //     Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> id = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        List<String> emails = users.stream().map(UserData::getEmail).collect(Collectors.toList());

        for(int i = 0; i<id.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(id.get(i)));
            Assertions.assertTrue(emails.get(i).endsWith("@reqres.in"));
        }
    }

    @Test
    public void successRegTest() {
        SpecData.installSpec(SpecData.requestSpec(Params.URL), SpecData.responseSpec200Ok());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register userReg = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(userReg)
                .when()
                .post("api/register")
                .then().log().all().extract().as(SuccessReg.class);
     //   Assertions.assertEquals(id, successReg.getId());
     //   Assertions.assertEquals(token, successReg.getToken());
    }
    //TODO https://www.youtube.com/watch?v=gxzXOMxIt4w&t=1618s - продолжить просмотр
    //TODO разобраться со стримами и с лямбда в java
}
