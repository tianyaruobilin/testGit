package com.nonglianwang.verifier.verifierService;

import java.util.List;

import com.nonglianwang.verifier.VerifierDao.VeriferDao;


public class VeriferService {
	private VeriferDao veriferDao=new VeriferDao();
	
	
	/**
	 * �ҳ�������Ҫ��֤���̼�
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
	 * ͳ�Ƴ�������Ҫ��˵��̼ҵ�����
	 * @return
	 */
	public int CountNeedVeriferBusinessman() {
		return veriferDao.CountNeedVeriferBusinessman();
	}


	/**
	 * ͨ���̼����
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
	 * ɾ��α�̼�
	 * @param uno
	 * @return
	 */
	public boolean delete(String uno) {
		System.out.println(veriferDao.delete(uno));
		return veriferDao.delete(uno);
	}


}
