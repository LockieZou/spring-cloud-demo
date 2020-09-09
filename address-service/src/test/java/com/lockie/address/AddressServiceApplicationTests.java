package com.lockie.address;

import com.lockie.address.config.MyPasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	MyPasswordEncoder myPasswordEncoder;
	@Test
	public void testEncoder() {
		String pass = "123456";
		String hello = "hello";
		System.out.println("=========================");

		String encode = myPasswordEncoder.encode(pass);
		System.out.println(encode);
		String helloEnco = myPasswordEncoder.encode(hello);
		System.out.println(helloEnco);

		CharSequence cs = hello;
		boolean matches = myPasswordEncoder.matches(cs, hello);
		System.out.println(matches);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		String str = bCryptPasswordEncoder.encode("hello");
		System.out.println(str);
	}
}
