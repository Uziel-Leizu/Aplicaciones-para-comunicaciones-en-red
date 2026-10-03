
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import mp3agic.ID3v1;
import mp3agic.ID3v1Tag;
import mp3agic.ID3v2;
import mp3agic.ID3v24Tag;
import mp3agic.InvalidDataException;
import mp3agic.Mp3File;
import mp3agic.NotSupportedException;
import mp3agic.UnsupportedTagException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author axel
 */
public class Mp3GUI extends javax.swing.JFrame implements BasicPlayerListener {

    /**
     * Creates new form Mp3GUI
     */
    public Mp3GUI() {
        initComponents();
    }

    static void set_f(File ff){
        f=ff;
    }//set_f
    
    public void setController(BasicController controller)
  {
   // display("setController : "+controller);
  }//setController
    
   public void stateUpdated(BasicPlayerEvent event)
  {
    // Notification of BasicPlayer states (opened, playing, end of media, ...)
   // display("stateUpdated : "+event.toString());
  }//stateUpdated
   
   public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties)
  {
    // Pay attention to properties. It depends on underlying JavaSound SPI
    // MP3SPI provides mp3.equalizer.
   // display("progress : "+properties.toString());
  }//progress
   
   public void opened(Object stream, Map properties)
  {
    // Pay attention to properties. It's useful to get duration, 
    // bitrate, channels, even tag such as ID3v2.
   // display("opened : "+properties.toString()); 
  }//opened
   
    void getInfoMp3(File ff)throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException{
        try{
                    String arch = f.getAbsolutePath();
        Mp3File mp3file = new Mp3File(arch);
        System.out.println("Longitud del mp3: " + mp3file.getLengthInSeconds() + " segundos -> "+mp3file.getLengthInSeconds()/60+" minutos.");
        System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
        System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
        System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
        System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
        System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
        
        if (mp3file.hasId3v1Tag()) {
        	ID3v1 id3v1Tag = mp3file.getId3v1Tag();
        	System.out.println("Track: " + id3v1Tag.getTrack());
        	System.out.println("Artista: " + id3v1Tag.getArtist());
        	System.out.println("Titulo: " + id3v1Tag.getTitle());
        	System.out.println("Album: " + id3v1Tag.getAlbum());
        	System.out.println("Anio: " + id3v1Tag.getYear());
        	System.out.println("Genero: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
        	System.out.println("Comentario: " + id3v1Tag.getComment());
        }
        
        ID3v1 id3v1Tag;
        if (mp3file.hasId3v1Tag()) {
        	id3v1Tag =  mp3file.getId3v1Tag();
        } else {
        	id3v1Tag = new ID3v1Tag();
        	mp3file.setId3v1Tag(id3v1Tag);
        }
//        id3v1Tag.setTrack("5");
//        id3v1Tag.setArtist("An Artist");
//        id3v1Tag.setTitle("The Title");
//        id3v1Tag.setAlbum("The Album");
//        id3v1Tag.setYear("2001");
//        id3v1Tag.setGenre(12);
//        id3v1Tag.setComment("Some comment");
//        mp3file.save("MyMp3File.mp3");
        
        if (mp3file.hasId3v2Tag()) {
        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        	System.out.println("Track: " + id3v2Tag.getTrack());
        	System.out.println("Artist: " + id3v2Tag.getArtist());
        	System.out.println("Title: " + id3v2Tag.getTitle());
        	System.out.println("Album: " + id3v2Tag.getAlbum());
        	System.out.println("Year: " + id3v2Tag.getYear());
        	System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
        	System.out.println("Comment: " + id3v2Tag.getComment());
        	System.out.println("Composer: " + id3v2Tag.getComposer());
        	System.out.println("Publisher: " + id3v2Tag.getPublisher());
        	System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
        	System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
        	System.out.println("Copyright: " + id3v2Tag.getCopyright());
        	System.out.println("URL: " + id3v2Tag.getUrl());
        	System.out.println("Encoder: " + id3v2Tag.getEncoder());
        }
        
        if (mp3file.hasId3v2Tag()) {
        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();
            if (imageData != null) {
				String mimeType = id3v2Tag.getAlbumImageMimeType();
				System.out.println("Mime type: " + mimeType);
                                if(mimeType.compareTo("image/jpeg")==0)
                                    extension="jpg";
				// Write image to file - can determine appropriate file extension from the mime type
				RandomAccessFile file = new RandomAccessFile("portada."+extension, "rw");
				file.write(imageData);
				file.close();
                                File tmpp = new File("");
                                System.out.println("Portada: "+tmpp.getAbsolutePath()+"\\"+nombrePortada+extension);
                                ImageIcon icono = new ImageIcon(tmpp.getAbsolutePath()+"\\"+nombrePortada+extension);
                                Icon icono2 = new ImageIcon(icono.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),Image.SCALE_DEFAULT));
                                jLabel1.setIcon(icono2);
                                this.repaint();
            }else{
                                File tmpp = new File("");
                                System.out.println("Portada: no disponible");
                                ImageIcon icono = new ImageIcon(tmpp.getAbsolutePath()+"\\nodisponible.png");
                                Icon icono2 = new ImageIcon(icono.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),Image.SCALE_DEFAULT));
                                jLabel1.setIcon(icono2);
                                this.repaint();
            }//else
        }
        
        ID3v2 id3v2Tag;
        if (mp3file.hasId3v2Tag()) {
        	id3v2Tag =  mp3file.getId3v2Tag();
        } else {
        	id3v2Tag = new ID3v24Tag();
        	mp3file.setId3v2Tag(id3v2Tag);
        }
