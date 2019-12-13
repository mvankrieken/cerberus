package com.nike.cerberus.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DomainPojoTest {

    @Test
    public void test_pojo_structure_and_behavior() {

        List<PojoClass> pojoClasses = PojoClassFactory.getPojoClasses("com.nike.cerberus.domain");

        // exclude this class because it does not need getters and setters for every constructor parameter
        pojoClasses.remove(PojoClassFactory.getPojoClass(AssetResourceFile.class));

        pojoClasses.remove(PojoClassFactory.getPojoClass(CerberusAuthToken.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(CerberusAuthToken.Builder.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(VaultStyleErrorResponse.Builder.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(IamPrincipalPermission.Builder.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(UserGroupPermission.Builder.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(SafeDepositBoxV2.Builder.class));
        pojoClasses.remove(PojoClassFactory.getPojoClass(SecureDataResponse.SecureDataResponseBuilder.class));

        Assert.assertTrue(pojoClasses.size() > 1);

        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();

        validator.validate(pojoClasses);
    }
}
