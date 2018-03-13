package ru.liga;

import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Александр on 13.03.2018.
 */
public class WriterInFile {
    private PrintStream writer;
    public WriterInFile(){
        try {
            writer = new PrintStream(new FileOutputStream("Analyze.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void writeNumber(SimpleMidiFile simpleMidiFile){
        writer.println("Всего нот: " + simpleMidiFile.vocalNoteList().size());
        writer.println("<p>");
    }
    public void writeRange(SimpleMidiFile simpleMidiFile){
        NoteSign highestNote = Analyzer.findHighestNote(simpleMidiFile.vocalNoteList());
        NoteSign lowestNote = Analyzer.findLowestNote(simpleMidiFile.vocalNoteList());
        writer.println("Анализ диапазона:");
        writer.println("верхняя: " + highestNote.fullName());
        writer.println("нижняя: " + lowestNote.fullName());
        writer.println("диапазон: " + highestNote.diffInSemitones(lowestNote));
        writer.println("<p>");
    }
    public void writeDuration(SimpleMidiFile simpleMidiFile){
        writer.println("Анализ длительности нот (мс):");
        Analyzer.analyzeDuration(simpleMidiFile.vocalNoteList()).forEach((duration, freguency) -> writer.println(Math.round(duration * simpleMidiFile.tickInMs()) + ": " + freguency));
        writer.println("<p>");
    }
    public void writeHeight(SimpleMidiFile simpleMidiFile){
        writer.println("Анализ нот по высоте:");
        Analyzer.analyzeHeight(simpleMidiFile.vocalNoteList()).forEach((note, height) -> writer.println(note.fullName() + ": " + height));
        writer.println("<p>");
    }
    public void writeIntervals(SimpleMidiFile simpleMidiFile){
        writer.println("Анализ интервалов:");
        Analyzer.analyzeInterval(simpleMidiFile.vocalNoteList()).forEach((interval, frequency) -> writer.println(interval + ": " + frequency));
    }
}
