package com.vueshop.manager;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.service.RightsService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VueShopManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class VueShopManagerApplicationTests {


	@Autowired
	private RightsService rightsService;

	@Test
	public void testRights() throws Exception{
		List<MenuInfoResponse> allRightsInfoNormal = rightsService.getAllRightsInfoNormal();
		List<MenuInfoResponse> allRightsInfoTree = rightsService.getAllRightsInfoTree();
		System.out.println(allRightsInfoTree);
	}



}
