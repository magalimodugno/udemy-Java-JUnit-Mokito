package com.in28minutes.bussiness;

import com.in28minutes.dataapi.TodoService;
//import com.in28minutes.data.api.TodoServiceStub;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {
    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {

        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

    }

@Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        //given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        //when
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        //then
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD() {

        //given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
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

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Code");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //when
        todoBusinessImpl.deleteTodosRelatedToSpring("Dummy");
        //then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        //assertThat(stringArgumentCaptor.getValue(),is("Learn to Code"));
        assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
        }
}