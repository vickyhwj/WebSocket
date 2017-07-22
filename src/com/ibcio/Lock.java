package com.ibcio;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

import javax.sound.midi.VoiceStatus;

import org.apache.commons.collections.map.StaticBucketMap;
class Key{
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
class Peo extends Thread{
	static Object lock=new Object();
	static int msg=1;
	int num=1;
	public Peo(int num){
		this.num=num;
		
	}
	
	public  void run(){
		try{
			synchronized (lock) {
				while(Peo.msg!=num)
					lock.wait();
				for(int i=1;i<=10;++i)
					System.out.print(num);
				System.out.println();
				Peo.msg=Peo.msg%3+1;
				lock.notifyAll();
			}

					
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
public class Lock {
	static ArrayList<String> list=new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Peo[] peos=new Peo[4];
		for(int i=1;i<=3;++i)
			peos[i]=new Peo(i);
	/*	for(int i=3;i>=1;--i){
			peos[i].start();
		}*/
		
		
		
		}
		
		
	
	}

}
