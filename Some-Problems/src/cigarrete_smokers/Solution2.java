package cigarrete_smokers;

public class Solution2 {
	public static void main(String[] args) {
		BrokerShared bvars = new BrokerShared();
		ProviderShared pvars = new ProviderShared();
		Provider provider = new Provider(pvars);

		Runnable smokerWithMatch = () -> {
			while (true) {
				try {
					bvars.matchSignal.acquire();
					System.out.println("Fumante com fósforo fez o cigarro.");
					pvars.agent.release();
					System.out.println("Fumante com fósforo conseguiu fumar.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable smokerWithTobacco = () -> {
			while (true) {
				try {
					bvars.tobaccoSignal.acquire();
					System.out.println("Fumante com tabaco fez o cigarro.");
					pvars.agent.release();
					System.out.println("Fumante com tabaco conseguiu fumar.");
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable smokerWithPaper = () -> {
			while (true) {
				try {
					bvars.paperSignal.acquire();
					System.out.println("Fumante com papel fez o cigarro.");
					pvars.agent.release();
					System.out.println("Fumante com papel conseguiu fumar.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable brokerWithMatch = () -> {
			while (true) {
				try {
					pvars.match.acquire();
					bvars.mutex.acquire();
					if (bvars.isPaper) {
						bvars.isPaper = false;
						bvars.tobaccoSignal.release();
					} else if (bvars.isTobacco) {
						bvars.isTobacco = false;
						bvars.paperSignal.release();
					} else {
						bvars.isMatch= true;
					}
					bvars.mutex.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable brokerWithTobacco = () -> {
			while (true) {
				try {
					pvars.tobacco.acquire();
					bvars.mutex.acquire();
					if (bvars.isPaper) {
						bvars.isPaper = false;
						bvars.matchSignal.release();
					} else if (bvars.isMatch) {
						bvars.isMatch = false;
						bvars.paperSignal.release();
					} else {
						bvars.isTobacco = true;
					}
					bvars.mutex.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable brokerWithPaper = () -> {
			while (true) {
				try {
					pvars.paper.acquire();
					bvars.mutex.acquire();
					if (bvars.isTobacco) {
						bvars.isTobacco = false;
						bvars.matchSignal.release();
					} else if (bvars.isMatch) {
						bvars.isMatch = false;
						bvars.paperSignal.release();
					} else {
						bvars.isPaper = true;
					}
					bvars.mutex.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread(brokerWithTobacco).start();
		new Thread(brokerWithMatch).start();
		new Thread(brokerWithPaper).start();
		
		new Thread(smokerWithTobacco).start();
		new Thread(smokerWithMatch).start();
		new Thread(smokerWithPaper).start();
		
		new Thread(provider).start();
	}
}
