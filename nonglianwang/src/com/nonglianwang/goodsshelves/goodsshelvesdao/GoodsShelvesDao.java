package com.nonglianwang.goodsshelves.goodsshelvesdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;

import utils.C3P0Utils;

public class GoodsShelvesDao {
	private QueryRunner queryRunner=new QueryRunner();
	/**
	 * ��ȡ��ӵ���Ʒ����
	 * @param uno
	 * @return
	 */
	public List<GoodsBean> selectGoodsShelves(String uno) {
		Connection connection;
		//��������
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="select goodsno,uno,goodsname,goodscategory,goodsimg as 'goodsimgpath',goodsprice,goodsdescript,goodsweight,preferential from tb_goods where uno=?";
		Object[] params={uno};
		List<GoodsBean> goodsBeans;
		//��ѯ
		try {
			goodsBeans=queryRunner.query(connection,sqlString, new BeanListHandler<GoodsBean>(GoodsBean.class), params);
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		//�ͷ�����
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return goodsBeans;
	}
	/**
	 * ɾ����Ʒ����tb_goods���У�����goodsno
	 * @param goodsno
	 */
	public void deleteGoods(String goodsno) {
		String[] snolist=getStrArray(goodsno);
		StringBuilder sb=new StringBuilder("delete tb_goods where goodsno in");
		sb.append(" (");
		for (int i = 0; i < snolist.length; i++) {
			if(i==0){
				sb.append("?");
			}
			else {
				sb.append(",?");
			}
		}
		sb.append(")");
		//���sql���
		String sql=sb.toString();
		//��ȡ����
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//����
		Object params[]=snolist.clone();
		try {
			queryRunner.update(connection, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ָ��ַ���Ϊ����ķ���
	 */
	public String[] getStrArray(String goodsno){
		return goodsno.split("-");
	}
}
