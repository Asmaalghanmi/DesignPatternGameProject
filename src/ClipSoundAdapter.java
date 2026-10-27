/**
 *
 * @author Nada
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ClipSoundAdapter implements SoundPlayer {
    private Clip clip;

    public ClipSoundAdapter(String path) {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (Exception e) {
            e.printStackTrace(); // اللعبة تشتغل حتى لو الملف ناقص
        }
    }

    @Override
    public void play() {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }
}