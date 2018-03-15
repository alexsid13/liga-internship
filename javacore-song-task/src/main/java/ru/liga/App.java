package ru.liga;


import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.*;


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

    public static void main(String[] args) {
            if(args[1].equals("analyze")){
                    if(args.length >2){
                            SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new File(args[0]));
                            WriterInFile writerInFile = new WriterInFile(simpleMidiFile);
                            writerInFile.writeNumber();
                            writerInFile.writeRange();
                            writerInFile.writeDuration();
                            writerInFile.writeHeight();
                            writerInFile.writeIntervals();
                    }
                    SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new File(args[0]));
                    LoggerMidi loggerMidi = new LoggerMidi(simpleMidiFile);
                    loggerMidi.loggingNumber();
                    loggerMidi.loggingRange();
                    loggerMidi.loggingDuration();
                    loggerMidi.loggingHeight();
                    loggerMidi.loggingIntervals();
            }
            else if(args[1].equals("change")) {
                    SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new File(args[0]));
                    Trans trans = new Trans(simpleMidiFile);
                    trans.make();
            }
    }
}

