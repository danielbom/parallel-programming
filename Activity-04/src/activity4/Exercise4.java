package activity4;

import java.util.Random;

import activity4.core.ObjectMonitor;

/**
 * Implemente uma solução para o problema do Barbeiro
 * Dorminhoco usando monitores.
 * 
 * @author daniel
 */
public class Exercise4 {
	public static void main(String[] args) {
		QueueMonitorable<Integer> chairs = new QueueMonitorable<Integer>(6);
		ObjectMonitor<Integer> barberShop = new ObjectMonitor<Integer>(chairs);
		
		Runnable clients = () -> {
			Random random = new Random();
			while (true) {
				Integer clientsInterval = random.nextInt(500) + 500;
				Integer time = random.nextInt(500) + 1000;
				barberShop.produce(time);
				try {
					Thread.sleep(clientsInterval);
				} catch(InterruptedException e) {
					// ...
				}
			}
		};
		
		Runnable barber = () -> {
			while (true) {
				Integer workTime = barberShop.consume();
				System.out.println("Atendendo um cliente...");
				try {
					Thread.sleep(workTime);
				} catch (InterruptedException e) {
					// ...
				}
				System.out.println("Cliente atendido: " + workTime);
			}
		};
		
		new Thread(clients).start();
		new Thread(barber).start();
		
	}
}
