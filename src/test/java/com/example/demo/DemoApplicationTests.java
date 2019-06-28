package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void test1() {
		List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		list.parallelStream().forEach(
				str -> {
					if ("bc".equals(str)){
						return;
					}
					System.out.println(str);
				}
		);
		System.out.println("xxxxxxxxxxxxxxxxx");
	}

}

