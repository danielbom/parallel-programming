package cigarrete_smokers;

public class Solution1 {
	
	public static void main(String[] args) {
		ProviderShared vars = new ProviderShared();
		Provider provider = new Provider(vars);


		Runnable smokerWithMatch = () -> {
			while (true) {
				try {
					vars.tobacco.acquire();
					vars.paper.acquire();
					vars.agent.release();
					System.out.println("Fumante com fósforo conseguiu fumar.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable smokerWithTobacco = () -> {
			while (true) {
				try {
					vars.paper.acquire();
					vars.match.acquire();
					vars.agent.release();
					System.out.println("Fumante com tabaco conseguiu fumar.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable smokerWithPaper = () -> {
			while (true) {
				try {
					vars.tobacco.acquire();
					vars.match.acquire();
					vars.agent.release();
					System.out.println("Fumante com papel conseguiu fumar.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread(smokerWithTobacco).start();
		new Thread(smokerWithMatch).start();
		new Thread(smokerWithPaper).start();
		
		new Thread(provider).start();
	}
}
