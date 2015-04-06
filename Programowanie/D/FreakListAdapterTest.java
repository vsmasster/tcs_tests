import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FreakListAdapterTest {

    @Test
    public void testExample() throws Exception {
        FreakList<Integer> Cycle11 = new FreakList<Integer> () {
            private LinkedList<Integer> ttt = new LinkedList<Integer>();
            {
                int a = 2;
                for (int i = 0; i < 10; i++)
                {
                    ttt.add(a);
                    a = (2*a)%11;
                }
            }
            @Override
            public Integer pop() throws AccessDenied, WasWrong, IsEmpty {
                if (ttt.isEmpty())
                    throw new IsEmpty();
                return ttt.pop();
            }
        };

        FreakListAdapter<Integer> f = new FreakListAdapter<Integer>(Cycle11);

        List<Integer> expectedResults = Arrays.asList(2,4,8,5,10,9,7,3,6,1);

        for(Integer i : expectedResults) {
            try {
                assertEquals(i, f.pop());
            }
            catch (IsEmpty e) {
                fail();
            }
        }
        try {
            f.pop();
            fail();
        }
        catch (IsEmpty e) {}
    }
    @Test
    public void testWrongValues() throws Exception{
        FreakList<Integer> fl = new FreakList<Integer>() {
            int counter = 0;
            @Override
            public Integer pop() throws AccessDenied, WasWrong, IsEmpty {
                switch(++counter) {
                    case 1 :
                        return 2;
                    case 2 :
                        throw new WasWrong();
                    case 3 :
                        return 1;
                    case 4 :
                        return 3;
                    default:
                        throw new IsEmpty();
                }
            }
        };
        FreakListAdapter<Integer> f = new FreakListAdapter<Integer>(fl);

        List<Integer> expectedResults = Arrays.asList(1,2,3);

        for(Integer i : expectedResults) {
            try {
                assertEquals(i, f.pop());
            }
            catch (IsEmpty e) {
                fail();
            }
        }
        try {
            f.pop();
            fail();
        }
        catch (IsEmpty e) {}
    }
    
    @Test
    public void randomErrorTest() throws Exception{
        FreakList<Integer> fl = new FreakList<Integer>() {
            int counter = 0;
            @Override
            public Integer pop() throws AccessDenied, WasWrong, IsEmpty {
                switch(++counter) {
                    case 1 :
                    	return 1;
                    case 2 :
                    	throw new WasWrong();
                    case 3 :
                    	return 2;
                    case 4:
                    	throw new IsEmpty();
                    case 5 :
                    	throw new WasWrong();
                    case 6:
                    	return 3;
                    default:
                        throw new IsEmpty();
                }
            }
        };
        FreakListAdapter<Integer> f = new FreakListAdapter<Integer>(fl);
        List<Integer> expectedResults = Arrays.asList(2,1,3);
        for(Integer i : expectedResults) {
            try {
                assertEquals(i, f.pop());
            }
            catch (IsEmpty e) {
                fail();
            }
        }
        try {
            f.pop();
            fail();
        }
        catch (IsEmpty e) {}
    }
}
