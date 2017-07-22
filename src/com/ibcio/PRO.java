package com.ibcio;

import java.util.Comparator;
import java.util.PriorityQueue;

import javax.management.relation.Relation;

class People{
	String name;
	int num;
	public People(String name,int num){
		this.name=name;
		this.num=num;
		
	}
}
public class PRO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comparator<People> comparator=new Comparator<People>() {
			
			@Override
			public int compare(People o1, People o2) {
				// TODO Auto-generated method stub
				if(o1.num>o2.num)
					return -1;
				else if(o1.num==o2.num)
					return 0;
				return 1;
			}
		};
		PriorityQueue<People> queue=new PriorityQueue<People>(100, comparator);
		for(int i=1;i<=3;++i){
			queue.add(new People("a"+i, i));
		}
		while(true){
			People people=queue.poll();
			System.out.print(people.name+" ");
		}
		
		
	}

}
