package com.in28minutes.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class invokingPrivateMethodTest {

    //1) Use specific runner --> above test class
    //2) Initialize UtilityClass.class --> below specific runner
    //3) mock --> PowerMockito.mockStatic(UtilityClass.class);

    @Mock
    Dependency dependency;


    @InjectMocks
    SystemUnderTest systemUnderTest;
    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() throws Exception {

        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        //to test a private method we must use "invokeMethod"
        long result = Whitebox.invokeMethod(systemUnderTest,"privateMethodUnderTest");

        assertEquals(6,result);
        //check if the method is actually called:
    }

}