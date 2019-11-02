package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.services.mailing.EmailServiceTest.class,
        org.wahlzeit.services.EmailAddressTest.class
})

public class EmailTestSuite {
}
