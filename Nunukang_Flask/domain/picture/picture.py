import os
import datetime as dt


class Picture:
    file_path = ""
    file_name = ""
    picture = ""
    user_email = ""
    
    def __init__(self, user_email, file_path, picture, file_name):
        self.user_email = user_email
        self.file_name = file_name
        self.file_path = file_path +"/"+ user_email
        self.load_picture_path = "/fish/images/"+user_email+"/"+self.file_name
        self.picture = picture

    def save_picture(self):
        try :
            if (not (os.path.exists(self.file_path))):
                os.makedirs(self.file_path)
        except :
            print("error")
            return False
        finally :
            self.picture.save(self.file_path+"/"+self.file_name)
            return True


    def get_image_path(self):
        return self.file_path+"/"+self.file_name

