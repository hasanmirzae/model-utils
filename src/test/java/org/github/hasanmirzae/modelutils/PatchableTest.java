package org.github.hasanmirzae.modelutils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class PatchableTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldPatchCorrectly() throws InvocationTargetException, IllegalAccessException {

        SomeModel someModel = new SomeModel();
        someModel.setStrField("String value");
        someModel.setIntField(100);

        SomeModel anotherInstance = new SomeModel();
        anotherInstance.setIntField(200);
        anotherInstance.setOverridingFields(Arrays.asList("intField"));
        someModel.patchBy(anotherInstance);

        assertEquals(200, someModel.getIntField());
        assertEquals(someModel.getStrField(), "String value");

    }

    @Test
    public void shouldThrowException(){
        thrown.expect(RuntimeException.class);
        SomeModel someModel = new SomeModel();

        SomeModel anotherInstance = new SomeModel();
        anotherInstance.setOverridingFields(Arrays.asList("nonExistingField"));
        someModel.patchBy(anotherInstance);

    }

}
