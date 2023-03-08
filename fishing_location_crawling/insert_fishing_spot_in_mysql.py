import csv
import pymysql

conn = pymysql.connect(host='localhost',
                             user='username',
                             password='password!',
                             database='DB')

cursor = conn.cursor()


cnt = 0
for i in range(1, 18):
    with open("fishing_location_crawling/fishing_spot_csv/"+str(i)+"_copy.csv", "r", encoding="utf-8") as f:
        line =  csv.reader(f)
        # print(line)
        for spot in line:
            print(spot, end="\n")
        # insert_sql = "INSERT INTO fishing_spot (fisinh_spot_id, type, address, latitude, longitude, number, capacity, price, name ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);"
        # cnt+=1
        val = (cnt, spot[1], spot[3], spot[4], spot[5], spot[6], spot[7], spot[-1],spot[0])
        # cursor.execute(insert_sql, val)
        # conn.commit()
    break