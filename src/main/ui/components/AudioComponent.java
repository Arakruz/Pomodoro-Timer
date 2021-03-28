package ui.components;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//TODO add class level comments
public class AudioComponent {
    private static final String finishFocusFile = "./data/Focus.wav";
    private static final String finishBreakFile = "./data/Break.wav";

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
            inputStream = new FileInputStream(finishFocusFile);
        } else {
            inputStream = new FileInputStream(finishBreakFile);
        }
        audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
    }
}
