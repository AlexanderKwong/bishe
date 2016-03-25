package org.kwong.bishe.context.view.base.util;

/**  
 * List分页
 * 实现：利用List的获取子List方法，实现对List的分页
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kwong.bishe.common.util.Util;


public class  PageModel<T> implements Serializable{
    
	private static final long serialVersionUID = 1L;

	public int page = 1; // 当前页

	public int totalPages = 0; // 总页数

    public int pageSize= Util.pageSize;// 每页5条数据

    public int totalRows = 0; // 总数据数

    public int pageStartRow = 0;// 当前页的起始数

    public int pageEndRow = 0; // 当前页显示数据的终止数

    public boolean hasNextPage = false; // 是否有下一页

    public boolean hasPreviousPage = false; // 是否有前一页
    
    public String showdivId;
    
    public String pageInputName;


	public List<T> list=new ArrayList<T>();
	
	/**
	 * 翻页代码
	 */
	private StringBuffer roll;

	
	/**
	 * url
	 */
	private String url;
	

	/**
	 * @param page 当前页
	 * @param totalRows 记录总数
	 * @param pageSize  每页显示记录数
	 * @param funcName  页面刷新的divId
	 */
	public void init(int page,int totalRows,int pageSize,String showdivId,String pageInputName,String url){
		this.page=page;
		this.totalRows=totalRows;
		this.pageSize=pageSize;
		this.showdivId=showdivId;
		this.pageInputName=pageInputName;
		this.url=url;
		this.pageInit();
		this.parse();
	}
	
	/**
	 * 初始化,pagesieze缺省为20
	 */
	public void init(int page,int totalRows){
		this.page=page;
		this.totalRows=totalRows;
		this.pageInit();
		this.parse();
	}
	
	/**
	 * 
	 */
	public void init(int page,int totalRows,int pageSize){
		this.page=page;
		this.totalRows=totalRows;
		this.pageSize=pageSize;
		this.pageInit();
		this.parse();
	}
	
	
    /** 
     * 初始化list，并告之该list每页的记录数
     * @param list
     * @param pageSize
     */
    public void pageInit() {
        if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize == 0 ? 1 : totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }
        if(page>totalPages){
        	page=1;
        }
		hasPreviousPage = page > 1;

		hasNextPage = page < totalPages;
        this.pageStartRow = (page-1)*pageSize;
        this.pageEndRow = page*pageSize;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getList(){
    	return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
	
    /**
     * 默认的div ID="divSearchList"
     * @param funcName
     */
    public void parse() {
		boolean pre = page > 1;
		boolean end = page < totalPages;
		roll = new StringBuffer("");
		if (pre) {
			roll.append(this.getHrefString(1));
			roll.append("首页");
			roll.append("</a>");

			roll.append("&nbsp;|&nbsp;");
			roll.append(this.getHrefString(page-1));
			roll.append("上一页");
			roll.append("</a>");
		}
		if (pre && end) {
			roll.append("&nbsp;|&nbsp;");
		}
		if (end) {
			roll.append(this.getHrefString(page+1));
			roll.append("下一页");
			roll.append("</a>");

			roll.append("&nbsp;|&nbsp;");
			roll.append(this.getHrefString(totalPages));
			roll.append("末页");
			roll.append("</a>");
		}

		roll.append("&nbsp;&nbsp;<em>当前页"+page+"/"+totalPages+"</em><input type='text' size='3' width='10px;' name='"+this.pageInputName+"' id='"+this.pageInputName+"' value='"+page+"'/>");
		roll.append("<em>共" + this.totalRows + "条记录&nbsp;</em><input type='hidden' id='totalpage' value='"+this.totalPages+"'>");
	}
    
    
	public StringBuffer getRoll() {
		return roll;
	}

	public void setRoll(StringBuffer roll) {
		this.roll = roll;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getShowdivId() {
		return showdivId;
	}

	public void setShowdivId(String showdivId) {
		this.showdivId = showdivId;
	}
	
	public String getPageInputName() {
		return pageInputName;
	}

	public void setPageInputName(String pageInputName) {
		this.pageInputName = pageInputName;
	}
	
	/**
	 * 获取url
	 * @param urlparas
	 * @param divId
	 * @param pageName
	 * @return
	 */
	public String getHrefString(int page){
		return "<a href=\"javascript:base.pageLoad({'url':'"+url+"','page':'"+page+"','pageName':'"+this.pageInputName+"','showdivId':'"+this.showdivId+"'});\">";
	}
}
