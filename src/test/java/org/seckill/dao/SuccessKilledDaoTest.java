package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Sonic on 2017/10/5.
 */
// 配置spring和junit整合，junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    /**
     * 第一次 insertCnt = 1
     * 第二次 insertCnt = 0
     * 由于联合主键约束，第二次插入失败（SQL中的ignore忽略报错）
      * @throws Exception
     */
    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1001L;
        long phone = 13666666666L;
        int insertCnt = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCnt = " + insertCnt);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id = 1001L;
        long phone = 13666666666L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}