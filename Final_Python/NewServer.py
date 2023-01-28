import time
import socket
import random
import os
import math
from sense_hat import SenseHat

sense = SenseHat()
        
def avg(data):
	sum = 0
	for q in data:
		sum += float(q)
	return ( (float(sum) / len(data) ))

def temp():
	global sense
	sense.show_message("T")
	print("Gona colect temp for a minute and send the result")
	temps = []
	for x in range(10):
		temps.append(round(sense.get_temperature(), 2))
		time.sleep(0.1)
	print(temps)
	print("Preparing the string for server with min max and avg ")
	# Prepare first 5 temp readings and add them to the mainString
	mainString = ""
	for n in temps:
		mainString += "|"
		mainString += str(n)
    # Lets add also min max and avg at the end as a HEAVEY BACK LOADING SHOULD BE DONE ON SERVER SIDE.
	mainString += "|"
	mainString += str(min(temps))
	mainString += "|"
	mainString += str(max(temps))
	mainString += "|"
	mainString += str(avg(temps))

	print(mainString)
	return mainString

def hum():
	global sense
	sense.show_message("H")
	print("Gona colect temp for a minute and send the result")
	hums = []
	for x in range(10):
		hums.append(round(sense.get_humidity(),2))
		time.sleep(0.1)
	print(hums)
	print("Preparing the string for server with min max and avg ")
	# Prepare first 5 temp readings and add them to the mainString
	mainString = ""
	for n in hums:
		mainString += "|"
		mainString += str(n)
    # Lets add also min max and avg at the end as a HEAVEY BACK LOADING SHOULD BE DONE ON SERVER SIDE.
	mainString += "|"
	mainString += str(min(hums))
	mainString += "|"
	mainString += str(max(hums))
	mainString += "|"
	mainString += str(avg(hums))
	print(mainString)
	return(mainString)

def connect():
	ip = "192.168.3.128"
	port = 8009
	serverObject = socket.socket()
	server = socket.gethostname()
	print(server)
	serverObject.bind((ip, port))
	print("Waiting for connection.....")

	serverObject.listen(5)
	client,addr = serverObject.accept()
	print("Got a connection from " +str(addr))

	#Reciving message from server if
	message = 0
	data = client.recv(1)
	print("Data type recived from server is:" + str(type(data)))
	message = data.decode("utf-8")
	print(message)
	
	to_send = ""
	if(int(message) == 1):
		to_send = temp()
	elif(int(message) == 2):
		to_send = hum()

	client.send(to_send)
	client.close()
	serverObject.close()

print("aaa")
connect()
#temp()


	
