package org.soframel.tests.flowable.spring;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.soframel.tests.flowable.spring.model.TestEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProcess {
	public static void main(String[] args) {
		StartProcess sp=new StartProcess();
		sp.start();
		sp.shutdown();
	}
	
	private ConfigurableApplicationContext context;
	
	public StartProcess() {
		System.setProperty("SPRING_PROFILES_ACTIVE", "mock");
		context=new ClassPathXmlApplicationContext("/application-context.xml");
		
	}
	
	public void start() {
		RepositoryService repositoryService=context.getBean(RepositoryService.class);
		RuntimeService runtimeService=context.getBean(RuntimeService.class);
		
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery().processDefinitionKey("process").latestVersion().singleResult();


		Map<String, Object> params = new HashMap<>();
		TestEntity test=new TestEntity();
		long id=System.currentTimeMillis();
		test.setId(id);
		System.out.println("######################### Entity id="+test.getId());
		test.setValue("entity");
		params.put("entity", test);
		params.put("var", 21);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("process", params);
		String pid = pi.getId();
	}
	
	public void shutdown() {
		context.close();
	}
}
