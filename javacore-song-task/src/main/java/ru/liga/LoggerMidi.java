package ru.liga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

/**
 * Created by Александр on 13.03.2018.
 */
public class LoggerMidi {
    private static Logger logger = LoggerFactory.getLogger(LoggerMidi.class);
    public void loggingNumber(SimpleMidiFile simpleMidiFile){
        logger.info("Всего нот: " + simpleMidiFile.vocalNoteList().size());
    }
    public void loggingRange(SimpleMidiFile simpleMidiFile){
        NoteSign highestNote = Analyzer.findHighestNote(simpleMidiFile.vocalNoteList());
        NoteSign lowestNote = Analyzer.findLowestNote(simpleMidiFile.vocalNoteList());
        logger.info("Анализ диапазона:");
        logger.info("верхняя: " + highestNote.fullName());
        logger.info("нижняя: " + lowestNote.fullName());
        logger.info("диапазон: " + highestNote.diffInSemitones(lowestNote));
    }
    public void loggingDuration(SimpleMidiFile simpleMidiFile){
        logger.info("Анализ длительности нот (мс):");
        Analyzer.analyzeDuration(simpleMidiFile.vocalNoteList()).forEach((duration, freguency) -> logger.info(Math.round(duration * simpleMidiFile.tickInMs()) + ": " + freguency));
    }
    public void loggingHeight(SimpleMidiFile simpleMidiFile){
        logger.info("Анализ нот по высоте:");
        Analyzer.analyzeHeight(simpleMidiFile.vocalNoteList()).forEach((note, height) -> logger.info(note.fullName() + ": " + height));
    }
    public void loggingIntervals(SimpleMidiFile simpleMidiFile){
        logger.info("Анализ интервалов:");
        Analyzer.analyzeInterval(simpleMidiFile.vocalNoteList()).forEach((interval, frequency) -> logger.info(interval + ": " + frequency));
    }
}
