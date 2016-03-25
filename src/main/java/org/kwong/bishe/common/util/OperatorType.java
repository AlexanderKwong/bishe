package org.kwong.bishe.common.util;

/**
 * 系统操作类型，用于日志
 * @author
 *
 */
public enum OperatorType {
	add,
	mdf,
	del,
	query,
	export,
	imports;
	/**
	 * 重载toString();
	 */
	public String toString(){
		switch (this){ 
		case add:
			return "add";
		case mdf:
			return "mdf";
		case del:
			return "del";
		case imports:
			return "imports";
		case export:
			return "export";
		case query:
			return "query";
		default :
			return "query";
		}
	}
	
	/**
	 * 重载valueOf();
	 */
	public String valueOf(){
		switch (this){ 
		case add:
			return "add";
		case mdf:
			return "mdf";
		case del:
			return "del";
		case query:
			return "quey";
		case export:
			return "export";
		case imports:
			return "imports";
		default :
			return "quey";
		}
	}
}
