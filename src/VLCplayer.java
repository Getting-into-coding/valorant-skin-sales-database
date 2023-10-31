import javax.swing.*;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.*;

public class VLCplayer extends JPanel{

    private MediaPlayerFactory factory;

    private EmbeddedMediaPlayer mediaPlayer;

    public VLCplayer(){
        init();
    }
    
    private void init(){
        factory = new MediaPlayerFactory();
        mediaPlayer = factory.mediaPlayers().newEmbeddedMediaPlayer();
        Canvas canvas = new Canvas();

        setLayout(new BorderLayout());
        add(canvas);

        mediaPlayer.videoSurface().set(factory.videoSurfaces().newVideoSurface(canvas));

        
        
    }

    public void play(){
        if (mediaPlayer.status().isPlaying()){
            mediaPlayer.controls().stop();
        }
        mediaPlayer.media().play("Video/HomeScreen.mp4");
    }

    public void stop(){
        mediaPlayer.controls().stop();
        mediaPlayer.release();
        factory.release();
    }

}
