package mahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class majing implements IMajong{
    private final static String[] mahjong = {"1T", "2T", "3T", "4T", "5T",
            "6T", "7T", "8T", "9T", "1W", "2W", "3W", "4W", "5W", "6W", "7W", "8W", "9W",
            "1O","2O","3O","4O","5O","6O","7O","8O","9O",
            "DONG", "NAN", "XI", "BEI", "ZHONG", "FA", "BAI"};
    private static int[] c = new int[34];
    // private static String input[] = {"3S", "4S","5S","ZHONG" ,"BAI","BAI", "BAI"};
    private List<String> input = new ArrayList<String>();
    private List<String> listen = new ArrayList<String>();
    public majing()
    {

    }
    public static int convert(String s) {

        for (int i = 0; i < 34; i++)
            if (s.equals(mahjong[i]))
                return i;
        return -1;
    }

    private static boolean search(int dep) {
        int i;
        for (i = 0; i < 34; i++)
            if (c[i] >= 3) {
                if (dep == 3)
                    return true;
                c[i] -= 3;
                if (search(dep + 1))
                    return true;
                c[i] += 3;
            }
        for (i = 0; i <= 29; i++)
            if (i % 9 <= 6 && c[i] >= 1 && c[i + 1] >= 1 && c[i + 2] >= 1) {
                if (dep == 3)
                    return true;
                c[i]--;
                c[i + 1]--;
                c[i + 2]--;
                if (search(dep + 1))
                    return true;
                c[i]++;
                c[i + 1]++;
                c[i + 2]++;
            }
        return false;
    }

    private static boolean check() {
        int i;
        for (i = 0; i < 34; i++) {
            if (c[i] >= 2) {
                c[i] -= 2;
                if (search(0))
                    return true;
                c[i] += 2;
            }

        }
        return false;
    }

    private static int[] addListToMj(List<String> s) {
        int mj[] = new int[14];
        for (int i = 0; i < s.size(); i++) {

            mj[i] = convert(s.get(i));
        }
        for (int i = 0; i < (mj.length - s.size()); i++) {
            mj[s.size()] = 0;
        }
        return mj;
    }
    @Override
    public List<String> getListen() {

        boolean ok;
        String s;

        int mj[] = addListToMj(input);
        ok = false;
        for (int i = 0; i < 34; i++) {
            Arrays.fill(c, 0);
            for (int j = 0; j < 13; j++) c[mj[j]]++;
            if (c[i] >= 4) continue;
            c[i]++;//假设摸了这张牌
            if (check()) {//和了
                ok = true;
                listen.add(mahjong[i]);
            }
            c[i]--;
        }
        if (!ok) {
            System.out.println("not ready");
        }
        else
        {
            return listen;
        }
        return listen;
    }

    @Override
    public void input(List<String> s) {
        this.input =s;
    }

}
