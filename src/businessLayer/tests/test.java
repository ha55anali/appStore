package businessLayer.tests;

import java.util.*;
class test {
    public static void main(String[] args) {
        System.out.println("oe");
        blInterface.appInterface ap = businessLayer.blFactory.getAppObject();
        try {
            List<blInterface.App> a = ap.showAllApps();
            System.out.println(a.get(0).AppID);
            System.out.println(a.get(1).AppID);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
