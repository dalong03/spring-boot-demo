package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fb.springbootdemo.SpringBootDemoApplication;
import com.fb.springbootdemo.test.postprocessor.PostBean;

@SpringBootTest(classes = SpringBootDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanFactoryPostProcessorTest {
    @Autowired
    private PostBean postBean;
    
    @Test
    public void sayhello() throws Exception {
        postBean.sayhello();
    }
}
