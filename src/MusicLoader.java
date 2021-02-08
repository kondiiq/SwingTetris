import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicLoader {
    void playMusic(String musicLocation){
        try{
            File musicPath = new File(musicLocation);
                    if(musicPath.exists()){
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();

                        JOptionPane.showMessageDialog(null,"Press OK to stop playing");
                    }
                    else{
                        System.out.println("Cant find file");
                    }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
