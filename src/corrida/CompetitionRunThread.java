package corrida;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucio
 */
public class CompetitionRunThread extends Thread {
	
	private final CompetitionWinnerLock lock;
	private int progress;
	private final int FINISH_VALUE = 50; // The value it has to reach to be the winner
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	public CompetitionRunThread(CompetitionWinnerLock lock) {
		this.lock = lock;
		this.progress = 0;
		changeSupport.firePropertyChange("progress", null, this.progress);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(CompetitionRunThread.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		while (!this.lock.hasWinner()) {
			
			synchronized (this) {
				changeSupport.firePropertyChange("progress", this.progress, this.progress += new Random().nextInt(10));
			}
			
			if (this.progress >= FINISH_VALUE) {
				synchronized (lock) {
					System.out.println("lock = " +this.getName());
					this.lock.setWinner(this.getName());
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Logger.getLogger(CompetitionRunThread.class.getName()).log(Level.SEVERE, null, e);
			}
        }
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		this.progress = progress;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
       changeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
}
