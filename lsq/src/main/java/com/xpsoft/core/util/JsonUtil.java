 package com.xpsoft.core.util;
 
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;
 
 public class JsonUtil
 {
   public static JSONSerializer getJSONSerializer(String...dateFields)
   {
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "class" });
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), dateFields);
     return serializer;
   }
   
   public static void main(String[] args) {
	   String str = "{obj:[{'needNum':'1','otherNeeds':'常务副总经理'},{'needNum':'2','otherNeeds':'常务副总经理22'}]}";
	   String str1 = "[{\"needNum\":\"1\",\"otherNeeds\":\"常务副总经理\"},{\"needNum\":\"2\",\"otherNeeds\":\"常务副总经理22\"}]";
	   String str3 = "[{needNum:'1',otherNeeds:'常务副总经理'},{needNum:'2',otherNeeds:'常务副总经理22'}]";
	   String str2 = "{\"needNum\":\"1\",\"otherNeeds\":\"常务副总经理\"}";
//	   String str4 = "[{'job':{'jobId':'272'},'needNum':21,'age':'321','sex':'女','jobDesc':'321','educationDic':{'dicId':'74'},'workYear':'231','otherNeeds':'32132'},{'job':{'jobId':'226'},'needNum':321,'age':'321','sex':'男女都可','jobDesc':'321','educationDic':{'dicId':'76'},'workYear':'321','otherNeeds':'321'}]";
	   String str4 = "[{'job':{'jobId':'272'},'needNum':21,'age':'321','sex':'女','needWorkDate':'2013-03-13','jobDesc':'321','educationDic':{'dicId':'74'},'workYear':'231','otherNeeds':'32132'},{'job':{'jobId':'226'},'needNum':321,'age':'321','sex':'男女都可','needWorkDate':'2013-03-15','jobDesc':'321','educationDic':{'dicId':'76'},'workYear':'321','otherNeeds':'321'}]";
	   JsonObject obj = new JsonParser().parse(str).getAsJsonObject();
	   JsonArray arr = obj.getAsJsonArray("obj");
	   Iterator<JsonElement> iter = arr.iterator();
//	   for (JsonElement jsonElement : arr) {
//		   JsonObject jsObj = jsonElement.getAsJsonObject();
//		   System.out.println(jsObj.get("jobId"));
//	   }
//	   List<StaffRecruitInfo> infoList = new JSONDeserializer<List<StaffRecruitInfo>>()
//	   .use(null, ArrayList.class)
//	   .use("values",StaffRecruitInfo.class)
//	   .deserialize( str1 ); 
//	   System.out.println(infoList);
	   Gson gson =new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).create();
//	   StaffRecruitInfo  staffRecruitInfo=  gson.fromJson(str2,StaffRecruitInfo.class);
//	   StaffRecruitInfo  staffRecruitInfo=  fromJson(str2,StaffRecruitInfo.class);
//	   System.out.println(staffRecruitInfo.getOtherNeeds());
//		List<StaffRecruitInfo> staffList = gson.fromJson(str4,
//				new TypeToken<List<StaffRecruitInfo>>() {
//				}.getType());
//		List<StaffRecruitInfo> staffList = fromJsonTypesWithDate(str4,
//				new TypeToken<List<StaffRecruitInfo>>() {
//				}.getType());
//		for(StaffRecruitInfo si:staffList){
//			System.out.println(si.getOtherNeeds());
//		}
//	   for(StaffRecruitInfo info:infoList){
//		   System.out.println(info.getOtherNeeds());
//	   }
//	   for (int i = 0; i < list.size(); i++) {
//           Map map = (Map)list.get(i);
//           Set set = map.keySet();
//           for (Iterator it = set.iterator();it.hasNext();) {
//               String key = (String)it.next();
//               System.out.println(key + ":" + map.get(key));
//           }
//       }
   }
   
   public static <T> T fromJson(String jsonStr,Class<T> classOfT){
	   Gson gson = new Gson();
	   return  gson.fromJson(jsonStr, classOfT);
   }
   
   public static <T> T fromJsonWithDate(String jsonStr,Class<T> classOfT,String ...pattern){
	   String format = 0==pattern.length?Constants.DATE_FORMAT_YMD:pattern[0];
	   Gson gson = new GsonBuilder().setDateFormat(format).create();
	   return  gson.fromJson(jsonStr, classOfT);
   }
   
   @SuppressWarnings("unchecked")
   public static <T> T fromJsonTypes(String jsonStr,Type typeOfT){
	   Gson gson = new Gson();
	   T target = (T)gson.fromJson(jsonStr,typeOfT);
	   return  target;
   }
   @SuppressWarnings("unchecked")
   public static <T> T fromJsonTypesWithDate(String jsonStr,Type typeOfT,String ...pattern){
	   String format = 0==pattern.length?Constants.DATE_FORMAT_YMD:pattern[0];
	   Gson gson = new GsonBuilder().setDateFormat(format).create();
	   T target = (T)gson.fromJson(jsonStr,typeOfT);
	   return  target;
   }
 }

