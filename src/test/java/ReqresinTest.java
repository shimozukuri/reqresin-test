import api.ReqresinApi;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import pojo.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresinTest {

    private final ReqresinApi reqresinApi = new ReqresinApi();

    @Test
    @Owner("shimozukuri")
    @Description("������������� ������ ������������� � ����������� ����� ����� � ������� � �������� �������� ���� �������������")
    public void checkAvatarAndIdTest() {
        String domain = "@reqres.in";

        List<UserData> users = reqresinApi.getUserList();

        users.forEach(i -> assertTrue(i.getAvatar().contains(i.getId().toString())));
        assertTrue(users.stream().allMatch(i -> i.getEmail().endsWith(domain)));
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� ������������ � ����������� ��� � ������� ������������")
    public void checkFirstNameAndLastNameTest() {
        String firstName = "Janet";
        String lastName = "Weaver";

        UserData user = reqresinApi.getSingleUser();

        assertEquals(firstName, user.getFirst_name());
        assertEquals(lastName, user.getLast_name());
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� �������������� ������������ � ����������� ��� ������")
    public void userNotFoundTest() {
        reqresinApi.getUserNotFound();
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� ������ ������ � ����������� ���������� �� ����")
    public void sortedYearTest() {
        List<ColorData> colors = reqresinApi.getListColors();

        List<Integer> years = colors.stream().map(ColorData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();

        assertEquals(sortedYears, years);
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� ���� � ����������� �������� � ��� �����")
    public void checkNameAndColorTest() {
        String name = "fuchsia rose";
        String colorName = "#C74375";

        ColorData color = reqresinApi.getSingleColor();

        assertEquals(name, color.getName());
        assertEquals(colorName, color.getColor());
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� �������������� ���� � ����������� ��� ������")
    public void colorNotFoundTest() {
        reqresinApi.getColorNotFound();
    }

    @Test
    @Owner("shimozukuri")
    @Description("��������� ������������ � ����������� ���� ��������")
    public void checkProfileDateCreatedTest() {
        UserProfileRequest request = new UserProfileRequest(
                "morpheus",
                "leader"
        );
        String date = LocalDate.now().toString();

        UserProfileCreatedResponse response = reqresinApi.createUserProfile(request);

        assertEquals(date, response.getCreatedAt().split("T")[0]);
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� ������������ � ����������� ���� �������� � ����������� ���� ������")
    public void checkProfileJobAndDateUpdatedPutTest() {
        String job = "zion resident";
        UserProfileRequest request = new UserProfileRequest(
                "morpheus",
                job
        );
        String date = LocalDate.now().toString();

        UserProfileUpdatedResponse response = reqresinApi.putUserProfile(request);

        assertEquals(date, response.getUpdatedAt().split("T")[0]);
        assertEquals(job, response.getJob());
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� ������������ � ����������� ���� �������� � ����������� ���� ������")
    public void checkProfileJobAndDateUpdatedPatchTest() {
        String job = "zion resident";
        UserProfileRequest request = new UserProfileRequest(
                "morpheus",
                job
        );
        String date = LocalDate.now().toString();

        UserProfileUpdatedResponse response = reqresinApi.patchUserProfile(request);

        assertEquals(date, response.getUpdatedAt().split("T")[0]);
        assertEquals(job, response.getJob());
    }

    @Test
    @Owner("shimozukuri")
    @Description("��������� ������������ � ���������� ��� ������")
    public void deleteUserProfileTest() {
        reqresinApi.deleteUserProfile();
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� � �������� id � ������")
    public void checkSuccessRegTest() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Authorization register = new Authorization(
                "eve.holt@reqres.in",
                "cityslicka"
        );

        SuccessReg success = reqresinApi.postSuccessReg(register);

        assertEquals(id, success.getId());
        assertEquals(token, success.getToken());
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� ��� ������ � �������� ������ ������")
    public void checkUnSuccessRegTest() {
        String error = "Missing password";
        Authorization register = new Authorization(
                "sydney@fife",
                ""
        );

        UnSuccessAuth unSuccess = reqresinApi.postUnSuccessReg(register);

        assertEquals(error, unSuccess.getError());
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� � �������� ������")
    public void checkSuccessAuthTest() {
        String token = "QpwL5tke4Pnpja7X4";
        Authorization authorization = new Authorization(
                "eve.holt@reqres.in",
                "cityslicka"
        );

        SuccessAuth success = reqresinApi.postSuccessAuth(authorization);

        assertEquals(token, success.getToken());
    }

    @Test
    @Owner("shimozukuri")
    @Description("����������� ��� ������ � �������� ������ ������")
    public void checkUnSuccessAuthTest() {
        String error = "Missing password";
        Authorization authorization = new Authorization(
                "peter@klaven",
                ""
        );

        UnSuccessAuth unSuccess = reqresinApi.postUnSuccessAuth(authorization);

        assertEquals(error, unSuccess.getError());
    }

    @Test
    @Owner("shimozukuri")
    @Description("������������� ������ ������������� � ��������� 3 ������� � �������� ����� � ������� ������� ������������")
    public void checkUserProfileListWithDelay() {
        String firstName = "Janet";
        String lastName = "Weaver";

        List<UserData> users = reqresinApi.getUserListWithDelay();

        assertEquals(firstName, users.get(1).getFirst_name());
        assertEquals(lastName, users.get(1).getLast_name());
    }

}
