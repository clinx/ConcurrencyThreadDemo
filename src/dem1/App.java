package dem1;

class Runner extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("Hi Thread loop sequence" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
}

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//jvm会检查每一个线程是否执行完毕。然后才会停止jvm进程。
		Thread t1 = new Runner();
		t1.start();
		System.out.println("Hi Primary thread");
	}

}
