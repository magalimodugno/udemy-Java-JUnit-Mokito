package com.in28minutes.bussiness;

import org.junit.Test;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void letsMockListSizeMethod(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);

        assertEquals(2, listMock.size());
    }

    @Test
    public void letsMockListSize_ReturnMultipleValues(){
        List listMock = mock(List.class);

        //Primer return va a devolver un 2 y el segundo return va a devolver un 3
        when(listMock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void letsMockListGet(){
        List listMock = mock(List.class);

        when(listMock.get(0)).thenReturn("in28minutes");

        assertEquals("in28minutes", listMock.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_throwAnException(){
        List listMock = mock(List.class);
        //argument matcher
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
        listMock.get(0);
    }

    @Test
    public void letsMockListGet_usingBDD(){
        //given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("in28minutes");
    //when --> listMock.get(0)
        String firstElement = listMock.get(0);
        //Then
        assertEquals("in28minutes", listMock.get(0));
        assertThat(firstElement,is("in28minutes"));
    }
}
