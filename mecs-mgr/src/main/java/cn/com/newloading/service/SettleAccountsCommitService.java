package cn.com.newloading.service;

import javax.servlet.http.HttpServletRequest;

public interface SettleAccountsCommitService {
	public String commit(String mrId,HttpServletRequest req);

}
