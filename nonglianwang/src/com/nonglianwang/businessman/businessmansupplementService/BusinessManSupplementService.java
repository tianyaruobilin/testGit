package com.nonglianwang.businessman.businessmansupplementService;

import com.nonglianwang.businessman.businessmansupplementDao.BusinessManSupplementDao;
import com.nonglianwang.businessman.businessmansupplementDao.BusinessManSupplementException;
import com.nonglianwang.businessman.businessmansupplementInfoBean.BusinessManSupplementInfo;

public class BusinessManSupplementService {
	private BusinessManSupplementDao businessManSupplementDao=new BusinessManSupplementDao();
	
	
	public void addBusinessMan(BusinessManSupplementInfo businessman) throws BusinessManSupplementException {
		businessManSupplementDao.addBusinessMan(businessman);
	}

	
	public boolean applyOrNot(String nameString) {
		String uno=businessManSupplementDao.load(nameString);
		if(uno!=null){
			return false;
		}
		return true;
	}
}
