package org.kwong.bishe.common.util;

/**
 * @author
 *
 */
public class CacheUtil {

	private static long classId=-1;
	private static long tree1=-1;
	private static long tree2=-1;
	private static long tree3=-1;
	public static long getClassId() {
		return classId;
	}
	public static void setClassId(long classId) {
		CacheUtil.classId = classId;
	}
	public static long getTree1() {
		return tree1;
	}
	public static void setTree1(long tree1) {
		CacheUtil.tree1 = tree1;
	}
	public static long getTree2() {
		return tree2;
	}
	public static void setTree2(long tree2) {
		CacheUtil.tree2 = tree2;
	}
	public static long getTree3() {
		return tree3;
	}
	public static void setTree3(long tree3) {
		CacheUtil.tree3 = tree3;
	}
	
}
