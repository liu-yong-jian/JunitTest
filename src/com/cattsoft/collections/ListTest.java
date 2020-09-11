package com.cattsoft.collections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.cattsoft.dbutil.DBUtil;
import com.cattsoft.dbutil.SysConstant;
import org.junit.Test;


/**
 * 1. java????List???????"???????"??ArrayList(??????????????)??LinkedList(?????????????)??????????????????
 *	2. Queue????????��?Deque????????????(??????????????��?????????????)
 *	3. ????????????????????????????��?????????????????????????????????��????????????????????????????????????????????��?
 *	4. ??????????????????????????��????????????��???????
 *	5. ???��??????????????????????????????????????????????????????
 */
public class ListTest {

	/**
	 * 1) ensureCapacity(int minCapacity): ??ArrayList?????Object[]???�A??????minCapacity
	 * 2) trimToSize(): ????ArrayList?????Object[]???�A????????????????????????????????????ArrayList????????????????
	 */
	@Test
	public void testArrayList(){
		List list = new ArrayList();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		
		//????????
		String values = "9" + ",";
		for (int i = 0; i < list.size(); i++) {
			values = values + list.get(i);
			if (i < list.size() - 1) {
				values = values + ",";
			}
		}
		System.out.println(values);
		
		
		
		
		System.out.println(list);
		
		//???????��?��???????
		list.add(1, "5");
		System.out.println(list);//[1, 5, 2, 3, 4]
		
		for(Object str : list){
			System.out.println(str);
			//list.add("8");//ConcurrentModificationException
		}
		
		list.remove(1);
		System.out.println(list);//[1, 2, 3, 4]
		
		//????��?
		System.out.println(list.indexOf("2"));//1
		
		//?�I?��?
		list.set(3, "5");
		System.out.println(list);//[1, 2, 3, 5]
		
		//??????,?????? 2
		System.out.println(list.subList(0, 2));//[1, 2]
		
	}
	
	
	/**
	 * Stack????????????
	 */
	@Test
	public void testStack(){
		Stack st = new Stack();
		st.push("1");
		st.push("2");
		st.push("3");
		System.out.println(st);
		
		st.push("4");
		
		//???????????
		System.out.println(st.peek());
		System.out.println(st);
		
		//??????????		
		System.out.println(st.pop());
		System.out.println(st);

	}
	
	/**
	 * LinkedList??????????????��?????��??????????????????
	 * 
	 */
	@Test
	public void testLinkedList(){
		LinkedList ll = new LinkedList();
		
		ll.offer("1");//?????��??
		ll.push("2");//?????
		ll.offerFirst("3");//?????
		ll.offer("4");
		System.out.println(ll);//[3, 2, 1, 4]
	
		
		for(int i=0; i<ll.size(); i++){
			System.out.println(ll.get(i));
		}
		
		//??????????????
		System.out.println(ll.peekFirst());
		System.out.println(ll.peek());
		System.out.println(ll);//[3, 2, 1]
		
		//?????????????
		System.out.println(ll.poll());
		System.out.println(ll.pollLast());
		System.out.println(ll);//[2]
	}
	
	/**
	 * PriorityQueue?????????null??????????????????????????
	 */
	@Test
	public void testPriorityQueue(){
	
		PriorityQueue pq = new PriorityQueue();
		pq.add(-1);
		pq.add(2);
		pq.add(3);
		pq.add(7);
		pq.add(1);
		
		System.out.println(pq);//[-1, 2, 3, 7, 2]
		
		PriorityQueue pq1 = new PriorityQueue();
		pq1.offer(6);
		pq1.offer(-1);
		pq1.offer(9);
		pq1.offer(7);
	
		System.out.println(pq1);//[-1, 6, 9, 7]
	}
	
	@Test
	public void testFor(){
		for(int i = 0; i < 3; i++){
			System.out.println(i);
			
			for(int j = i + 1; j < 3; j++){
				System.out.println("j" + j);
				if(j==2){
					i = 1;
					break;
				}
			}
		}
	}

	@Test
	public void testList() throws SQLException {
		String sql = "select DATE_FORMAT(DATE,'%Y-%m-%d') str from t_calendar_info where STATUS IN(0,3) order by DATE";
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		List<String> list = DBUtil.queryHashMapList(conn, sql,null);

		System.out.println(list.get(0));
		Integer firstNum = list.indexOf("2020-12-30");
		Integer secondNum = list.indexOf("2020-12-31");
		System.out.println("firstNum=" + firstNum + " secondNum=" + secondNum);

	}



	
}
