package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;

import util.Utils;

public class WeatherHttpClient {



    public String getWeatherData(String place){
        HttpsURLConnection connection = null;
        InputStream inputStream =  null;
        try{
        connection = (HttpsURLConnection) (new URL(Utils.BASE_URL + place)).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();

       // connection.addRequestProperty("x-api-key", Utils.OPEN_WEATHER_MAP_API);

            //read the response
            StringBuffer stringBuffer =  new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = bufferedReader.readLine()) !=null){
                stringBuffer.append(line + "\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