//        id3v2Tag.setTrack("5");
//        id3v2Tag.setArtist("An Artist");
//        id3v2Tag.setTitle("The Title");
//        id3v2Tag.setAlbum("The Album");
//        id3v2Tag.setYear("2001");
//        id3v2Tag.setGenre(12);
//        id3v2Tag.setComment("Some comment");
//        id3v2Tag.setComposer("The Composer");
//        id3v2Tag.setPublisher("A Publisher");
//        id3v2Tag.setOriginalArtist("Another Artist");
//        id3v2Tag.setAlbumArtist("An Artist");
//        id3v2Tag.setCopyright("Copyright");
//        id3v2Tag.setUrl("http://foobar");
//        id3v2Tag.setEncoder("The Encoder");
//        mp3file.save("MyMp3File.mp3");
	
        }catch(Exception e){
            e.printStackTrace();
        }
    }//getInfoMp3
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\axel\\Documents\\NetBeansProjects\\mp3agic\\nodisponible.png")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));

        jButton1.setText("Seleccionar canción");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reproducir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pausa/Reanudar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Detener");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jLabel2.setText("URL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.b=0;
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mp3", "MP3");
        jf.setFileFilter(filter);
        //jf.addChoosableFileFilter(new FileNameExtensionFilter("*.mp3", "Mp3"));
        int r = jf.showOpenDialog(null);
        jf.requestFocus();
        if(r==JFileChooser.APPROVE_OPTION){
            f = jf.getSelectedFile();
            try{
                getInfoMp3(f);
            }catch(Exception e){e.printStackTrace();}
        }//if
        System.out.println("Archivo elegido:"+f.getAbsolutePath());
        /////////abre mp3 para inicializar reproductor////
               // Instantiate BasicPlayer.
      player = new BasicPlayer();
      // BasicPlayer is a BasicController.
      control = (BasicController) player;
      // Register BasicPlayerTest to BasicPlayerListener events.
      // It means that this object will be notified on BasicPlayer
      // events such as : opened(...), progress(...), stateUpdated(...)
      player.addBasicPlayerListener(this);

  try
     { 
      // Open file, or URL or Stream (shoutcast, icecast) to play.
      control.open(f);

      // control.open(new URL("http://yourshoutcastserver.com:8000"));
      // Set Volume (0 to 1.0).
      control.setGain(0.85);
      // Set Pan (-1.0 to 1.0).
      control.setPan(0.0);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }//catch
        //////////////////////////////////////////
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try
     { 
      this.b=1;
      // Open file, or URL or Stream (shoutcast, icecast) to play.
      //control.open(f);

      // control.open(new URL("http://yourshoutcastserver.com:8000"));

      // Start playback in a thread.
      control.play();

      // Set Volume (0 to 1.0).
      //control.setGain(0.85);
      // Set Pan (-1.0 to 1.0).
      //control.setPan(0.0);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }//catch

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
try
     { 
      // Open file, or URL or Stream (shoutcast, icecast) to play.
      //control.open(f);

      // control.open(new URL("http://yourshoutcastserver.com:8000"));

      // Start playback in a thread.
      if(this.b==1){
          this.b=2;
          control.pause();
      }else if(this.b==2){
          this.b=1;
          control.resume();
      }//else

      // Set Volume (0 to 1.0).
      //control.setGain(0.85);
      // Set Pan (-1.0 to 1.0).
      //control.setPan(0.0);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }//catch

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
  ///stop
  try
     { 
      this.b=1;
      // Open file, or URL or Stream (shoutcast, icecast) to play.
      //control.open(f);

      // control.open(new URL("http://yourshoutcastserver.com:8000"));

      // Start playback in a thread.
      this.b= -1;
      control.stop();

      // Set Volume (0 to 1.0).
      //control.setGain(0.85);
      // Set Pan (-1.0 to 1.0).
      //control.setPan(0.0);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }//catch

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mp3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mp3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mp3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mp3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mp3GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    static File f=null;
    String nombrePortada="portada.";
    String extension="";
    BasicPlayer player= null;
    BasicController control = null;
    static int b;
}
