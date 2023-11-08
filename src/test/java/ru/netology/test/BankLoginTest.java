package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuthCodes;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class BankLoginTest {
    LoginPage loginPage;
    @AfterEach
    void tearDown(){
        cleanAuthCodes();
    }
    @AfterAll
    static void tearDownAll(){
        cleanDatabase();
    }

    @BeforeEach
    void setUp (){
        loginPage = open ("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Should seccessfully login to dashboard with exist login and password from sut test data")
    void shouldSuccessfullLogin() {
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
