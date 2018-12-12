package com.nonglianwang.businessman.businessmansupplementDao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.businessman.businessmansupplementInfoBean.BusinessManSupplementInfo;

import utils.C3P0Utils;

public class BusinessManSupplementDao {
	private QueryRunner queryRunner = new QueryRunner();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String load(String uno) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString = "select uno from tb_businessman where uno=?";
		Object param[] = { uno };
		String uunoString = null;
		try {
			uunoString = (String) queryRunner.query(connection, sqlString, new ScalarHandler(), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return uunoString;

	}

	/**
	 * 添加商家补充信息到tb_businessman
	 * 
	 * @param businessman
	 * @throws BusinessManSupplementException 
	 */
	public void addBusinessMan(BusinessManSupplementInfo businessman) throws BusinessManSupplementException {
		String uunoString=load(businessman.getUno());
		if(uunoString!=null){
			throw new BusinessManSupplementException();
		}
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString = "insert into tb_businessman values(?,?,?,?,?,?,?,GETDATE())";
		Object param[] = { businessman.getUno(), businessman.getBusinessManName(), businessman.getBusinessManIdCard(),
				businessman.getBusinessManTel(), businessman.getBusinessManAddr(),
				businessman.getBusinessManAddrSupplement(), businessman.getIsBusinessMan() };
		try {
			queryRunner.update(connection, sqlString, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
