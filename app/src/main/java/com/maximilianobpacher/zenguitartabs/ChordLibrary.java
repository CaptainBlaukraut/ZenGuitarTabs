package com.maximilianobpacher.zenguitartabs;

import android.content.Context;

import com.maximilianobpacher.zenguitartabs.Chord;
import com.maximilianobpacher.zenguitartabs.R;

import java.util.*;

/**
 * Created by MaximilianObpacher on 27/03/15.
 */

// Chord Library Creates and returns Arraylists of the Chords, that are required for the levels

public class ChordLibrary{

    // Chords for Level 1
    static Chord a = new Chord(R.drawable.achord,"A");
    static Chord b = new Chord(R.drawable.bchord,"B");
    static Chord c = new Chord(R.drawable.cchord,"C");
    static Chord d = new Chord(R.drawable.dchord,"D");
    static Chord e = new Chord(R.drawable.echord,"E");
    static Chord f = new Chord(R.drawable.fchord,"F");
    static Chord g = new Chord(R.drawable.gchord,"G");

    // Chords for Level 2

    static Chord am = new Chord(R.drawable.amin,"Amin");
    static Chord bm = new Chord(R.drawable.bmin,"Bmin");
    static Chord cm = new Chord(R.drawable.cmin,"Cmin");
    static Chord dm = new Chord(R.drawable.dmin,"Dmin");
    static Chord em = new Chord(R.drawable.emin,"Emin");
    static Chord fm = new Chord(R.drawable.fmin,"Fmin");
    static Chord gm = new Chord(R.drawable.gmin,"Gmin");

    // Chords for Level 3

    static Chord a7 = new Chord(R.drawable.a7,"A7");
    static Chord b7 = new Chord(R.drawable.b7,"B7");
    static Chord c7 = new Chord(R.drawable.c7,"C7");
    static Chord d7 = new Chord(R.drawable.d7,"D7");
    static Chord e7 = new Chord(R.drawable.e7,"E7");
    static Chord f7 = new Chord(R.drawable.f7,"F7");
    static Chord g7 = new Chord(R.drawable.g7,"G7");

    // create Chord List

    public static ArrayList<Chord> getChordList(int level) {

        ArrayList<Chord> chords = new ArrayList<Chord>();

        //InfiniteMode

        if (level == 0) {
            chords.add(a);
            chords.add(b);
            chords.add(c);
            chords.add(d);
            chords.add(e);
            chords.add(f);
            chords.add(g);
            chords.add(am);
            chords.add(bm);
            chords.add(cm);
            chords.add(dm);
            chords.add(em);
            chords.add(fm);
            chords.add(gm);
            chords.add(a7);
            chords.add(b7);
            chords.add(c7);
            chords.add(d7);
            chords.add(e7);
            chords.add(f7);
            chords.add(g7);
        }

        //Level1

        if (level == 1) {
            chords.add(a);
            chords.add(b);
            chords.add(c);
            chords.add(d);
            chords.add(e);
            chords.add(f);
            chords.add(g);

            //Shuffle for Random order

            Collections.shuffle(chords);

            return chords;
        }

        //Level1

        if (level == 2) {
            chords.add(am);
            chords.add(bm);
            chords.add(cm);
            chords.add(dm);
            chords.add(em);
            chords.add(fm);
            chords.add(gm);
            Collections.shuffle(chords);

            return chords;
        } else {
            chords.add(a7);
            chords.add(b7);
            chords.add(c7);
            chords.add(d7);
            chords.add(e7);
            chords.add(f7);
            chords.add(g7);
            Collections.shuffle(chords);

            return chords;
        }
    }
}
