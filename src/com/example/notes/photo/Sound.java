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
	private boolean isRecording = true, isPlaying = false; // ���

	private int frequence = 16000;// 8000;
	private File audioFile;					// //¼��Ƶ�ʣ���λhz.�����ֵע���ˣ�д�Ĳ��ã�����ʵ����AudioRecord�����ʱ�򣬻�����ҿ�ʼд��11025�Ͳ��С���ȡ����Ӳ���豸
	private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	private int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	@Override
	protected Void doInBackground(Void... arg0) {
		isRecording = true;
		try {
			// ��ͨ�������ָ�����ļ�
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(audioFile)));
			// ���ݶ���õļ������ã�����ȡ���ʵĻ����С
			int bufferSize = AudioRecord.getMinBufferSize(frequence,
					channelConfig, audioEncoding);
			// ʵ����AudioRecord
			AudioRecord record = new AudioRecord(
					MediaRecorder.AudioSource.MIC, frequence,
					channelConfig, audioEncoding, bufferSize);
			// ���建��
			short[] buffer = new short[bufferSize];

			// ��ʼ¼��
			record.startRecording();

			int r = 0; // �洢¼�ƽ���
			// ����ѭ��������isRecording��ֵ���ж��Ƿ����¼��
			while (isRecording) {
				// ��bufferSize�ж�ȡ�ֽڣ����ض�ȡ��short����
				// �������ǳ���buffer overflow����֪����ʲôԭ�����˺ü���ֵ����û�ã�TODO�������
				int bufferReadResult = record
						.read(buffer, 0, buffer.length);
				// ѭ����buffer�е���Ƶ����д�뵽OutputStream��
				for (int i = 0; i < bufferReadResult; i++) {
					dos.writeShort(buffer[i]);
				}
				publishProgress(new Integer(r)); // ��UI�̱߳��浱ǰ����
				r++; // ��������ֵ
			}
			// ¼�ƽ���
			record.stop();
			Log.v("The DOS available:", "::" + audioFile.length());
			dos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}





}