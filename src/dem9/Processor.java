package dem9;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			synchronized(lock){
				//如果线程被非consumer线程唤醒，比如其他线程对lock对象调用了notifyAll
				//如果不循环这个时候就会执行下面的add. 即使判断是false.
				while(list.size() == LIMIT){
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}
			
		}
	}
	
	public void consume() throws InterruptedException{
		Random random = new Random();
		while(true){
			synchronized(lock){
			
				while(list.size()==0){
					lock.wait();
				}
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();// First in first out.
				System.out.println(";value is: " + value);
				lock.notify();
			}
			//让数据先加满，一次充分利用consumer的线程池
			Thread.sleep(random.nextInt(1000));
		}
		
	}
}
