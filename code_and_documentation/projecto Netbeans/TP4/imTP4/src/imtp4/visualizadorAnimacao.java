package imtp4;

import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.util.Iterator;
import java.awt.event.WindowEvent;

import fr.apteryx.imageio.dicom.*;

public class visualizadorAnimacao extends Thread
{   
    String ficheiro;
    String idPac;
    String nomePac;
    String tipoEx;
    int frameT;
    int numF; 
    
    public visualizadorAnimacao(String ficheiro, String idPac, String nomePac, String tipoEx, int frameT, int numF) 
    {
        this.ficheiro = ficheiro;
        this.idPac = idPac;
        this.nomePac = nomePac;
        this.tipoEx = tipoEx;
        this.frameT = frameT;
        this.numF = numF;
        start();
    }
    
    public void run()
    {
        try
       {
            Plugin.setLicenseKey("NM73KIZUPKHLFLAQM5L0V9U");                 
            ImageIO.scanForPlugins();
                                                
            File f = new File(ficheiro);                      
            Iterator readers = ImageIO.getImageReadersByFormatName("dicom");
	    DicomReader reader = (DicomReader)readers.next();
			          
            reader.setInput(new FileImageInputStream(f));

            DicomMetadata dmd = reader.getDicomMetadata();
                                                                                            
            BufferedImage[] biArray = new BufferedImage[numF];
                        
            for (int i=0;i<numF;i++)
            {
                   biArray[i]  = dmd.applyGrayscaleTransformations(reader.read(i), 0);
            }
                        
	    JFrame jf = new JFrame();
            jf.setTitle(idPac+" - "+nomePac+" - "+tipoEx);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                
            for (int j=0;j<biArray.length;j++)
            {
                final BufferedImage bi = biArray[j];
                final Rectangle bounds = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
			JPanel panel = new JPanel()
			{
				public void paintComponent(Graphics g)
				{
					Rectangle r = g.getClipBounds();
					((Graphics2D)g).fill(r);
					if (bounds.intersects(r))
						try
						{
							g.drawImage(bi, 0, 0, null);                                                        
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
				}
			};
			jf.getContentPane().add(panel);
			panel.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
			jf.pack();
			jf.setVisible(true);
                        Thread.sleep(frameT);                        
            }                                    
       }
       catch(Exception e)
       {
            System.out.println(e);
       }        
    }    
}
