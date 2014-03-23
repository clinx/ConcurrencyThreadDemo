package dem8;

import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException{
		synchronized(this){// instance lock
			System.out.println("Producer  thread running...");
			wait();//有时给timeout. 调用wait将失去锁，并且让当前线程block.
			System.out.println("Resumed.");
		}
	}
	
	public void consume() throws InterruptedException{
		
		Thread.sleep(2000);//let produce method kickoff first.
		Scanner scanner = new Scanner(System.in);
		synchronized(this){// instance lock
			System.out.println("Waiting for return key.");
			scanner.nextLine();
		
			notify();//Notify can only be called synchronized code block.
			// notify will  weak up first same lock thread. but don't release the lock.
			System.out.println("Return key pressed.");
			Thread.sleep(5000);
			}
	}
}
