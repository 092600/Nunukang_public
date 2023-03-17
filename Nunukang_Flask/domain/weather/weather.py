import json
import requests, math

from datetime import date

from config import config

class Weather:
    def __init__(self, location_name_kr):
        self.api_key = config.apikey
        self.location_name_kr = location_name_kr
        self.today = str(date.today())

    def get_weather_data(self, lat, lon):
        response = requests.get(f"https://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={self.api_key}")
        if (response.status_code == 200):

            k = 0
            resp = response.json()

            # print(resp["list"])

            for i in resp["list"]:
                if (i["dt_txt"].split(" ")[0] != self.today):
                    break
                k+=1
                return_list = [resp["list"][2:13], self.location_name_kr]
            
            return return_list

        else :
            return False
