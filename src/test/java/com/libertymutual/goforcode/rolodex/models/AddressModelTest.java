package com.libertymutual.goforcode.rolodex.models;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.rolodex.models.Address;

public class AddressModelTest {

    @Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Address.class);
    }

}
