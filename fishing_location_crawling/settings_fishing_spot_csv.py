import csv
import pymysql


conn = pymysql.connect(host='localhost',
                             user='root',
                             password='tlawjdgns1!',
                             database='nunukang')

cursor = conn.cursor()

cnt = 0

for i in range(1, 18):
    with open("./fishing_spot_csv/"+str(i)+".csv", "r", encoding="utf-8") as f:
        line =  csv.reader(f)
        # print(line)
        for spot in line:
            # print(spot)
            cnt += 1
                
            if (spot[2] == "저수지"):
                fishing_spot_type = 2
            elif (spot[2] == "바다"): 
                fishing_spot_type = 1
            elif (spot[2] == "평지"):
                fishing_spot_type = 3
            else:
                continue

            if ((spot[5] != "" ) and (spot[6] != "")) :
                try:
                    insert_sql = "INSERT INTO fishing_spot (fishing_spot_id, type, address, latitude, longitude, number, capacity, price, name ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s);"
                    val = (cnt, fishing_spot_type, spot[4], spot[5], spot[6], spot[7], spot[10], spot[12], spot[1])
                    
                    cursor.execute(insert_sql, val)
                    conn.commit()
                except Exception as e:
                    print(spot[12])        
            
            else :
                continue