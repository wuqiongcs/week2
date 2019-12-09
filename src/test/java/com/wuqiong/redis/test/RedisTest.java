package com.wuqiong.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuqiong.UserUtils;
import com.wuqiong.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	RedisTemplate redisTemplate;
	//jdk
	@Test
	public void testJDK() {
		//开是
		long start = System.currentTimeMillis();
		//循环
		for (int i = 0; i <50000; i++) {
			User u = new User();
			u.setId(i);
			u.setName(UserUtils.getName());
			u.setGender(UserUtils.getSex());
			u.setPhone(UserUtils.getPhone());
			u.setEmail(UserUtils.getMail());
			u.setBirthday(UserUtils.getBirthday());
			//添加到redis
			redisTemplate.opsForValue().set("u"+i, u);
			
		}
		//结束
		long end = System.currentTimeMillis();
		//输出
		System.out.println("使用的是jdk的序列化方式");
		System.out.println("保存数量为5w");
		System.out.println("共使用了"+(end-start)+"时间");
		
	}
	//json
	 
		@Test
		public void testJSON() {
			//开始
			long start = System.currentTimeMillis();
			//循环
			for (int i = 0; i <50000; i++) {
				User u = new User();
				u.setId(i);
				u.setName(UserUtils.getName());
				u.setGender(UserUtils.getSex());
				u.setPhone(UserUtils.getPhone());
				u.setEmail(UserUtils.getMail());
				u.setBirthday(UserUtils.getBirthday());
				//添加到redis
				redisTemplate.opsForValue().set("u"+i, u);
				
			}
			//结束
			long end = System.currentTimeMillis();
			//输出
			System.out.println("使用的是json的序列化方式");
			System.out.println("保存数量为5w");
			System.out.println("共使用了"+(end-start)+"时间");
			
		}
		//hash
		 
			@Test
			public void testHASH() {
				//开始
				long start = System.currentTimeMillis();
				//训话
				for (int i = 0; i <50000; i++) {
					User u = new User();
					u.setId(i);
					u.setName(UserUtils.getName());
					u.setGender(UserUtils.getSex());
					u.setPhone(UserUtils.getPhone());
					u.setEmail(UserUtils.getMail());
					u.setBirthday(UserUtils.getBirthday());
					//添加到redis
					redisTemplate.opsForHash().put("u"+i,"u"+i, u.toString());
					
				}
				//结束
				long end = System.currentTimeMillis();
				//输出
				System.out.println("使用的是hash的序列化方式");
				System.out.println("保存数量为5w");
				System.out.println("共使用了"+(end-start)+"时间");
				
			}



}
