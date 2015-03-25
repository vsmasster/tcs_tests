package satori;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class ListIsFunctionTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void sampleTest() {
		
        ListIsFunction temp = new ListIsFunction();
        assertEquals(temp.getClass().getSuperclass().toString(), "class java.lang.Object");
        
        List tempList = temp.asList();
        Map tempMap = temp.asMap();
        
        assertEquals(tempList.toString(), "[]");
        assertEquals(tempMap.toString(), "{}");
        
        tempList.add("Ala");
        tempList.add("ma");
        tempList.add("kota");
        
        assertEquals(tempMap.toString(), "{0=Ala, 1=ma, 2=kota}");
        assertEquals(tempMap.keySet().toString(), "[0, 1, 2]");
        assertEquals(tempMap.values().toString(), "[Ala, ma, kota]");
        
        tempMap.put(1, "miala");
        assertEquals(tempList.toString(), "[Ala, miala, kota]");
        
        tempMap.put(3,"malego");
        assertEquals(tempList.toString(), "[Ala, miala, kota, malego]");
        
        tempMap.remove(3);
        assertEquals(tempList.toString(), "[Ala, miala, kota]");
        
        String[] tmp = {"0-->Ala",
        				"1-->miala",
        				"2-->kota"};
        
        int j = 0;
        
        for(Object i : tempMap.keySet())
        	assertEquals( i + "-->" + tempMap.get(i) , tmp[j++] );
        
        Iterator it = tempMap.keySet().iterator();
        
        assertEquals( "... -->" + tempMap.get(it.next()) , "... -->Ala" );
        assertEquals( "... -->" + tempMap.get(it.next()) , "... -->miala" );
        
        try {
            it.remove();
        } catch(Exception e) {
            assertEquals("Cannot remove element: " + e ,
            			 "Cannot remove element: java.lang.IllegalStateException" );
        }
        
        assertEquals( "... -->" + tempMap.get(it.next()) , "... -->kota" );
        
        it.remove();
        assertEquals(tempList.toString(), "[Ala, miala]");
        
        try {
            tempMap.remove(0);
        } catch(Exception e) {
            assertEquals("Cannot remove element at 0: " + e , 
            			 "Cannot remove element at 0: java.lang.IllegalArgumentException");
        }
        
        tempMap.remove(1);
        tempMap.remove(0);
        assertEquals(tempList.toString(), "[]");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAsMap() {
		ListIsFunction temp = new ListIsFunction();
		
        List tempList = temp.asList();
        Map tempMap = temp.asMap();
        
        tempList.add("A");
        assertEquals(tempMap.toString(), "{0=A}");
        
        tempMap.put(1, "C");
        assertEquals(tempMap.toString(), "{0=A, 1=C}");
        
        for (int i = 1; i <= 3; i++)
        	tempMap.put(i + 1, "X");
        
        assertEquals(tempMap.size(), 5);
        
        for (int i = -100; i < 100; i++)
        	assertEquals(tempMap.containsKey(i), i <= 4 && i >= 0);
        
        assertTrue(tempMap.containsValue("A"));
        assertFalse(tempMap.containsValue("D"));
        
        assertTrue(tempMap.containsValue(String.valueOf("C")));
        assertTrue(tempMap.containsValue(String.valueOf("X")));
        
        for (int i = 0; i < 100; i++) {
        	Object elem = tempMap.get(i);
        	
        	if (i >= tempMap.size())
        		assertEquals(elem, null);
        	else
        		assertEquals(elem, tempList.get(i));
        }
        
        assertEquals(tempMap.size(), 5);
        
        for (int i = 0; i < 100; i++) {
        	tempMap.put(i, "lol");
        }
        
        assertEquals(tempMap.get(99), "lol");
        assertEquals(tempMap.get(100), null);
        
        ListIsFunction t = new ListIsFunction();
        
        t.asMap().putAll( temp.asMap() );
        
        assertEquals(t.asMap().size(), 100);
        assertEquals(t.asMap().get(99), "lol");
        assertEquals(t.asMap().get(100), null);
        assertEquals(t.asMap().get(-1), null);
        
        assertEquals(t.asMap(), temp.asMap());
        
        int cnt = 0;
        
        for (int i = 0; i < 100; i++) {
        	try {
        		tempMap.remove(i);
        	} catch (Exception e) {
        		assertTrue(i < 99);
        		
        		if (e.toString() == "java.lang.IllegalArgumentException")
        			cnt++;
        	}
        }
        
        assertEquals(cnt, 99);
        assertEquals(tempList.size(), 99);
    
        for (int i = 98; i >= 0; i--)
        	tempMap.remove(i);
        
        assertEquals(tempMap.toString(), "{}");        
	}
	
	@Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void testEquals() {
    	
		ListIsFunction temp = new ListIsFunction();
		
        Map tempMap = temp.asMap();
        
        tempMap.put(0, "Ala");
        tempMap.put(1, "Bala");
        tempMap.put(2, "Cala");
        tempMap.put(3, "Dala");
        
        assertEquals(temp.asList().size(), 4);
    	
        assertTrue(tempMap.equals(temp.asMap()));
        assertEquals(tempMap.hashCode(), temp.asMap().hashCode());
        
        TreeMap <Object, Object> X = new TreeMap <Object, Object> ();
        
        X.putAll( temp.asMap() );
        
        assertTrue(X.equals(tempMap));
        assertTrue(tempMap.equals(X));
        assertEquals(tempMap.hashCode(), X.hashCode());
        
        assertFalse(tempMap.equals(tempMap.entrySet()));
        assertFalse(temp.equals(tempMap));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testEntrySet() {
		
		ListIsFunction t = new ListIsFunction();
		
        for (int i = 0; i < 100; i++) {
        	t.asMap().put(i, "ang");
        }
        
        Set s = t.asMap().entrySet();
        
        Iterator it = s.iterator();
        
        while (it.hasNext())
        	((Map.Entry) it.next()).setValue("pol");
        
        it = s.iterator();
        
        while (it.hasNext())
        	assertEquals( ((Map.Entry) it.next()).getValue() , "pol" );
        
        it.remove();
        assertEquals(s.size(), 99);
        assertEquals(it.hasNext(), false);
	}
}
