package ru.liga;

import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Александр on 13.03.2018.
 */
public class Analyzer {
    public static NoteSign findHighestNote(List<Note> noteList){
        NoteSign highestNote= noteList.get(0).sign();
        for(int i = 1; i < noteList.size();i++){
            if(noteList.get(i).sign().higher(highestNote)){
                highestNote = noteList.get(i).sign();
            }
        }
        return highestNote;
    }
    public static NoteSign findLowestNote(List<Note> noteList){
        NoteSign lowestNote= noteList.get(0).sign();
        for(int i = 1; i < noteList.size();i++){
            if(noteList.get(i).sign().lower(lowestNote)){
                lowestNote = noteList.get(i).sign();
            }
        }
        return lowestNote;
    }
    public static Map<Long, Integer> analyzeDuration(List<Note> noteList){
        Map<Long, Integer> durationWithFrequency = new TreeMap<Long, Integer>();
        for (Note note : noteList) {
            if(durationWithFrequency.containsKey(note.durationTicks())){
                durationWithFrequency.put(note.durationTicks(), durationWithFrequency.get(note.durationTicks()) + 1);
            }
            else{
                durationWithFrequency.put(note.durationTicks(), 1);
            }
        }
        return durationWithFrequency;
    }
    public static Map<NoteSign, Integer> analyzeHeight(List<Note> noteList){
        Map <NoteSign, Integer> noteWithHeigh = new TreeMap<NoteSign, Integer>();
        for (Note note : noteList) {
            if(!noteWithHeigh.containsKey(note.sign())){
                noteWithHeigh.put(note.sign(), 1);
            }
            else{
                noteWithHeigh.put(note.sign(), noteWithHeigh.get(note.sign()) + 1);
            }
        }
        return noteWithHeigh;
    }
    public static Map<Integer, Integer> analyzeInterval(List<Note> noteList){
        Map<Integer, Integer> intervalWithFrequency = new TreeMap<>();
        Integer interval;
        for(int i = 1; i < noteList.size();i++){
            interval = noteList.get(i).sign().diffInSemitones(noteList.get(i-1).sign());
            if(intervalWithFrequency.containsKey(interval)){
                intervalWithFrequency.put(interval, intervalWithFrequency.get(interval)+1);
            }
            else {
                intervalWithFrequency.put(interval, 1);
            }
        }
        return intervalWithFrequency;
    }
}
