package com.surging.repository;

import com.surging.SurgingApplication;
import com.surging.entity.User1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Administrator on 2018/10/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurgingApplication.class)
@WebAppConfiguration
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void getTableStructure() throws Exception {
            if (userRepository==null){
                System.out.println("null");
            }
//            List<Columns> list = userRepository.getTableStructure("user1","my");
            List<User1>  list= userRepository.findAll();
            System.out.println(list.get(0));
    }

}