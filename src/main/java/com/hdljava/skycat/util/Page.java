package com.hdljava.skycat.util;

public class Page{
    private int start; //从第几条记录开始计算
    private int count;   //每页几条数据
    private int total; //一共有几条记录
    private String param;

    private static final int defaultCount = 5; //每页5条记录

    public Page(){
        count = defaultCount;
    }

    public Page(int start ,int count){
       this();
        this.start=start;
        this.count=count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public  int getTotalPage(){
        int totalPage;
        if(total%count==0)
            totalPage = total/count;
        else
            totalPage = total/count+1;

        if(totalPage==0)
            totalPage=1;
        return totalPage;
    }
  public boolean isHasPreviouse(){
        if (start==0)
            return false;
        else
            return true;
  }
  public boolean isHasNext(){
        if(start ==getLast()){
            return false;
        }
        else{
            return true;
        }
  }
    public int getLast(){
        int last;
        if (total%count==0)
            last=total - total/count;
        else
            last=total - total%count;

        last = last<0? 0:last;
        return  last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param +
                ", isHasNext=" + isHasNext() +
                ", isHasPreviouse()=" + isHasPreviouse() +
                ", getLast()=" + getLast() + '\'' +
                '}';
    }
}
