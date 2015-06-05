package junit.sys.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.langton.power.sys.service.CoreService;

public class InitSpringTest {

    @Test
    public void springInit(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//        Object bean = ctx.getBean("coreService");
        Object bean = ctx.getBean(CoreService.class);
        System.out.println(bean);
    }
}
