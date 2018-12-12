package com.nonglianwang.verifier.VerifierDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.businessman.businessmansupplementInfoBean.BusinessManSupplementInfo;

import utils.C3P0Utils;

public class VeriferDao {
	private QueryRunner queryRunner = new QueryRunner();
	
	
	/**
	 * 找出所有需要验证的商家
	 * 
	 * @param count
	 * @param page
	 * @return
	 */
	public List<?> findAllNeedVeriferBusinessman(int page, int count) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString = "SELECT uno, BusinessManName, BusinessManIdCard,BusinessManTel,BusinessManAddr,BusinessManAddrSupplement FROM tb_businessman where IsBusinessMan='false' ORDER BY applytime OFFSET "
				+ (page - 1) * count + " ROW  FETCH NEXT " + count + " ROW ONLY";
		List<?> list = null;
		try {
			list = queryRunner.query(connection, sqlString,
					new BeanListHandler<Object>(BusinessManSupplementInfo.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * 查询需要验证的商家的数量
	 * 
	 * @return
	 */
	public int CountNeedVeriferBusinessman() {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString = "select count(*) from tb_businessman where IsBusinessMan='false'";
		int count = 0;
		try {
			count = queryRunner.query(connection, sqlString, new ScalarHandler<Integer>());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;

	}
	/**
	 * 通过商家审核
	 * @param uno
	 * @return
	 */
	public boolean pass(String uno) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="UPDATE tb_businessman SET IsBusinessMan = 'true' WHERE uno = ?";
		Object param[]={uno};
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
		return true;
	}
	
	/**
	 * 删除审核不通过的伪商家,不将申请删除，只是将isbusinessman改为error
	 * @param uno
	 * @return
	 */
	public boolean delete(String uno) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="update tb_businessman set IsBusinessMan ='error' where uno=?";
		Object param[]={uno};
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
		return true;
	}

	public void addrelationShip(String uno) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="insert into tb_user_role values(?,'3')";
		Object param[]={uno};
		try {
			queryRunner.update(connection, sqlString, param);
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
