package cigarrete_smokers;

public class Provider implements Runnable {
	ProviderShared vars;
	
	public Provider(ProviderShared vars) {
		this.vars = vars;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				double rand = Math.random();

				vars.agent.acquire();
				System.out.println(rand);
				if (rand < (1.0 / 3.0)) {		 // Possibility 1
					vars.tobacco.release();
					vars.paper.release();
				} else if (rand < (2.0 / 3.0)) { // Possibility 2
					vars.paper.release();
					vars.match.release();
				} else {						 // Possibility 3
					vars.tobacco.release();
					vars.match.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
