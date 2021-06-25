import com.mask.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Mask.m
 * @create: 2021/06/23 22:33
 * @description:
 */
public class UserApp {

    public static void main(String[] args) {

        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService1 = (UserService) act.getBean("userService");
        UserService userService2 = (UserService) act.getBean("userService");
        UserService userService3 = (UserService) act.getBean("userService");

        System.out.println(userService1);
        System.out.println(userService2);
        System.out.println(userService3);

        //userService.save();


    }

}
