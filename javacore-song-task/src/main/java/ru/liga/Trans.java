package ru.liga;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 14.03.2018.
 */
public class Trans {
    private SimpleMidiFile simpleMidiFile;
    public Trans(SimpleMidiFile simpleMidiFile){
        this.simpleMidiFile = simpleMidiFile;
    }
    public void make(){
        List<MidiTrack> midiTrackList = simpleMidiFile.getMidiFormat().getTracks();
        for (MidiTrack midiTrack: midiTrackList) {
            for(MidiEvent midiEvent : midiTrack.getEvents()){
                if(midiEvent.getClass().equals(Tempo.class)) {
                    Tempo tempo = (Tempo) midiEvent;
                    tempo.setBpm((float) (tempo.getBpm() * 1.2));
                }
                else if(midiEvent.equals(NoteOn.class)){
                    NoteOn noteOn = (NoteOn) midiEvent;
                    noteOn.setNoteValue(noteOn.getNoteValue() + 2);
                }
                else if(midiEvent.equals(NoteOff.class)){
                    NoteOff noteOff = (NoteOff) midiEvent;
                    noteOff.setNoteValue(noteOff.getNoteValue() + 2);
                }
            }
        }

        try {
            simpleMidiFile.getMidiFormat().writeToFile(new File("Zombie2.mid"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
