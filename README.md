## AudioStream-demo


## What it does

This is an demo to show how to streaming audio from  Android Phone to another device, like S3C6410 Development board or Android Phone.

This demo use AudioTrack and AudioRecord API to capture audio and play with 
TCP socket packet, then send it to 127.0.0.1,which is localhost.

Last test fruit, if you say something to the phone, it will play it, like a **sound intensifier**.

##How does it work

the working flow as below

> AudioRecorder capture data ->tcp server ->tcp client(127.0.0.1) -> AudioTrack


## How to use it and change to communicate with another

It is easy, you just only to download it, decompress it and import to Eclipse. Then run it, you will see there is a button on the top right corner, select "start Audio" button, that's all.


Next, we will preventation how to change to comunicate with another, you can find below code at line 55 in Volplayer.java.
> ss = new Socket("127.0.0.1", 15636); 
 
change here to another server IP and port. like this. then you will receive another device sound and play it.
> ss = new Socket("192.168.1.118", 15636);


As for the record side, as TCP server, you should better not change it.


##why there are some error
If you find there are some error in this demo, they are not really error. you can only **right click the project name**, select **"Android tools"**, and then **"clear Lint Markers"**. OK, the world is stillness!

## Lately Update

At 2016/2/1 update, We solved the problems of noise and echo by **class AcousticEchoCanceler**, In order to use it, we must be get **class AudioRecord sessionId** by method of getAudioSessionId(), then invoke AcousticEchoCanceler.create(sessionId).

##Last but not least

if you want to communicate with me,you can join our **QQ group:498314991**, or you are a foreigner, not Chinese, you can also send E-mail:**370045744@qq.com**.