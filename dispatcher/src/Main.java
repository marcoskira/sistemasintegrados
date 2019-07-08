import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;
import model.Image;
import model.Dispatcher;

public class Main  {

    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask task = new Dispatcher();

        timer.schedule(task, 50, 6000);

    }
}