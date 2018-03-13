package ru.liga;

import ch.qos.logback.core.Appender;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;
import sun.applet.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class );
    public static void main(String[] args) {
        try(PrintStream writer = new PrintStream(new FileOutputStream("Analyze.txt"))) {
            SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
            NoteSign highestNote = findHighestNote(simpleMidiFile.vocalNoteList());
            NoteSign lowestNote = findLowestNote(simpleMidiFile.vocalNoteList());
            logger.info("Всего нот: " + simpleMidiFile.vocalNoteList().size());
          //  System.out.println("Всего нот: " + simpleMidiFile.vocalNoteList().size());
            writer.println("Всего нот: " + simpleMidiFile.vocalNoteList().size());
          //  System.out.println("<p>");
            writer.println("<p>");

//            System.out.println("Анализ диапазона:");
//            System.out.println("верхняя: " + highestNote.fullName());
//            System.out.println("нижняя: " + lowestNote.fullName());
//            System.out.println("диапазон: " + highestNote.diffInSemitones(lowestNote));
//            System.out.println("<p>");
            logger.info("Анализ диапазона:");
            logger.info("верхняя: " + highestNote.fullName());
            logger.info("нижняя: " + lowestNote.fullName());
            logger.info("диапазон: " + highestNote.diffInSemitones(lowestNote));
            writer.println("Анализ диапазона:");
            writer.println("верхняя: " + highestNote.fullName());
            writer.println("нижняя: " + lowestNote.fullName());
            writer.println("диапазон: " + highestNote.diffInSemitones(lowestNote));
            writer.println("<p>");


//            System.out.println("Анализ длительности нот (мс):");
//            analyzeDuration(simpleMidiFile.vocalNoteList()).forEach((duration, freguency) -> System.out.println(Math.round(duration * simpleMidiFile.tickInMs()) + ": " + freguency));
//            System.out.println("<p>");
            logger.info("Анализ длительности нот (мс):");
            analyzeDuration(simpleMidiFile.vocalNoteList()).forEach((duration, freguency) -> logger.info(Math.round(duration * simpleMidiFile.tickInMs()) + ": " + freguency));
            writer.println("Анализ длительности нот (мс):");
            analyzeDuration(simpleMidiFile.vocalNoteList()).forEach((duration, freguency) -> writer.println(Math.round(duration * simpleMidiFile.tickInMs()) + ": " + freguency));
            writer.println("<p>");

//            System.out.println("Анализ нот по высоте:");
//            analyzeHeight(simpleMidiFile.vocalNoteList()).forEach((note, height) -> System.out.println(note.fullName() + ": " + height));
//            System.out.println("<p>");
            logger.info("Анализ нот по высоте:");
            analyzeHeight(simpleMidiFile.vocalNoteList()).forEach((note, height) -> logger.info(note.fullName() + ": " + height));
            writer.println("Анализ нот по высоте:");
            analyzeHeight(simpleMidiFile.vocalNoteList()).forEach((note, height) -> writer.println(note.fullName() + ": " + height));
            writer.println("<p>");

//            System.out.println("Анализ интервалов:");
//            analyzeInterval(simpleMidiFile.vocalNoteList()).forEach((interval ,frequency) -> System.out.println(interval + ": " + frequency));
            logger.info("Анализ интервалов:");
            analyzeInterval(simpleMidiFile.vocalNoteList()).forEach((interval ,frequency) -> logger.info(interval + ": " + frequency));
            writer.println("Анализ интервалов:");
            analyzeInterval(simpleMidiFile.vocalNoteList()).forEach((interval ,frequency) -> writer.println(interval + ": " + frequency));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
    }

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
