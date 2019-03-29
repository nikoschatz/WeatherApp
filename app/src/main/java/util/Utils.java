package util;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    public static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static String ICON_URL = "https://openweathermap.org/img/w/";
    public static final String OPEN_WEATHER_MAP_API = "9093d445743228761b90bb706f13f699";


    //my personal key from openweathermap
    //public static String APPID = "{9093d445743228761b90bb706f13f699}";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
     JSONObject jObj = jsonObject.getJSONObject(tagName);
     return jObj;
    }
    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }
    public static float getFloat(String tagName,JSONObject jsonObject)throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}
