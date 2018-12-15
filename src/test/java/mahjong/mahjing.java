package mahjong;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class mahjing {
   // private static String input[] = {"3S", "4S","5S","ZHONG" ,"BAI","BAI", "BAI"};
    List<String> input =new ArrayList<>() ;
    @Test
    public void TestSerach()
    {
        List<String> out =new ArrayList<>() ;
        input.add("3O");
        input.add("4O");
        input.add("ZHONG");
        input.add("ZHONG");
        input.add("BAI");
        input.add("BAI");
        input.add("BAI");
        majing m =new majing();
        m.input(input);
        out.add("2O");
        out.add("5O");
        assertEquals(out,m.getListen());
    }
}
