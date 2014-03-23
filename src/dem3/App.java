package dem3;

public class App {

	private int count = 0;
	
	public void increment(){
//	public synchronized  void increment(){
		count++;
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}

	public void doWork(){
		Thread t1 = new Thread(){
			public void run(){
				for(int i=0;i<1000;i++){
					App.this.increment();
				}
			}
		};
		
		Thread t2 = new Thread(){
			public void run(){
				for(int i=0;i<1000;i++){
					App.this.increment();
				}
			}
		};
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println(count);
	}
}
