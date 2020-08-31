package com.huan;

import com.huan.sort.*;
import com.huan.tools.Asserts;
import com.huan.tools.Integers;

import java.text.DecimalFormat;
import java.util.Comparator;

public abstract class Sort<T> implements Comparable<Sort<T>>{

	protected T[] array;
	private int cmpCount;
	private int swapCount;
	private long time;
	private DecimalFormat fmt = new DecimalFormat("#.00");
	private Comparator<T> comparator;

	public Sort(){
		this(null);
	}
	public Sort(Comparator<T> comparator){
		this.comparator = comparator;
	}

	public void sort(T[] array) {
		if (array == null || array.length < 2) return;

		this.array = array;

		long begin = System.currentTimeMillis();
		sort();
		time = System.currentTimeMillis() - begin;
	}

	public abstract void sort();

	@Override
	public int compareTo(Sort o) {
		int result = (int) (time - o.time);
		if(result != 0) return result;
		result = cmpCount - o.cmpCount;
		if(result != 0) return result;
		return swapCount - o.swapCount;
	}

	protected void swap(int i,int j){
		++swapCount;
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

//	protected int cmp(int i,int j){
//		++cmpCount;
//		return array[i].compareTo(array[j]);
//	}


	protected int compare(T o1,T o2){
		++cmpCount;
		return comparator != null ? comparator.compare(o1,o2) : ((Comparable<T>)o1).compareTo(o2);
	}

	@Override
	public String toString() {
		String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
		String compareCountStr = "比较：" + numberString(cmpCount);
		String swapCountStr = "交换：" + numberString(swapCount);
		String stableStr = "稳定性：" + isStable();
		return "【" + getClass().getSimpleName() + "】\n"
				+ stableStr + " \t"
				+ timeStr + " \t"
				+ compareCountStr + "\t "
				+ swapCountStr + "\n"
				+ "------------------------------------------------------------------";

	}

	private String numberString(int number) {
		if (number < 10000) return "" + number;

		if (number < 100000000) return fmt.format(number / 10000.0) + "万";
		return fmt.format(number / 100000000.0) + "亿";
	}

	private boolean isStable() {
		if(this instanceof ShellSort) return false;
		if(this instanceof CountingSort) return true;
		if(this instanceof CountingSort1) return true;
		if(this instanceof RadixSort) return true;
		Student[] students = new Student[20];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(i * 10, 10);
		}
		sort((T[]) students);
		for (int i = 1; i < students.length; i++) {
			int score = students[i].score;
			int prevScore = students[i - 1].score;
			if (score != prevScore + 10) return false;
		}
		return true;
	}
}
