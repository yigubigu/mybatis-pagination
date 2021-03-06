package net.andromedarabbit.mybatis.pagination;

/**
 * Created by tywin on 2015. 11. 16..
 */

import net.andromedarabbit.configuration.MyBatisConfig;
import net.andromedarabbit.configuration.TestConfiguration;
import net.andromedarabbit.persistence.dao.MysqlUserDao;
import net.andromedarabbit.persistence.model.MysqlUser;
import net.andromedarabbit.persistence.model.MysqlUserExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        TestConfiguration.class, MyBatisConfig.class
})
public class SelectLimitByTest {
    @Inject
    private MysqlUserDao dao;

    @Test
    public void testGetUsers() {
        final int offset = 1;
        final int limit = 10;

        MysqlUserExample example = new MysqlUserExample();
        example.createCriteria()
                .andHostIsNotNull()
                ;

        RowBounds bounds = new RowBounds(offset, limit);
        List<MysqlUser> users = dao.getUsers(example, bounds);
        assertThat(users, notNullValue());
    }
}
