package com.in28minutes.bussiness;

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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockitoInjectMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    //Asi podemos tener multiples runners

    //@Mock
    @Mock
     TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;
    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
   // ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
    }

@Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        //given

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        //when
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        //then
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD() {

        //given

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        //when
        todoBusinessImpl.deleteTodosRelatedToSpring("Dummy");
        //then
        verify(todoServiceMock , atLeast(1)).deleteTodo("Learn to Code");
        verify(todoServiceMock).deleteTodo("Learn to Code");
        verify(todoServiceMock, never()).deleteTodo("Learn to Dance");
       // assertEquals(2,filteredTodos.size());
    }



    @Test
    public void testDeleteTodosRelatedToSpring_argumentCapture() {

        //1) Declare argument captor
        //2) Define argument captor on specific method call
        //3) Capture the argument


        //given

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        //when
        todoBusinessImpl.deleteTodosRelatedToSpring("Dummy");
        //then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        //assertThat(stringArgumentCaptor.getValue(),is("Learn to Code"));
        assertThat(stringArgumentCaptor.getAllValues().size(),is(1));
        }
}