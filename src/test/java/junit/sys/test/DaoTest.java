package junit.sys.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.langton.power.sys.bean.AdminLogBean;
import com.langton.power.sys.bean.TeminalBean;
import com.langton.power.sys.dao.impl.CommonDao;

public class DaoTest {
    ApplicationContext ctx;

    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    @Test
    public void save(){
        TeminalBean bean = new TeminalBean();
        bean.setSoftwareVersion("v0.3.9");
        bean.setStatus(TeminalBean.Status.IMPORTED);
        bean.setTeminalSerialNumber("443322114456fffd");
        bean.setTeminalVersion("v1.4");
//        CommonDao dao = ctx.getBean(CommonDao.class);
        CommonDao dao = (CommonDao) ctx.getBean("commonDao");
        dao.save(bean);
        dao.save(bean);
        dao.save(bean);
        AdminLogBean bean2 = new AdminLogBean();
        bean2.setOperateDetails("操作细节 成功");
        bean2.setOperateTime(new Date());
        bean2.setOperateType(AdminLogBean.OperateType.LOGIN);
        bean2.setOperator("admin");
        dao.save(bean2);
    }
}
