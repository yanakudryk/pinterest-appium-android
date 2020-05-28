import com.github.javafaker.Faker;
import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;


public class RegistrationTests extends BaseTests {
    @Test
    public void testRegistrationSuccessful(){
        Faker usFaker = new Faker(new Locale("en-US"));
        User user = new User(
                usFaker.name().firstName(),
                usFaker.name().username().concat("@email.com"),
                "123456asdQQ");
        welcomeScreen.tapRegister();
        registerScreen.register(user);
        Assert.assertEquals(createServerScreen.getTitleText(), "Create Your Server", "Create server screen is not displayed.");
        createServerScreen.setUpServer("Beauty");
        Assert.assertEquals(createServerScreen.getFinalTitle(), "Invite a friend to your server!", "Share screen is not displayed.");
        createServerScreen.closeShareServerLinkScreen();
    }
}
