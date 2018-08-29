package org.soframel.tests.flowable.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.soframel.tests.flowable.spring.model.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
@ActiveProfiles("mock")
public class TestProcessManagement {

	@Autowired
	public ProcessEngine processEngine;

	@Autowired
	public RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@Test
	public void testProcessWithJPAEntityAtStart() throws InterruptedException {
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery().processDefinitionKey("process").latestVersion().singleResult();

		assertNotNull(def);

		Map<String, Object> params = new HashMap<>();
		TestEntity test=new TestEntity();
		long id=System.currentTimeMillis();
		test.setId(id);
		System.out.println("######################### Entity id="+test.getId());
		test.setValue("entity");
		params.put("entity", test);
		params.put("var", 21);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("process", params);
	
	}
	
}
