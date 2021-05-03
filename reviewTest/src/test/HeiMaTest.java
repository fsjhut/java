package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;


public class HeiMaTest {
    String s= "A feather floats through the air。 " +
            "The falling feather。 A city, Savannah, is revealed in the background。 " +
            "The feather floats down toward the city below。 The feather drops down toward the street below, " +
            "as people walk past and cars drive by, and nearly lands on a man's shoulder。 " +
            "He walks across the street, causing the feather to be whisked back on its journey。 " +
            "The feather floats above a stopped car。 The car drives off right as the feather floats down " +
            "toward the street。 The feather floats under a passing car, then is sent flying back up in the " +
            "air。 A MAN sits on a bus bench。 The feather floats above the ground and finally lands on " +
            "the man's mud-soaked shoe。 The man reached down and picks up the feather。 His name is " +
            "FORREST GUMP。 He looks at the feather oddly, moves aside a box of chocolates from an " +
            "old suitcase, then opens the case。 Inside the old suitcase are an assortment of clothes," +
            " a ping-pong paddle, toothpaste and other personal items。 Forrest pulls out a book titled " +
            "'Curious George,' then places the feather inside the book。 Forrest closes the suitcase。 " +
            "Something in his eyes reveals that Forrest may not be all there。 Forrest looks right as " +
            "the sound of an arriving bus is heard。 A bus pulls up。 Forrest remains on the bus bench " +
            "as the bus continues on。 A BLACK WOMAN in a nurse's outfit steps up and sits down at the " +
            "bus bench next to Forrest。 The nurse begins to read a magazine as Forrest looks at her。  " +
            "Hello。 My name's Forrest Gump。 He opens a box of chocolates and holds it out for the nurse。" +
            " Forrest turns up the sidelines and runs toward the end zone。 Some opposing players fall down。 " +
            "Forrest runs along the sidelines。 The opposing players try to catch him。 Forrest runs into the " +
            "end zone as an opposing player dives at his feet。 The referee holds up his arm, singaling a touch" +
            " down。 The crowd cheers wildly。 Forrest continues to run, smashing through the band members, " +
            "then all the way toward the team tunnel。 The football coach looks at an asistant coach。Forrest " +
            "steps into Jenny's bedroom。 He carries a tray with breakfast on it。 Forrest looks at Jenny as she " +
            "sleeps。 Slowly she wakes up and looks at Forrest。 Forrest carries a suitcase as he runs down the road。 " +
            "Forrest runs past the row of mailboxes and turns into the drive。 Louise and others are on the front porch。";
    @Test
    public void test1(){
        String[] split = s.split("。");
        System.out.println(split.length);
        for (String s1 : split) {
            System.out.println(s1+"。");
        }
    }
    @Test
    public void test2(){
        String string = s.replaceAll("。", "");
        String string2 = string.replaceAll(",", "");
        String[] split = string2.split("[ ]");
        TreeSet<String> strings = new TreeSet<>((o2, o1) -> {
            if(o1.length()!=o2.length()){
                return o1.length()-o2.length();
            }else{
                return -o1.compareTo(o2);
            }
        });
        strings.addAll(Arrays.asList(split));
        int num = strings.size();
        System.out.println(num);
        for (String str : strings) {
            System.out.println(str);
        }
    }
    @Test
    public void test3(){
        String string = s.replaceAll("。", "");
        String string2 = string.replaceAll(",", "");
        String[] split = string2.split("[ ]");
        int num = 0;
        for (String s1 : split) {
            if("the".equals(s1)){
                num++;
            }
        }
        System.out.println(num);
    }
    @Test
    public void test4(){
        String string = s.replaceAll("。", "");
        String string2 = string.replaceAll(",", "");
        String[] split = string2.split("[ ]");
        int num = 0;
        HashSet<String> hashSet = new HashSet<>();
        for (String s1 : split) {
            char[] chars = s1.toCharArray();
            for (char aChar : chars) {
                if('a'== aChar||'A'==aChar){
                    num++;
                    hashSet.add(s1);
                }
            }
        }
        System.out.println(num);
        for (String aHashSet : hashSet) {
            System.out.println(aHashSet);
        }
    }
}
