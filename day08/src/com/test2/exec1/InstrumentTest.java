package com.test2.exec1;

import java.util.Objects;

/**
 * @author SunHang
 * @className: InstrumentTest
 * @description:
 * @createTime 2021/3/24 16:42
 */
public class InstrumentTest {
    public static void main(String[] args) {
        Instrument piano = new Piano("钢琴", 10000, "贝多芬钢琴曲");
        Instrument violin = new Violin("小提琴", 2000, "睡眠曲");
        System.out.println(InstrumentTest.playMusic(piano));
        System.out.println(InstrumentTest.playMusic(violin));
//        System.out.println(InstrumentTest.playMusic(new Instrument()));
    }

    public static String playMusic(Instrument instrument) {
        Objects.requireNonNull(instrument);
        String music = "";
        if (instrument instanceof Piano) {
            Piano piano = (Piano) instrument;
            music = piano.getPianoMusic();
        }


        if (instrument instanceof Violin) {
            Violin violin = (Violin) instrument;
            music = violin.getViolinMusic();
        }
        return instrument.getName() + " 正在演奏" + music + "-----";
    }
}
