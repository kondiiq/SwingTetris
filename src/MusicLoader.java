import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicLoader {
    public void playSound(String path){
        try{
            File musicPath =  new File(path);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                JOptionPane.showMessageDialog(null,"Press to stop playing");
            }
            else
                System.out.println("Can't find file");
        }
        catch(Exception e){

            e.printStackTrace();
        }

        }
}




