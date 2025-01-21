package api;

import base.Specifications;
import pojo.*;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresinApi {

    private final static String URL = "https://reqres.in";

    public List<UserData> getUserList() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    public UserData getSingleUser() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .when()
                .get("/api/users/2")
                .then().log().all()
                .extract().jsonPath().getObject("data", UserData.class);
    }

    public void getUserNotFound() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecError404()
        );

        given()
                .when()
                .get("/api/users/23")
                .then().log().all();
    }

    public List<ColorData> getListColors() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorData.class);
    }

    public ColorData getSingleColor() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .when()
                .get("/api/unknown/2")
                .then().log().all()
                .extract().jsonPath().getObject("data", ColorData.class);
    }

    public void getColorNotFound() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecError404()
        );

        given()
                .when()
                .get("/api/unknown/23")
                .then().log().all()
                .extract().asString();
    }

    public UserProfileCreatedResponse createUserProfile(UserProfileRequest request) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecUnique(201)
        );

        return given()
                .body(request)
                .post("/api/users")
                .then().log().all()
                .extract().as(UserProfileCreatedResponse.class);
    }

    public UserProfileUpdatedResponse putUserProfile(UserProfileRequest request) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .body(request)
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserProfileUpdatedResponse.class);
    }

    public UserProfileUpdatedResponse patchUserProfile(UserProfileRequest request) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .body(request)
                .patch("/api/users/2")
                .then().log().all()
                .extract().as(UserProfileUpdatedResponse.class);
    }

    public void deleteUserProfile() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecUnique(204)
        );

        given()
                .delete("/api/users/2")
                .then().log().all();
    }

    public SuccessReg postSuccessReg(Authorization register) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .body(register)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
    }

    public UnSuccessAuth postUnSuccessReg(Authorization register) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecError400()
        );

        return given()
                .body(register)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(UnSuccessAuth.class);
    }

    public SuccessAuth postSuccessAuth(Authorization authorization) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecOK200()
        );

        return given()
                .body(authorization)
                .when()
                .post("/api/login")
                .then().log().all()
                .extract().as(SuccessAuth.class);
    }

    public UnSuccessAuth postUnSuccessAuth(Authorization authorization) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecError400()
        );

        return given()
                .body(authorization)
                .when()
                .post("/api/login")
                .then().log().all()
                .extract().as(UnSuccessAuth.class);
    }

    public List<UserData> getUserListWithDelay() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL),
                Specifications.responseSpecUnique(200)
        );

        return given()
                .when()
                .get("/api/users?delay=3")
                .then().log().all()
                .extract().jsonPath().getList("data", UserData.class);
    }

}
