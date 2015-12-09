package com.example.notes.photo;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class Sound {
	public void record_sound (int requestCode, int resultCode, Intent data ){
		

		
		
		
		
		
		
	}

}
class RecordTask extends AsyncTask<Void, Integer, Void> {
	private boolean isRecording = true, isPlaying = false; // 标记

	private int frequence = 16000;// 8000;
	private File audioFile;					// //录制频率，单位hz.这里的值注意了，写的不好，可能实例化AudioRecord对象的时候，会出错。我开始写成11025就不行。这取决于硬件设备
	private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	private int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	@Override
	protected Void doInBackground(Void... arg0) {
		isRecording = true;
		try {
			// 开通输出流到指定的文件
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(audioFile)));
			// 根据定义好的几个配置，来获取合适的缓冲大小
			int bufferSize = AudioRecord.getMinBufferSize(frequence,
					channelConfig, audioEncoding);
			// 实例化AudioRecord
			AudioRecord record = new AudioRecord(
					MediaRecorder.AudioSource.MIC, frequence,
					channelConfig, audioEncoding, bufferSize);
			// 定义缓冲
			short[] buffer = new short[bufferSize];

			// 开始录制
			record.startRecording();

			int r = 0; // 存储录制进度
			// 定义循环，根据isRecording的值来判断是否继续录制
			while (isRecording) {
				// 从bufferSize中读取字节，返回读取的short个数
				// 这里老是出现buffer overflow，不知道是什么原因，试了好几个值，都没用，TODO：待解决
				int bufferReadResult = record
						.read(buffer, 0, buffer.length);
				// 循环将buffer中的音频数据写入到OutputStream中
				for (int i = 0; i < bufferReadResult; i++) {
					dos.writeShort(buffer[i]);
				}
				publishProgress(new Integer(r)); // 向UI线程报告当前进度
				r++; // 自增进度值
			}
			// 录制结束
			record.stop();
			Log.v("The DOS available:", "::" + audioFile.length());
			dos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}





}