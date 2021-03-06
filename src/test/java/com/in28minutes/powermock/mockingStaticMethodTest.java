package com.in28minutes.powermock;

import com.in28minutes.bussiness.TodoBusinessImpl;
import com.in28minutes.dataapi.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class mockingStaticMethodTest {

    //1) Use specific runner --> above test class
    //2) Initialize UtilityClass.class --> below specific runner
    //3) mock --> PowerMockito.mockStatic(UtilityClass.class);

    @Mock
    Dependency dependency;


    @InjectMocks
    SystemUnderTest systemUnderTest;
    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {

        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(150);

        int result = systemUnderTest.methodCallingAStaticMethod();

        assertEquals(150,result);

        //check if the method is actually called:
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);
    }

}