package dem7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	//最多10. 服务器上的message.
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) {
		Thread t1 =new Thread(){
			public void run(){
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		};

		Thread t2 =new Thread(){
			public void run(){
					try {
						consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		};
		
		t1.start();
		t2.start();
	}
	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			int input = random.nextInt(100);
			System.out.println("Put vlaue" + input);
			queue.put(input);
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			if(random.nextInt(10) == 0){
				Integer value = queue.take();
				System.out.println("Taken vlaue " + value+";Queue size is" + queue.size());
			}
		}
	}
}
