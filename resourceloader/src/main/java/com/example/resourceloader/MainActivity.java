package com.example.resourceloader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends BaseActivity {

	private TextView textV;
	private ImageView imgV;
	private LinearLayout layout;

	protected DexClassLoader classLoader = null;
	protected PathClassLoader pathClassLoader = null;
	protected File fileRelease = null; //?????
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.);
		
		textV = (TextView)findViewById(R.id.text);
		imgV = (ImageView)findViewById(R.id.imageview);
		layout = (LinearLayout)findViewById(R.id.layout);
		
		fileRelease = getDir("dex", 0);
		
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				copyData("ResourceLoaderApk1.apk");
				String filesDir = getFilesDir().getAbsolutePath();
				String filePath = filesDir + File.separator + "ResourceLoaderApk1.apk";
//				String filesDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//		        String filePath = filesDir + File.separator + "tmp" + File.separator + "ResourceLoaderApk1.apk";
				Log.e("Loader", "filePath:"+filePath);
				Log.e("Loader", "isExist:"+new File(filePath).exists());
//				classLoader = new DexClassLoader(filePath, fileRelease.getAbsolutePath(), null, getClassLoader());
				pathClassLoader = getPathClassLoad("com.dynamic.impl");
				loadResources(filePath);
//				setContent();
				setPathContent();
				//printResourceId();
				//setContent1();
				//printRField();
			}});
		
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				copyData("ResourceLoaderApk2.apk");
				String filesDir = getFilesDir().getAbsolutePath();
//				String filesDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		        String filePath = filesDir + File.separator +"ResourceLoaderApk2.apk";
		        classLoader = new DexClassLoader(filePath, fileRelease.getAbsolutePath(), null, getClassLoader()); 
				loadResources(filePath);
				setContent();
				setContent1();
			}});
	}
	
	@SuppressLint("NewApi")
	private void setContent(){
		try{
			Class clazz = classLoader.loadClass("com.example.resourceloaderapk.UIUtil");
			Method method = clazz.getMethod("getTextString", Context.class);
			String str = (String)method.invoke(null, this);
			textV.setText(str);
			method = clazz.getMethod("getImageDrawable", Context.class);
			Drawable drawable = (Drawable)method.invoke(null, this);
			imgV.setBackground(drawable);
			method = clazz.getMethod("getLayout", Context.class);
			View view = (View)method.invoke(null, this);
			layout.addView(view);
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
	}
	
	/**
	 * ?????????????
	 */
	private void setContent1(){
		int stringId = getTextStringId();
		int drawableId = getImgDrawableId();
		int layoutId = getLayoutId();
		Log.i("Loader", "stringId:"+stringId+",drawableId:"+drawableId+",layoutId:"+layoutId);
	}
	
	@SuppressLint("NewApi")
	private int getTextStringId(){
		try{
			Class clazz = classLoader.loadClass("com.example.resourceloaderapk2.R$string");
			Field field = clazz.getField("app_name");
			int resId = (int)field.get(null);
			return resId;
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
		return 0;
	}
	
	@SuppressLint("NewApi")
	private int getImgDrawableId(){
		try{
			Class clazz = classLoader.loadClass("com.example.resourceloaderapk2.R$drawable");
			Field field = clazz.getField("ic_launcher");
			int resId = (int)field.get(null);
			return resId;
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
		return 0;
	}
	
	@SuppressLint("NewApi")
	private int getLayoutId(){
		try{
			Class clazz = classLoader.loadClass("com.example.resourceloaderapk2.R$layout");
			Field field = clazz.getField("activity_main");
			int resId = (int)field.get(null);
			return resId;
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
		return 0;
	}
	
	@SuppressLint("NewApi")
	private void printResourceId(){
		try{
			Class clazz = classLoader.loadClass("com.example.resourceloaderapk.UIUtil");
			Method method = clazz.getMethod("getTextStringId", null);
			Object obj = method.invoke(null, null);
			Log.i("Loader", "stringId:"+obj);
			Log.i("Loader", "newId:"+ R.string.app_name);
			method = clazz.getMethod("getImageDrawableId", null);
			obj = method.invoke(null, null);
			Log.i("Loader", "drawableId:"+obj);
			Log.i("Loader", "newId:"+ R.drawable.ic_launcher);
			method = clazz.getMethod("getLayoutId", null);
			obj = method.invoke(null, null);
			Log.i("Loader", "layoutId:"+obj);
			Log.i("Loader", "newId:"+ R.layout.activity_main);
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
	}
	
	private void printRField(){
		Class clazz = R.id.class;
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			Log.i("Loader", "fields:"+field);
		}
		Class clazzs = R.layout.class;
		Field[] fieldss = clazzs.getFields();
		for(Field field : fieldss){
			Log.i("Loader", "fieldss:"+field);
		}
	}

	private void copyData(String fileName){
		InputStream in = null;

		FileOutputStream out = null;

		String path = getFilesDir().getAbsolutePath() + File.separator + fileName; // data/data目录

		File file = new File(path);

		if (!file.exists()) {
			try {
				in = this.getAssets().open(fileName); // 从assets目录下复制
				out = new FileOutputStream(file);
				int length = -1;
				byte[] buf = new byte[1024];
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if (in != null) {
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				if (out != null) {
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	private PathClassLoader getPathClassLoad(String action) {

		try {
//		Intent intent = new Intent("com.dynamic.impl", null);
			Intent intent = new Intent(action, null);
			//获得包管理器
			PackageManager pm = getPackageManager();
			List<ResolveInfo> resolveinfoes = pm.queryIntentActivities(intent, 0);
			//获得指定的activity的信息
			ActivityInfo actInfo = resolveinfoes.get(0).activityInfo;
			//获得apk的目录或者jar的目录
			String apkPath = actInfo.applicationInfo.sourceDir;
			//native代码的目录
			String libPath = actInfo.applicationInfo.nativeLibraryDir;

			return new PathClassLoader(apkPath, libPath, this.getClassLoader());
		} catch (Exception e) {
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}

		return null;
	}

	@SuppressLint("NewApi")
	private void setPathContent(){
		try{
			Class clazz = pathClassLoader.loadClass("com.example.resourceloaderapk.UIUtil");
			Method method = clazz.getMethod("getTextString", Context.class);
			String str = (String)method.invoke(null, this);
			textV.setText(str);
			method = clazz.getMethod("getImageDrawable", Context.class);
			Drawable drawable = (Drawable)method.invoke(null, this);
			imgV.setBackground(drawable);
			method = clazz.getMethod("getLayout", Context.class);
			View view = (View)method.invoke(null, this);
			layout.addView(view);
		}catch(Exception e){
			Log.i("Loader", "error:"+Log.getStackTraceString(e));
		}
	}
}
