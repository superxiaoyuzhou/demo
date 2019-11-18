package com.piter.redis;

import com.piter.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("k1","v1");
        stringRedisTemplate.opsForValue().set("k2","v2");

        //存放对象
        redisTemplate.opsForValue().set("k3",new User(1,"lyq",18));
//        User user = JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get("k3")), User.class);
        User user = (User)redisTemplate.opsForValue().get("k3");
        System.out.println(user);

        //存放hash
        redisTemplate.opsForHash().put("hash1","hk1","hv1");
        redisTemplate.opsForHash().put("hash1","hk2",new User(2,"lyq2",19));
        redisTemplate.opsForHash().put("hash1","hk3",new User(3,"lyq3",19));
    }

    @Test
    public void test2() {
        //bound操作
        BoundHashOperations hash1 = redisTemplate.boundHashOps("hash1");
        System.out.println(hash1.get("hk2"));
        hash1.put("hk3","hv3");
    }

    @Test
    public void test3() {
        //同一条连接下执行多个redis命令 SessionCallback
        /**
         * @see org.springframework.data.redis.core.SessionCallback
         */
        redisTemplate.execute((RedisOperations ro) -> {
            System.out.println(ro.opsForValue().get("k3"));
            redisTemplate.opsForValue().set("k4", "v4");
            return ro;
        });
    }

    /**
     * redis存取值String类型
     */
    @Test
    public void test4() {
        redisTemplate.opsForValue().set("aaa","bbb");
        Object str = redisTemplate.opsForValue().get("aaa");
        System.out.println(str);
    }

    /**
     * redis存取值Hash类型
     */
    @Test
    public void test5() {
        redisTemplate.opsForHash().put("hash1","aaa","bbb");
        Object str = redisTemplate.opsForHash().get("hash1","aaa");
        System.out.println(str);
    }

    /**
     * redis存取值List类型
     */
    @Test
    public void test6() {
        redisTemplate.opsForList().leftPush("list1","abc");
        redisTemplate.opsForList().index("list1",10);
        redisTemplate.opsForList().set("list1",0,"aaa");
        Object str = redisTemplate.opsForList().range("list1",0,1);
        System.out.println(str);
    }

    /**
     * redis存取值Set类型
     */
    @Test
    public void test7() {
        redisTemplate.opsForSet().add("set1","aaa","bbb","ccc");
        Object str = redisTemplate.opsForSet().members("set1");
        System.out.println(str);
    }

    /**
     * redis存取值ZSet类型(有序集合)
     */
    @Test
    public void test8() {
        redisTemplate.opsForZSet().add("ZSet1","abc",0);
        Object str = redisTemplate.opsForZSet().range("ZSet1",0,0);
        System.out.println(str);
    }

}
