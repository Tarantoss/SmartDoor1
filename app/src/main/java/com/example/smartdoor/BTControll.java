package com.example.smartdoor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.smartdoor.R;

public class BTControll<Study_3> extends MainActivity {

	private String connectedDeviceName;
	private BluetoothAdapter btAdapter = null;
	private BluetoothDevice btDevice = null;
	private BluetoothSocket btSocket = null;
	private OutputStream btos;
	private InputStream btis;
	String pass;
	String ans = "";
	int cnt = 0;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btn0;
	Button reset;
	Button enter;
	Button cam;
	int i = 0;
	int re = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		btn5 = (Button) findViewById(R.id.button5);
		btn6 = (Button) findViewById(R.id.button6);
		btn7 = (Button) findViewById(R.id.button7);
		btn8 = (Button) findViewById(R.id.button8);
		btn9 = (Button) findViewById(R.id.button9);
		btn0 = (Button) findViewById(R.id.button0);
		cam = (Button) findViewById(R.id.buttoncam);
		reset = (Button) findViewById(R.id.buttonr);
		enter = (Button) findViewById(R.id.buttonE);

		getPreferences();
		setListener();

	}

	private void getPreferences() {
		SharedPreferences mPref = getSharedPreferences("pref", MODE_PRIVATE);
		pass = mPref.getString("pass", "0000");
		
	}

	public void setListener() {
		try {
			openBT();
			Toast.makeText(getApplicationContext(), "open Success",
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "open Failed",
					Toast.LENGTH_LONG).show();
			finish();

		}

		btn1.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "1";

				}
				return false;
			}
		});
		btn2.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "2";

				}
				return false;
			}
		});
		btn3.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "3";

				}
				return false;
			}
		});
		btn4.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "4";

				}
				return false;
			}
		});
		btn5.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "5";

				}
				return false;
			}
		});
		btn6.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "6";

				}
				return false;
			}
		});
		btn7.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "7";

				}
				return false;
			}
		});
		btn8.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "8";

				}
				return false;
			}
		});
		btn9.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "9";

				}
				return false;
			}
		});
		btn0.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					ans = ans + "0";

				}
				return false;
			}
		});
		enter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (re == 1) {

					Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다.",
							Toast.LENGTH_SHORT).show();
					SharedPreferences mPref = getSharedPreferences("pref",
							MODE_PRIVATE);
					SharedPreferences.Editor editor = mPref.edit();
					editor.remove("pass");
					editor.putString("pass", ans);
					editor.commit();
					pass = ans;
					re = 2;

				} else {
					if (pass.equals(ans)) {
						write("a");
						reset.setEnabled(true);
						Toast.makeText(getApplicationContext(), "열렸습니다.",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "틀렸습니다.",
								Toast.LENGTH_SHORT).show();
						reset.setEnabled(false);
					}
				}
				ans = "";
			}
		});
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				re = 1;
				reset.setEnabled(false);

			}
		});
		
		cam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), webcam.class);
				startActivity(intent);
			}
		});
		
	}

	public void openBT() throws IOException {

		//권한처리
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
			{
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
			}
		}

		Intent intent = getIntent();

		connectedDeviceName = intent.getExtras().getString("mac");
		btAdapter = BluetoothAdapter.getDefaultAdapter();

		Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {

				if (device.getAddress().equals(connectedDeviceName)) {
					btDevice = device;
					break;
				}
			}
		}

		UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
		btSocket = btDevice.createRfcommSocketToServiceRecord(uuid);
		btSocket.connect();

		Toast.makeText(getApplicationContext(),
				String.valueOf(btSocket.isConnected()), Toast.LENGTH_LONG)
				.show();

		btos = btSocket.getOutputStream();
		btis = btSocket.getInputStream();

	}

	public void closeBt() throws IOException {
		btos.close();
		btis.close();
		btSocket.close();

	}

	public void write(String str) {
		try {
			btos.write(str.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
