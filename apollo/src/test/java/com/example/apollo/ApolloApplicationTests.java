package com.example.apollo;

import com.alibaba.fastjson.JSON;
import com.example.apollo.entity.User;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApolloApplicationTests {

	@Test
	public void contextLoads() {
		List<User> list = new LinkedList<>();
		list.add(new User());
		list.add(new User());
		String str = JSON.toJSONString(list);
		System.out.println(str);
		List<String> list2 = new LinkedList<>();
		list2.add("aaa");
		list2.add("bbb");
		String str2 = JSON.toJSONString(list2);
		System.out.println(str2);
	}

}

