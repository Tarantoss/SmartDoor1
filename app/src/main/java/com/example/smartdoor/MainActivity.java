package com.example.smartdoor;

import java.util.Set;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.smartdoor.R;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

	private static final int REQUEST_ENABLE_BT = 3;

	private ArrayAdapter<String> mArrayAdapter;

	private ListView mConversationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();

		mArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);

		//권한처리
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
			{
				ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
			}
		}

		if (!mBTAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}

		Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}
		// 리스트 뷰에 표시
		mConversationView = (ListView) findViewById(R.id.in);
		mConversationView.setAdapter(mArrayAdapter);

		mConversationView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent btIntent = new Intent(MainActivity.this,
						BTControll.class);
				btIntent.putExtra("mac", arg0.getAdapter().getItem(arg2)
						.toString().split("\n")[1]);
				startActivity(btIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}