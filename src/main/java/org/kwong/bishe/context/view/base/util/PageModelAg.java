package org.kwong.bishe.context.view.base.util;

import java.awt.List;
import java.util.ArrayList;

/**
 * @author
 *
 */
public class PageModelAg {
	public int currentPage = 1; // 当前页

	public int totalPages = 0; // 总页数

    public int pageSize=20;// 每页5条数据

    public int totalRows = 0; // 总数据数

    public int pageStartRow = 0;// 当前页的起始数

    public int pageEndRow = 0; // 当前页显示数据的终止数

    public boolean hasNextPage = false; // 是否有下一页

    public boolean hasPreviousPage = false; // 是否有前一页
    
    public int nextPage;
    public int previousPage;
    
    public ArrayList<Integer> pageList;
	
	/**
	 * 初始化,pagesieze缺省为20
	 */
	public void init(int page,int totalRows){
		this.currentPage=page;
		this.totalRows=totalRows;
		this.pageInit();
//		this.parse();
	}
	
	/**
	 * 
	 */
	public void init(int page,int totalRows,int pageSize){
		this.currentPage=page;
		this.totalRows=totalRows;
		this.pageSize=pageSize;
		this.pageInit();
//		this.parse();
	}
	
	
    /** 
     * 初始化list，并告之该list每页的记录数
     * @param
     * @param
     */
    public void pageInit() {
    	
        if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize == 0 ? 1 : totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }
        if(currentPage>totalPages){
        	currentPage=1;
        }
        if(currentPage<=1){
    		hasPreviousPage = false;
    	}else{
    		hasPreviousPage = true;
    		previousPage=currentPage-1;
    	}
        
        if (currentPage >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
            nextPage=currentPage+1;
        }
        this.pageStartRow = (currentPage-1)*pageSize;
        this.pageEndRow = currentPage*pageSize;
        
        pageList=new ArrayList<Integer>();
        //处理四个页
        if(totalPages>1){//所有分页大于1时才处理
			if(totalPages>4){
				//生成当前页面的4个数，用于显示
				int page1=1,page2=2,page3=3,page4=4;
				if(currentPage>1){//不处理第一页

					//处理倒数第二页面
					if(currentPage==totalPages-2){
						
						page1=currentPage-1;
						page2=currentPage;
						page3=currentPage+1;
						page4=currentPage+2;
					}else if(currentPage==totalPages-1){
						//处理倒数第一页面
						page1=currentPage-2;
						page2=currentPage-1;
						page3=currentPage;
						page4=currentPage+1;
					}else if(currentPage==totalPages){
						//处理最后一页
						page1=currentPage-3;
						page2=currentPage-2;
						page3=currentPage-1;
						page4=currentPage;
					}else{
						page1=currentPage-1;
						page2=currentPage;
						page3=currentPage+1;
						page4=currentPage+2;
					}
				}
				pageList.add(page1);
				pageList.add(page2);
				pageList.add(page3);
				pageList.add(page4);
			}else{
				for(int i=1;i<=totalPages;i++){
					pageList.add(i);
				}
			}
		}
    }

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getPage() {
		return currentPage;
	}

	public void setPage(int page) {
		this.currentPage = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	@Override
	public String toString() {
		return "PageModel [page=" + currentPage + ", totalPages=" + totalPages + ", pageSize=" + pageSize + ", totalRows=" + totalRows + ", pageStartRow=" + pageStartRow + ", pageEndRow=" + pageEndRow + ", hasNextPage=" + hasNextPage + ", hasPreviousPage=" + hasPreviousPage + ", nextPage=" + nextPage + ", previousPage=" + previousPage + ", pageList=" + pageList + "]";
	}
    
    
}
