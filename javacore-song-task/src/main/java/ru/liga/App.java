package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

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
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try (PrintStream writer = new PrintStream(new FileOutputStream("Analyze.txt"))) {
            SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
            LoggerMidi loggerMidi = new LoggerMidi();
            WriterInFile writerInFile = new WriterInFile();

            loggerMidi.loggingNumber(simpleMidiFile);
            writerInFile.writeNumber(simpleMidiFile);

            loggerMidi.loggingRange(simpleMidiFile);
            writerInFile.writeRange(simpleMidiFile);

            loggerMidi.loggingDuration(simpleMidiFile);
            writerInFile.writeDuration(simpleMidiFile);

            loggerMidi.loggingHeight(simpleMidiFile);
            writerInFile.writeHeight(simpleMidiFile);

            loggerMidi.loggingIntervals(simpleMidiFile);
            writerInFile.writeIntervals(simpleMidiFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
    }
}

