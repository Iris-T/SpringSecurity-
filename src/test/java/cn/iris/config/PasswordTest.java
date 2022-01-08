package cn.iris.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Iris 2022/1/8
 */
public class PasswordTest {

    @Test
    public void pwdTest1() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
