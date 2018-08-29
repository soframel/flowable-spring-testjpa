package org.soframel.tests.flowable.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TEST_ENTITY")
public class TestEntity {

	@Id
	@Column(name="ID_")
	private Long id;
	
	
	private String value;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", value=" + value + "]";
	}
	
	
}
