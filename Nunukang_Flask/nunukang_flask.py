from ast import Add
from datetime import date, datetime

import pandas as pd
import requests
import torch
from flask import Flask, request, jsonify
from flask_cors import CORS
from flask_mysqldb import MySQL
from PIL import Image as im

import domain.address.address as address
import domain.fish.fish as fish
import domain.picture.picture as pic
from domain.address.address import Address
from domain.weather.weather import Weather

import traceback



app = Flask(__name__)

CORS(app)


best_pt_path = "./model/fish_sample_1000/runs/train/exp3/weights/best.pt"


@app.route('/', methods=['POST'])
def modeling():
    # model settings
    model = torch.hub.load("./model/fish_sample_1000", 'custom', path=best_pt_path,source="local")

    model.conf = 0.4
    model.name = "/"
    
    user_email = request.form["email"]
    file_path = request.form["path"]
    file_name = request.form["fileName"]
    picture = request.files["picture"]
    
    pic_instance = pic.Picture(user_email, file_path, picture, file_name)

    if (pic_instance.save_picture()):
        img = im.open(pic_instance.get_image_path())

        result = model(img, 640) 
        result_img = result.render()
        
        img.save(f"{pic_instance.file_path}/{pic_instance.file_name}.jpg", format="JPEG")
    
        for img in result.imgs:
            img_base64 = im.fromarray(img)
            img_base64.save(f"{pic_instance.file_path}/{pic_instance.file_name}_model.jpg", format="JPEG")


        img_bbox_data = result.pandas().xyxy[0]

        try :
            if (len(img_bbox_data) == 2):
            
                fish_instance = fish.Fish(img_bbox_data.values[0],
                                    img_bbox_data.values[1],
                                    img_bbox_data.values[1][-1],
                                    user_email,
                                    pic_instance.load_picture_path,
                                    pic_instance.file_name,)
                
                result = fish_instance.get_result_data()

                print(fish_instance.get_result_data())
                return fish_instance.get_result_data()
            else :
                return "None"
            
        except Exception as e:
            print(e)
            print(traceback.format_exc())
            return "None"
            
    else :
        return "None"
    



    

@app.route('/weather/location', methods=['POST'])
def getLocation():
    latitude = request.form["latitude"]
    longitude = request.form["longitude"]

    address = Address(latitude, longitude)
    weather = Weather(address.get_address_name_kor())

    return_dict = weather.get_weather_data(latitude, longitude)
    
    return jsonify(return_dict)



@app.route('/weather/search/location', methods=['GET'])
def findLocation():
    addr = request.args.to_dict()["location"]
    lat_lon = Address.get_lat_lon(addr)

    if(lat_lon != False):
        weather = Weather(addr)
        return_dict = weather.get_weather_data(lat_lon[0], lat_lon[1])
    
        return jsonify(return_dict)


    else:
        return "false"




if __name__ == '__main__':
    app.run(host='localhost', port=5000, debug=True)

