import Data.Params;
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
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(Params.URL + "api/users?page=2")
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
    //TODO https://www.youtube.com/watch?v=gxzXOMxIt4w&t=1618s - продолжить просмотр
    //TODO разобраться со стримами и с лямбда в java
}
