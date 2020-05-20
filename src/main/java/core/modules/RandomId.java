package core.modules;

import java.util.Random;

public class RandomId {
    public  static int setRandomId() {
        int max = 2147483647;
        int min = -21474891;
        int r=  min + (int) (Math.random() * max);;
        System.out.println(r);
        return r;
    }

}
