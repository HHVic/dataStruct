package com.huan.性能比较;

public class Test {

    public static void main(String[] args) {
        //Times.test("test2",() -> test2());
        Times.test("test1",() -> test1());


    }

    private static void test1(){

        System.out.println(Integer.reverse(9));
    }

    private static void test2(){
        for(int i = 0;i < Integer.MAX_VALUE;++i){
            long res = ((20 << 26));
        }
    }
}
