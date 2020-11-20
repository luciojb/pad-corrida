package corrida;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucio
 */
public class MainTest {

	public static void main(String[] args) {
		CompetitionWinnerLock lock = new CompetitionWinnerLock();
		CompetitionRunThread t1 = new CompetitionRunThread(lock);
		CompetitionRunThread t2 = new CompetitionRunThread(lock);
		
		
		t1.start();
		t2.start();
		while (!lock.hasWinner()) {
			// wait
		}

		System.out.println("WINNER: " + lock.getWinnerName());
		
//		new Thread() {
//			@Override
//			public void run() {
//				
//			}
//		}.start();
	}	
	
}
