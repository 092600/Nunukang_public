from geopy.geocoders import Nominatim

class Address:
    def __init__(self, latitude, longitude):
        self.latitude = latitude
        self.longitude = longitude
    
    def get_address_name_kor(self):
        geolocoder = Nominatim(user_agent = 'South Korea', timeout=None)
        address = geolocoder.reverse(f'{self.latitude}, {self.longitude}')
        address_list = str(address).split(",")
        self.address_name_kor = address_list[4] + address_list[3] + address_list[2]
    
        return self.address_name_kor

    def get_lat_lon(address):
        geolocoder = Nominatim(user_agent = 'South Korea', timeout=None)
        
        try:
            geo = geolocoder.geocode(address)
            crd = (geo.latitude, geo.longitude)
            return crd
        except:
            return False
    