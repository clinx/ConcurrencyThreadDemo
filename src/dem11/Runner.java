package dem11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			// acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				if(gotFirstLock){
					firstLock.unlock();
				}
				if(gotSecondLock){
					secondLock.unlock();
				}
				
			}
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException{
		Random random = new Random();
		for(int i=0; i<1000; i++){
			acquireLocks(lock1,lock2);
			try{
				Account.transfer(acc1, acc2, random.nextInt(1000));
			}finally{
				lock2.unlock();
				lock1.unlock();
			}
			
		}
	}
	
	public void secondThread() throws InterruptedException{
		Random random = new Random();
		for(int i=0; i<1000; i++){
			acquireLocks(lock2,lock1);
			try{
				Account.transfer(acc2, acc1, random.nextInt(1000));
			}finally{
				lock2.unlock();
				lock1.unlock();
			}
			
		}
	}
	
	public void finished(){
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
