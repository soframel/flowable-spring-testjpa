package org.soframel.tests.flowable.spring.tasks;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.soframel.tests.flowable.spring.model.TestEntity;

public class TestJPATask  implements JavaDelegate {
	
	private final static Logger logger=Logger.getLogger(TestJPATask.class);

	@Override
	public void execute(DelegateExecution execution) {
		
		logger.debug("hello world");
		
		//TestEntity entity=(TestEntity) execution.getVariable("entity");
		
		//logger.debug("found entity="+entity);
		
//		TestEntity test=new TestEntity();
//		test.setId(UUID.randomUUID().getMostSignificantBits());
//		test.setValue("toto");
//		
//		logger.debug("********************************before setting variable");
//		
//		execution.setVariable("test", test);
//		
//		logger.debug("********************************after setting variable");
	}

}
