/*
 * @(#)Page.java 1.0 Dec 29, 2010
 *
 * Copyright 2010 *** SocialMedia, Inc. All rights reserved.
 * ***SocialMedia PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package xx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Page.
 * 
 * @author   Huidi Yang
 * @version  1.0, Dec 29, 2010
 * @see      Page
 * @since    JDK1.6
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	private int  total;  //必须设定
    private int  pageSize;  //必须设定
    private int  totalPage;  
    private int  pageNo; //必须设定
    private int  prePage;  
    private int  nextPage;  
    private List list; //必须设定
    //页面的开始索引和结束索引
    private int  start;
    private int  end;
    private List<Integer>range;//页面索引区间
	public List<Integer> getRange() {
		List<Integer>result=new ArrayList<Integer>();
		for (int i = start; i <=end; i++) {
			result.add(i);
		}
		return result;
	}
	/**
	 * 
	 */
	public Page() {
		pageNo=1;
		pageSize=10;
	}
    public int getStart() {
		return start;
	}
    //设置�?�?�?�?�索�?�?�?�?参数start 即当前页
	public void setStart(int start) {
		if(getTotalPage() <= 10){
			this.start = 1;
		}
		else {
			this.start = start - 3;
			if(this.start < 1){
				this.start = 1;
			}
		}
	}
	public int getEnd() {
		return end;
	}
	//设置结束索引 参数end 即当前页
	public void setEnd(int end) {
		if(getTotalPage() <= 7) {
			this.end = getTotalPage();
		}
		else {
			this.end = end + 3;
			if(this.end > getTotalPage()) {
				this.end = getTotalPage();
			}
		}
	}
	public int getTotal() {  
        return total;  
    }  
	/**
	 * 设定总记录数目，同时设定totalPage
	 * (pageSize必须先设�?�?�?�?
	 * 
	 */
    public void setTotal(int total) {  
    	if(pageSize!=0){ 
	    	if(total%pageSize == 0){  
	             totalPage = total/pageSize;  
	         }else{  
	             totalPage = total/pageSize + 1;  
	         }
    	}
    	this.total = total;  
    }  
    public int getPageSize() {  
        return pageSize;  
    }  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
    public int getTotalPage() {  
    	if(pageSize!=0){ 
	    	if(total%pageSize == 0){  
	             totalPage = total/pageSize;  
	         }else{  
	             totalPage = total/pageSize + 1;  
	         }
    	}
        return totalPage;  
    }  
    public void setTotalPage(int totalPage) {  
        this.totalPage = totalPage;  
    }  
    public int getPageNo() {  
        return pageNo;  
    }  
    /**
     * 设置当前页，并自动计算开始索引和结束索引
     * （totalPage必须先设定）
     */
    public void setPageNo(int pageNo) {  
        this.pageNo = pageNo; 
        if(getTotalPage() <= 7){
			this.start = 1;
		}
		else {
			this.start = this.pageNo - 3;
			if(this.start >getTotalPage()-6){
				this.start=getTotalPage()-6;
			}
			if(this.start < 1){
				this.start = 1;
			}
		}
        //设置结束索引
        if(getTotalPage() <= 10) {
			this.end = getTotalPage();
		}
		else {
			this.end = this.pageNo + 3;
			if(this.end <7){
				this.end=7;
			}
			if(this.end > getTotalPage()) {
				this.end = getTotalPage();
			}
		}
        //下一�?�?
        if(pageNo == totalPage){  
            this.nextPage = pageNo;  
        }else{  
            this.nextPage = pageNo + 1;  
        }  
        //上一�?�?
        if(pageNo == 1){  
            this.prePage = pageNo;  
        }else{  
            this.prePage = pageNo - 1;  
        }  
    }  
    public int getPrePage() {  
        return prePage;  
    }  
    public void setPrePage(int prePage) {  
        this.prePage = prePage;  
    }  
    public int getNextPage() {  
        return nextPage;  
    }  
    public void setNextPage(int nextPage) {  
        this.nextPage = nextPage;  
    }  
    public List getList() {  
        return list;  
    }  
    public void setList(List list) {  
        this.list = list;  
    }  
}
