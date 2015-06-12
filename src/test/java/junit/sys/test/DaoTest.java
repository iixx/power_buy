package junit.sys.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.langton.power.sys.bean.AdminBean;
import com.langton.power.sys.bean.AdminLogBean;
import com.langton.power.sys.bean.TeminalBean;
import com.langton.power.sys.dao.impl.AdminRightDao;
import com.langton.power.sys.dao.impl.CommonDao;

public class DaoTest {
    ApplicationContext ctx;

    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    @Test
    public  void rightSave(){
        AdminRightDao dao = (AdminRightDao) ctx.getBean("adminRightDao");
        dao.changeAdminRight(1);
    }
    
//    @Test
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
        bean2.setOperatorName("admin");
        dao.save(bean2);
    }
    
//    @Test
    public void delete(){
        CommonDao dao = (CommonDao) ctx.getBean("commonDao");
        AdminBean bean = new AdminBean();
        bean.setId(2);
        dao.delete(bean);
    }
    
//    @Test
    public void listAdminLog(){
        CommonDao dao = (CommonDao) ctx.getBean("commonDao");
        dao.find(AdminLogBean.class, 0, 10,null,"id",false);
    }
//    @Test
    public void count(){
        CommonDao dao = (CommonDao) ctx.getBean("commonDao");
        int count = dao.count(AdminLogBean.class);
        System.out.println(count);
    }
}
