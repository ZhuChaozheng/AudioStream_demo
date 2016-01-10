package edu.hhu.zhucz.AudioStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class VolRecorder extends Thread
{
 
    protected AudioRecord record ;
    protected int         in_buf_size ;
    protected byte []     in_bytes ;
    protected boolean     keep_running ;
    protected Socket      s;
    protected DataOutputStream dout;
    protected LinkedList<byte[]>  m_in_q ;
    protected ServerSocket ss;
    public void init()
    {
     in_buf_size =  AudioRecord.getMinBufferSize(44100,
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);
  
  record = new AudioRecord(MediaRecorder.AudioSource.MIC,
  44100,
  AudioFormat.CHANNEL_IN_MONO,
  AudioFormat.ENCODING_PCM_16BIT, in_buf_size) ;
 
  in_bytes = new byte [in_buf_size] ;
 
  keep_running = true ;
  m_in_q=new LinkedList<byte[]>();
    }
  
    public void run ()
 {
      try
      {
    	 ss = new ServerSocket(5060);
     	 s = ss.accept();
     	 dout=new DataOutputStream(s.getOutputStream());
          byte [] bytes_pkg ;		//用于存储录制好的语音
             record.startRecording() ;  //开始录音
             while(keep_running)
             {
                 record.read(in_bytes, 0, in_buf_size) ;
                 bytes_pkg = in_bytes.clone() ;
                 if(m_in_q.size() >= 2)
                 {
                    dout.write(m_in_q.removeFirst() , 0, m_in_q.removeFirst() .length);
                 }
                    m_in_q.add(bytes_pkg) ;
             }
             record.stop() ;
             record = null ;
             in_bytes = null ;
       dout.close();
     
      }
      catch(Exception e)
      {
       e.printStackTrace();
      }
    }
    public void free()
 {
  keep_running = false ;
        try {
            Thread.sleep(1000) ;
        } catch(Exception e) {
            Log.d("sleep exceptions...\n","") ;
        }
 }
}

