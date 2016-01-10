package edu.hhu.zhucz.AudioStream;
import edu.hhu.zhucz.AudioStream.VolPlayer;
import edu.hhu.zhucz.AudioStream.VolRecorder;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final int mi1 = Menu.FIRST ;
    public static final int mi2 = Menu.FIRST + 1 ;
    public static final int mi3 = Menu.FIRST + 2 ;
 
    protected VolPlayer     m_player ;
    protected VolRecorder   m_recorder ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    case R.id.mi1:
        {
         m_player = new VolPlayer() ;
            m_recorder = new VolRecorder() ;
            m_recorder.init() ;
            m_player.init() ;
            m_recorder.start() ;
            m_player.start() ;
           
        }
        break ;
    case R.id.mi2:
        {  
         m_recorder.free() ;
            m_player.free() ;

            m_player = null ;
            m_recorder = null ;
        }
        break ;
    case R.id.mi3:
        {
           int pid = android.os.Process.myPid() ;
            android.os.Process.killProcess(pid) ;
           //Toast.makeText(getApplicationContext(), "ÍË³ö?", Toast.LENGTH_LONG).show();
        }
        break ;
    default:
        break ;
    }

    return super.onOptionsItemSelected(item);
}

}
