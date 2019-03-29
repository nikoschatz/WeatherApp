package com.example.weatherapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;
import model.Place;
import util.Utils;

public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;


    Weather weather = new Weather();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String city = "London,UK";

        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView)findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated =(TextView) findViewById(R.id.updateText);
        renderWeatherData("Athens");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void renderWeatherData(String city){

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});

    }

    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }


    }



    private  class WeatherTask extends AsyncTask<String, Void, Weather>{


        @Override
        protected void onPostExecute(Weather weather) {

            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();
            String sunriseDate = df.format(new Date(weather.place.getSunrise()));
            String sunsetDate = df.format(new Date(weather.place.getSunset()));
            String updateDate = df.format(new Date(weather.place.getLastupdate()));

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());


            cityName.setText(weather.place.getCity() + ","+ weather.place.getCountry());
            temp.setText(""+tempFormat+"C");
            humidity.setText("Humidity:"+weather.currentCondition.getHumidity()+"%");
            pressure.setText("Pressure:"+weather.currentCondition.getPressure()+"hPa");
            wind.setText("Wind:"+weather.wind.getSpeed()+"m/s");
            sunrise.setText("Sunrise:"+sunriseDate);
            sunset.setText("Sunset:"+sunsetDate);
            updated.setText("Last Updated:"+updateDate);
            description.setText("Condition:"+weather.currentCondition.getCondition()+"("+weather.currentCondition.getDescription()+")");




        }

        @Override
        protected Weather doInBackground(String... params) {

            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));


            weather = JSONWeatherParser.getWeather(data);



          //      Log.v("Data:", weather.place.getCity());


            return weather;
        }

    }

}
