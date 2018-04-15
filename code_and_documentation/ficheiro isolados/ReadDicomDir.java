package imtp4;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;

import fr.apteryx.imageio.dicom.DataSet;
import fr.apteryx.imageio.dicom.DicomReader; 
import fr.apteryx.imageio.dicom.FileSet; 
import fr.apteryx.imageio.dicom.Tag; 
import fr.apteryx.imageio.dicom.Plugin;

public class ReadDicomDir 
{        
    public void ReadDicomDir()
    {
        Plugin.setLicenseKey("NM73KIZUPKHLFLAQM5L0V9U"); 
    }
    
    public static Vector leDirectorio(String path) throws Exception
    {
        Plugin.setLicenseKey("NM73KIZUPKHLFLAQM5L0V9U");
        
        Vector resultadoLeitura = new Vector();
        Vector dadosTabela = new Vector();
        Vector linhaTabela = new Vector(4);
        String idPaciente = null;
        String nomePaciente = null;
        String dataNascimento = null;  
        String tipoExame = null;             
        Vector atributosExames = new Vector();
        Vector filesExames = new Vector();
        Vector frameTime = new Vector();
        Vector numFrames = new Vector();
        DataSet atributosPacienteDR = new DataSet();
        DataSet atributosEstudoDR = new DataSet();
        DataSet atributosSerieDR = new DataSet();
        DataSet atributosImagemDR = new DataSet();
        
        
                        
        //baseado no ficheiro exemplo
        File f = new File(path+"/DICOMDIR");
        
        Iterator readers = ImageIO.getImageReadersByFormatName("dicom");
        DicomReader reader = (DicomReader)readers.next();
        reader.setInput(new FileImageInputStream(f));
        //
        
        FileSet fs = new FileSet(f,reader);
        
        FileSet.Directory rootDE = fs.getRootDirectory();
        int numDRrootDE = rootDE.getNumRecords();
        
        for(int i=0;i < numDRrootDE; i++)
        {            
            FileSet.Record rootDEDR = rootDE.getRecord(i);            
            if(rootDEDR.getType().equals("PATIENT"))
            {
                atributosPacienteDR = rootDEDR.getAttributes();
                
                idPaciente = rootDEDR.getAttribute(Tag.PatientID).toString(); 
                nomePaciente = rootDEDR.getAttribute(Tag.PatientsName).toString();
                dataNascimento = rootDEDR.getAttribute(Tag.PatientsBirthDate).toString();
                linhaTabela.add(0,idPaciente);
                linhaTabela.add(1,nomePaciente);
                linhaTabela.add(2,dataNascimento);             
                
                
                
                FileSet.Directory patientDE = rootDEDR.getLowerLevelDirectory();
               
                int numDRpatientDE = patientDE.getNumRecords();
                
                for(int j=0;j < numDRpatientDE; j++)
                {
                    FileSet.Record patientDEDR = patientDE.getRecord(j);                    
                    
                    if(patientDEDR.getType().equals("STUDY"))
                    {
                        atributosEstudoDR = patientDEDR.getAttributes();
                        
                        FileSet.Directory studyDE = patientDEDR.getLowerLevelDirectory();
                        int numDRstudyDE = studyDE.getNumRecords();
                        for(int k=0;k<numDRstudyDE;k++)
                        {
                            FileSet.Record studyDEDR = studyDE.getRecord(k);
                            if (studyDEDR.getType().equals("SERIES"))
                            {
                                atributosSerieDR = studyDEDR.getAttributes();
                                tipoExame=studyDEDR.getAttribute(Tag.Modality).toString();
                                linhaTabela.add(3,tipoExame);
                                
                                FileSet.Directory seriesDE = studyDEDR.getLowerLevelDirectory();
                                int numDRseriesDE = seriesDE.getNumRecords();  
                                
                                
                                for(int l=0;l<numDRseriesDE;l++)
                                {
                                    FileSet.Record seriesDEDR = seriesDE.getRecord(l);
                                    
                                    if(seriesDEDR.getType().equals("IMAGE"))
                                    {
                                           atributosImagemDR = seriesDEDR.getAttributes();
                                           File exame = seriesDEDR.getFile();
                                           String time = seriesDEDR.getAttribute(Tag.FrameTime).toString();
                                           String frames = seriesDEDR.getAttribute(Tag.NumberOfFrames).toString();                                          
                                           filesExames.add(exame);
                                           frameTime.add(time);
                                           numFrames.add(frames);                                          
                                          
                                           dadosTabela.add(linhaTabela);
                                           Atributes atributos = new Atributes(atributosPacienteDR,atributosEstudoDR,atributosSerieDR,atributosImagemDR);
                                           atributosExames.add(atributos);
                                    }                                    
                                }                                
                            }                                                                                   
                        }                        
                    }                    
                }                
            }            
        }
        resultadoLeitura.add(0,dadosTabela);
        resultadoLeitura.add(1,atributosExames);
        resultadoLeitura.add(2,filesExames);
        resultadoLeitura.add(3,frameTime);
        resultadoLeitura.add(4,numFrames);
        
        return resultadoLeitura;   
    }
}
