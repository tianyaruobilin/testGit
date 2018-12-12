package utils;

/**
 * @author:������
 * 	@Test  ����ʹ��ʾ��
	public void test(){
		cityList=selectByProvince("�㶫ʡ");
		System.out.println(cityList.toString());
		System.out.println(provinceList.toString());
		emptyCityList();
	}
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJson {
	private List<String> cityList = null;
	private List<String> provinceList = null;

	/**
	 * ��ȡjson���飬��ȡʡ�������뵽���ݿ�province,city,area
	 * 
	 * @param jsonStr
	 * @throws Exception
	 */
	public void ReadJson(String jsonStr, String wantprovince) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = (JSONArray) jsonObject.get("provinces");
		provinceList = new ArrayList<String>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			String province = jsonObject2.get("provinceName").toString();
			provinceList.add(province);
			if (province.equals(wantprovince)) {
				JSONArray jsonArray2 = (JSONArray) jsonObject2.get("citys");
				cityList = new ArrayList<String>();
				for (int j = 0; j < jsonArray2.size(); j++) {
					JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
					String city = jsonObject3.get("citysName").toString();
					cityList.add(city);
				}
			}

		}
	}

	/**
	 * ��ȡȫ��ʡ����json�ļ�
	 * 
	 * @return String
	 */
	public  String ReadTxt() {
		String str = "";
		String line = "";
		String pathname = this.getClass().getResource("ProvinceCityJson.txt").getPath();
		// ͨ��������·�����ַ���ת��Ϊ����·����������һ���� File ʵ����
		File file = new File(pathname);
		try {
			// ����һ��ʹ��Ĭ���ַ����� InputStreamReader��
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			// ����һ��ʹ��Ĭ�ϴ�С���뻺�����Ļ����ַ���������
			BufferedReader br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				str += line;// һ�ζ���һ������
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * ��ȡ���е�ʡ
	 * @param jsonStr
	 */
	public void findAllProvince(String jsonStr) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = (JSONArray) jsonObject.get("provinces");
		provinceList = new ArrayList<String>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			String province = jsonObject2.get("provinceName").toString();
			provinceList.add(province);
		}
	}
	public List<String> findprovinces(){
		String jsonStr = ReadTxt();
		findAllProvince(jsonStr);
		return provinceList;
	}
	
	/**
	 * ����ʡ��ȡ��
	 * @param province
	 * @return
	 */
	public List<String> selectByProvince(String province) {
		String jsonStr = ReadTxt();
		ReadJson(jsonStr, province);
		return cityList;
	}

	/**
	 * ����˳����ÿգ���ֹ�´ε����ݳ��ֵ��ӣ������ݿ�Ĺرղ���һ��
	 */
	public void emptyCityList() {
		cityList = null;
	}
}
