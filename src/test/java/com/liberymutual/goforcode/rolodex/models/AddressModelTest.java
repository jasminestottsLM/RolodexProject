package com.liberymutual.goforcode.rolodex.models;


import org.junit.Test;
import org.meanbean.test.BeanTester;

public class AddressModelTest {

	@Test
	public void test_all_gets_and_sets() {
        new BeanTester().testBean(Address.class);
	}

}
