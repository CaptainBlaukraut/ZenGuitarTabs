package com.maximilianobpacher.zenguitartabs;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.maximilianobpacher.zenguitartabs.R;

/**
 * Created by MaximilianObpacher on 27/03/15.
 */

// Class Chord for Objects of the type chord

public class Chord {

    private int chordImage;
    private String chordName = "";

    public Chord(int chordImage, String chordName){
        this.chordImage = chordImage;
        this.chordName = chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }

    public String getChordName() {
        return chordName;
    }

    public void setChordImage(int image){
        this.chordImage = image;
    }

    public int getChordImage(){
        return chordImage;
    }
}
