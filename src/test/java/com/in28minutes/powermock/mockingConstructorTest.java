package com.in28minutes.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class mockingConstructorTest {

    //1) PrepareForTest --> prepare the class for test --> SystemUnderTest.class
        // PrepareForTest with the class using the Constructor to create the object
    //2) Override the constructor
    @InjectMocks
    SystemUnderTest systemUnderTest;
    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    @Mock
    ArrayList mockList;

    @Test
    public void constructorArrayTest() throws Exception {

        List<Integer> stats = Arrays.asList(1,2,3);

       // when(mockList.size()).thenReturn(10); //Con 10 falla, con 1 pasa por el expected
        when(mockList.size()).thenReturn(1);

        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

       int size = systemUnderTest.methodUsingAnArrayListConstructor();
        assertEquals(1,size);
    }


}