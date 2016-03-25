package org.kwong.bishe.context.view.base.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 *  MySessionContext是实现session的读取和删除增加  单例模式
 * @author
 *
 */
public class MySessionContext {
	private static MySessionContext instance;
	private HashMap mymap;

	private MySessionContext() {
		mymap = new HashMap();
	}

	public static MySessionContext getInstance() {
		if (instance == null) {
			instance = new MySessionContext();
		}
		return instance;
	}

	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}

	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) mymap.get(session_id);
	}

}
