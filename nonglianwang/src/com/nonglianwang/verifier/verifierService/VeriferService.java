package com.nonglianwang.verifier.verifierService;

import java.util.List;

import com.nonglianwang.verifier.VerifierDao.VeriferDao;


public class VeriferService {
	private VeriferDao veriferDao=new VeriferDao();
	
	
	/**
	 * 找出所有需要验证的商家
	 * @param count 
	 * @param page 
	 * @return
	 */
	public List<?> findAllNeedVeriferBusinessman(int page, int count) {
		// TODO Auto-generated method stub
		List<?> list=veriferDao.findAllNeedVeriferBusinessman(page,count);
		return list;
	}
	
	
	/**
	 * 统计出所有需要审核的商家的数量
	 * @return
	 */
	public int CountNeedVeriferBusinessman() {
		return veriferDao.CountNeedVeriferBusinessman();
	}


	/**
	 * 通过商家审核
	 * @param uno
	 * @return
	 */
	public boolean pass(String uno) {
		// TODO Auto-generated method stub
		if (veriferDao.pass(uno)) {
			veriferDao.addrelationShip(uno);
			return true;
		}
		return false;
	}


	/**
	 * 删除伪商家
	 * @param uno
	 * @return
	 */
	public boolean delete(String uno) {
		System.out.println(veriferDao.delete(uno));
		return veriferDao.delete(uno);
	}


}
