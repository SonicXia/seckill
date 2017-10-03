package org.seckill.dao;

import org.seckill.entity.Seckill;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**
 * Created by Sonic on 2017/10/3.
 */
public interface SeckillDao {

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return 如果影响的行数>1，表示更新的行数
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据id查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@PathVariable int offset, int limit);

}
