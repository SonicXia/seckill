-- 秒杀执行存储过程
DELIMITER $$ -- console ; 转换成 $$
-- 定义存储过程
-- 参数：in 输入参数；out 输出参数
-- row_count():返回上一条修改类型sql(delete,insert,update)的影响行数
-- row_count(): 0:未修改数据；>0:表示修改的行数；<0:sql错误/未执行修改sql
CREATE PROCEDURE `seckill`.`execute_seckill`
  (IN v_seckill_id bigint, IN v_phone bigint, IN v_kill_time timestamp , OUT r_result int)
  BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION ;
    INSERT ignore INTO success_killed
      (seckill_id, user_phone, ceate_time)
    VALUES (v_seckill_id, v_phone, v_kill_time);
    SELECT ROW_COUNT() INTO insert_count;
    IF (insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -2;
    ELSE
      UPDATE seckill
      SET number = number - 1
      WHERE seckill_id = v_seckill_id
        AND end_time > v_kill_time
        AND start_time < v_kill_time
        AND number > 0;
      SELECT ROW_COUNT() INTO insert_count;
    END IF;

