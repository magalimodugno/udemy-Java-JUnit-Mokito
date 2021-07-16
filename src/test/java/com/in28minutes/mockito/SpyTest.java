package com.in28minutes.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SpyTest {
//      ·:.. Avoid spy in projects ..:·
    @Test
    public void test(){

        List arrayListSpy = spy(ArrayList.class);
        assertEquals(0,arrayListSpy.size());
        //mock return default value
        arrayListSpy.add("Dummy");
        assertEquals(1,arrayListSpy.size());

        arrayListSpy.remove("Dummy");
        assertEquals(0,arrayListSpy.size());

    }

    @Test
    public void test2(){
        List arrayListSpy = spy(ArrayList.class);
        stub(arrayListSpy.size()).toReturn(5);
        assertEquals(5,arrayListSpy.size());
    }

    @Test
    public void checkIfMethodWasCalled(){
        List arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Dummy");
        verify(arrayListSpy).add("Dummy");
        verify(arrayListSpy,never()).clear();
    }




}
