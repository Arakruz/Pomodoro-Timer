package ui.components;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// Represents the AudioComponent of the program, allowing it to play the specified sounds in FINISH_FOCUS_FILE or
// FINISH_BREAK_FILE
public class AudioComponent {
    private static final String FINISH_FOCUS_FILE = "./data/Focus.wav";
    private static final String FINISH_BREAK_FILE = "./data/Break.wav";

    InputStream inputStream;
    AudioStream audioStream;
    CounterPanel.PossibleTimers currentTimer;

    public AudioComponent(CounterPanel.PossibleTimers audioToPlay) throws IOException {
        currentTimer = audioToPlay;
        playAudio();
    }


    // MODIFIES: this
    // EFFECTS:  plays the audio file from finishFocusFile or finishBreakFile based on currentTimer
    private void playAudio() throws IOException {
        if (currentTimer == CounterPanel.PossibleTimers.FOCUS) {
            inputStream = new FileInputStream(FINISH_FOCUS_FILE);
        } else {
            inputStream = new FileInputStream(FINISH_BREAK_FILE);
        }
        audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
    }
}
