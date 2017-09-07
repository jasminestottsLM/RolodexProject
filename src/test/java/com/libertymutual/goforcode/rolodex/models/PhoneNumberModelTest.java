package com.libertymutual.goforcode.rolodex.models;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.rolodex.models.PhoneNumber;

public class PhoneNumberModelTest {

	@Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(PhoneNumber.class);
    }
}