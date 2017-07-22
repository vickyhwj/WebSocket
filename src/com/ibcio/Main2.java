package com.ibcio;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

class A implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println(123);
		}
	}
	
}
class B implements Runnable{
	public B(Object object){
		lockedObject=object;
	}
	Object lockedObject;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (lockedObject) {
			for(int i=1;i<=1000;++i){
				System.out.println(Thread.currentThread().getName());
			}
		}
		
			
	}
	
}

public class Main2 {
	
	static int[] a={1,2,3,4,5,6,7,8,6,5,4,2,1};
	static int count=0;
	static int getmax(int front,int rear){
		++count;
		if(front==rear)
			return a[front];
		int mid=(front+rear)/2;
		if(a[front]==a[mid]&&front!=mid) return getmax(front+1,mid-1 );
		if(a[mid]==a[rear]&&mid!=rear) return getmax(mid+1, rear-1);
		if(a[front]==a[rear]||a[front]<a[mid]&&a[mid]>a[rear]){
			return Math.max(getmax(front, mid), getmax(mid+1, rear));
		}
		else{
			if(a[mid]<a[rear]){
				return getmax(mid+1, rear);
			}
			return getmax(front, mid);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a=new int[10000000];
		int c=0;
		for(int i=0;i<a.length;++i)
		{	
			if(i<=2){
				a[i]=++c;
			}
			else{
				a[i]=--c;
			}
		}
		A a2=new A();
		Object object=new Object();
		B b1=new B(object);
		B b2=new B(object);
		Thread thread=new Thread(b1);
		Thread thread2=new Thread(b2);
		thread.start();
		thread2.start();
		System.out.println(getmax(0, a.length-1));
		System.out.print(count);
	}

}
