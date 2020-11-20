package corrida;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 *
 * @author lucio
 */
public class CompetitionWinnerLock {
	private volatile boolean hasWinner;
	private String winnerName;
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public CompetitionWinnerLock() {
		this.hasWinner = false;
	}
	
	public synchronized void setWinner(String name) {
		this.hasWinner = true;
		this.winnerName = name;
		changeSupport.firePropertyChange("winnerName", "", this.winnerName);
	}
	
	public synchronized boolean hasWinner() {
		return this.hasWinner;
	}
	
	public synchronized String getWinnerName() {
		return this.winnerName;
	}

	public synchronized void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
       changeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		   changeSupport.removePropertyChangeListener(listener);
	}
	
}
