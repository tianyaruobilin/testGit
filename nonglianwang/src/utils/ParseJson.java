package utils;

/**
 * @author:李润民
 * 	@Test  这是使用示例
	public void test(){
		cityList=selectByProvince("广东省");
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
	 * 读取json数组，提取省市区插入到数据库province,city,area
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
	 * 读取全国省市区json文件
	 * 
	 * @return String
	 */
	public  String ReadTxt() {
		String str = "";
		String line = "";
		String pathname = this.getClass().getResource("ProvinceCityJson.txt").getPath();
		// 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。
		File file = new File(pathname);
		try {
			// 创建一个使用默认字符集的 InputStreamReader。
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			// 创建一个使用默认大小输入缓冲区的缓冲字符输入流。
			BufferedReader br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				str += line;// 一次读入一行数据
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取所有的省
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
	 * 根据省获取市
	 * @param province
	 * @return
	 */
	public List<String> selectByProvince(String province) {
		String jsonStr = ReadTxt();
		ReadJson(jsonStr, province);
		return cityList;
	}

	/**
	 * 将市顺序表置空，防止下次的数据出现叠加，和数据库的关闭操作一样
	 */
	public void emptyCityList() {
		cityList = null;
	}
}
