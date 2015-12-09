package com.example.notes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import photo_multis.ImgFileListActivity;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


import com.example.notes.adapter.ContactListAdapter;
import com.example.notes.adapter.ListViewAdapter;
import com.example.notes.adapter.NotesListAdapter;
import com.example.notes.entity.Friend;
import com.example.notes.entity.Group;
import com.example.notes.photo.Photo;
import com.example.notes.photo.Photo_pulish;
import com.example.notes.photo.Photos;
import com.example.notes.utils.DatabaseHelper;
import com.example.notes.utils.InitData;
import com.example.notes.utils.SortName;
import com.example.notes.view.MyLetterListView;
import com.example.notes.view.MyLetterListView.OnTouchingLetterChangedListener;
import com.example.notes.view.RoundImage;
import com.zxing.activity.CaptureActivity;


import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class Main extends Activity implements IXListViewListener, OnScrollListener {

	public static Main instance = null;
	private ViewPager mTabPager;
	// private ImageView mTabImg;// 动画图片
	private ImageView mImg1, mImg2, mImg3, mImg4;
	private TextView mTxt1, mTxt2, mTxt3, mTxt4;
	private LinearLayout mTab1, mTab2, mTab3, mTab4;
	private int currIndex = 0;// 当前标签页的编号
	private int one;// 单个水平动画位移
	private TextView tv;
	private Note_write_popmenu menuWindow;
	private ImageView head_image;
	// 2015-11-11
	 private Thread thread;
	private ArrayList<ImageView> mImgs;
	private ArrayList<TextView> mTxts;
	private static final int[] imgs_pressed = { R.drawable.icon_note_pressed,
			R.drawable.icon_faxian_pressed, R.drawable.icon_contact_pressed,
			R.drawable.icon_me_pressed };
	private static final int[] imgs_normal = { R.drawable.icon_note_normal,
			R.drawable.icon_faxian_normal, R.drawable.icon_contact_normal,
			R.drawable.icon_me_normal };

	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private int tiem=0;
	private XListView xlist;
	private Handler handler;//第一个标签页刷新数据
    private Boolean judge=false;
	// 11-17
	private ListView listview_friends;
//	private HashMap<String, Integer> selector;// 存放含有索引字母的位置
	private LinearLayout layoutIndex;
	private TextView tv_show;
//	private ListViewAdapter adapter;
	private String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
//	private List<Friend> friends = null;
//	private List<Friend> newFriends = new ArrayList<Friend>();
	private int height;// 字体高度
	private boolean flag = false;
    private View vciew;
	// 2015-11-13
	private LinearLayout bottom;
	private int bottom_height;
    private Handler mHandler;
    private int photo_label=0;
    private int label=0;
    private int a;
    String fileName=null;
	String fileName2=null;
	WindowManager.LayoutParams lp;
	// 2015-11-25
	private BaseAdapter adapter;
	private ListView contactList;
	private TextView overlay; // 对话框首字母textview
	private MyLetterListView letterListView; // A-Z listview
	private Handler overlayHandler;
	private OverlayThread overlayThread; // 显示首字母对话框
	private HashMap<String, Integer> selector;// 存放含有索引字母的位置
	private ArrayList<Friend> allFriends; // 通讯录列表：包括多人笔记本布局+好友列表
	private ArrayList<Friend> friends;// 好友列表
	private List<Friend> newFriends;// 排序后的好友列表
	private ArrayList<Group> groups;// 多人笔记本列表
	private LocationClient mLocationClient;
	private MyLocationListener mMyLocationListener;

	private String currentCity; // 用于保存定位到的城市
	private int locateProcess = 1; // 记录当前定位的状态 正在定位-定位成功-定位失败
	private boolean isNeedFresh;
	private DatabaseHelper helper;
	private int isrefresh = 0;
	private static String path="/sdcard/myHead/";
	private Bitmap bt ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		instance = this;
		one = getWindowManager().getDefaultDisplay().getWidth() / 4;
		bottom = (LinearLayout) findViewById(R.id.bottom);
		init();
		
		 bt = BitmapFactory.decodeFile(path + "head.jpg");
		if (bt!=null) {
			Drawable drawable = new BitmapDrawable(bt);
			head_image.setImageDrawable(drawable);
		}else {
			
		}
		menuWindow = new Note_write_popmenu(Main.this, itemsOnClick);
		
		menuWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				WindowManager.LayoutParams lp=getWindow().getAttributes();
		        lp.alpha = 1f;
		        getWindow().setAttributes(lp);	
			}
		});
	}

	// 获取底部菜单栏的高度
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		bottom_height = bottom.getHeight();
	}

	// 页面数据初始化
	public void init() {
		vciew=getLayoutInflater().from(this).inflate(R.layout.myheadr,  null );
		 head_image=(ImageView) vciew.findViewById(R.id.note_bg);
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		// 获取图片
		mImg1 = (ImageView) findViewById(R.id.bottom_note_img);
		mImg2 = (ImageView) findViewById(R.id.bottom_find_img);
		mImg3 = (ImageView) findViewById(R.id.bottom_contact_img);
		mImg4 = (ImageView) findViewById(R.id.bottom_me_img);
		mImgs = new ArrayList<ImageView>();
		mImgs.add(mImg1);
		mImgs.add(mImg2);
		mImgs.add(mImg3);
		mImgs.add(mImg4);

		mImg1.setOnClickListener(new MyOnClickListener(0));
		mImg2.setOnClickListener(new MyOnClickListener(1));
		mImg3.setOnClickListener(new MyOnClickListener(2));
		mImg4.setOnClickListener(new MyOnClickListener(3));

		// 获取底部文字
		mTxt1 = (TextView) findViewById(R.id.bottom_note_text);
		mTxt2 = (TextView) findViewById(R.id.bottom_find_text);
		mTxt3 = (TextView) findViewById(R.id.bottom_contact_text);
		mTxt4 = (TextView) findViewById(R.id.bottom_me_text);
		mTxts = new ArrayList<TextView>();
		mTxts.add(mTxt1);
		mTxts.add(mTxt2);
		mTxts.add(mTxt3);
		mTxts.add(mTxt4);
        mHandler=new Handler();
		// 获取各个标签
		mTab1 = (LinearLayout) findViewById(R.id.bottom_note);
		mTab2 = (LinearLayout) findViewById(R.id.bottom_find);
		mTab3 = (LinearLayout) findViewById(R.id.bottom_contact);
		mTab4 = (LinearLayout) findViewById(R.id.bottom_me);

		// 各个标签添加监听事件
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		mTab4.setOnClickListener(new MyOnClickListener(3));

		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.tab_note, null);
		View view2 = mLi.inflate(R.layout.tab_find, null);
		View view3 = mLi.inflate(R.layout.tab_contact, null);
		View view4 = mLi.inflate(R.layout.tab_me, null);
		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			// @Override
			// public CharSequence getPageTitle(int position) {
			// return titles.get(position);
			// }

			// 在此处实例化子页面中的对象
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				// 2015-11-11 为第一个页面时加载xlistview的数据
				if (position == 0) {
					// TODO 页面滚动
					do_BottomList(container);
				}
				// 2015-11-17 为第3个页面时加载xlistview的数据
				if (position == 2) {
					if (isrefresh == 0) {
						do_listview_friends(container);
						isrefresh++;
					}
				}
				// 11-18 为第四个页面设置圆形头像
				if (position == 3) {
					RoundImage user_head = (RoundImage) container
							.findViewById(R.id.tab_me_userimg);
					user_head.setImageResource(R.drawable.icon_user_head);
				}
				return views.get(position);
			}
		};
		mTabPager.setAdapter(mPagerAdapter);
	}

	// 底部菜单添加监听
	public class MyOnClickListener implements OnClickListener {

		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};

	// 各个标签页切换监听，设置底部动画效果
	public class MyOnPageChangeListener implements OnPageChangeListener {
		final int txt_pressed = Main.this.getResources().getColor(
				R.color.main_txt_pressed);
		final int txt_normal = Main.this.getResources().getColor(
				R.color.main_txt_normal);

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			// 设置选中页面图标为pressed
			mImgs.get(arg0).setImageResource(imgs_pressed[arg0]);
			mTxts.get(arg0).setTextColor(txt_pressed);
			// 移动的动画
			animation = new TranslateAnimation(currIndex * one, arg0 * one, 0,
					0);
			// 选中之前的当前页面图标修改为normal
			mImgs.get(currIndex).setImageResource(imgs_normal[currIndex]);
			mTxts.get(currIndex).setTextColor(txt_normal);

			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/**
	 * 各标签页中涉及到的一些页面跳转(主要各标题上的功能操作)
	 * 
	 * @param
	 */

	// 页面1--点击写日记弹出自定义的popmenu
	
	public void to_write_note(View v) {
		tv = (TextView) this.findViewById(R.id.tab_wtrie_note);
		// 把文字控件添加监听，点击弹出自定义窗口
		tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 实例化SelectPicPopupWindow
				
				// 显示窗口位置
				menuWindow.showAtLocation(Main.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
						bottom_height); // 设置layout在PopupWindow中显示的位置
				ColorDrawable cd = new ColorDrawable(0x000000);
				menuWindow.setBackgroundDrawable(cd); 
			    lp=getWindow().getAttributes();
				lp.alpha = 0.4f;
				getWindow().setAttributes(lp);
				
			}
		});
	}

	
	// 为弹出的popmenu实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {
		Intent intent = null;

		public void onClick(View v) {
		
			menuWindow.dismiss();
			
			switch (v.getId()) {
			case R.id.note_take_photo:

			
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 99);
		
	
				break;
			case R.id.note_txt:
				Toast.makeText(Main.this, "文本", 2500).show();
				intent = new Intent(Main.this, Note_write_txt.class);
				startActivity(intent);
				break;
			case R.id.note_weilaixin:
				Toast.makeText(Main.this, "未来信", 2500).show();
				break;
			case R.id.note_short_aduio:
				Toast.makeText(Main.this, "短音频", 2500).show();
				Intent intent22 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION); 
				judge=true;
				startActivityForResult(intent22,4); 
			
				break;
			case R.id.note_from_phone:
            //2015.12.3没有调用系统的相册跳转到自己的类里面
				Intent intent2=new  Intent(Main.this, ImgFileListActivity.class);
				startActivity(intent2);

				
			
				break;
				
			default:
				break;  
			}
		}
		
	};
	
	   

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 调用相机和相册的结果处理方法
	
		   switch (requestCode) {
//		   2015.12.2调用扫码
		   case 0:
				Bundle bundle = data.getExtras();
				String scanResult = bundle.getString("result");
				Toast.makeText(Main.this, scanResult, 1000).show();
			   break;
			case 1:

					cropPhoto(data.getData());
					
 
					break;
			
				
			
			case 3:
				if (data != null) {
					Bundle extras = data.getExtras();
					bt = extras.getParcelable("data");
					if(bt!=null){
						
						setPicToView(bt);//保存在SD卡中
						head_image.setImageBitmap(bt);//用ImageView显示出来
				
					}
				}
				break;//调用相机传图片传到Photo_pulish
		
			case 4:
			//	2015.12.2	调用录音机
				Uri android_uri=data.getData();
				Uri audioPath = data.getData();
				Intent intent_Auto=new Intent(Main.this, Note_write_txt.class);
			    String[] filePathColumn1 = { MediaStore.Images.Media.DATA };
				Cursor curso1r = getContentResolver().query(audioPath,
		                    filePathColumn1, null, null, null);
		        curso1r.moveToFirst();

		        int columnIndex1 = curso1r.getColumnIndex(filePathColumn1[0]);
		        String picturePath1 = curso1r.getString(columnIndex1);
		        intent_Auto.putExtra("picturePath1", picturePath1);
		        startActivity(intent_Auto);
				Toast.makeText(Main.this, picturePath1, 1000).show();
				
			    break;
	
				case 99:
					
					    Intent intent=new Intent(Main.this, Photo_pulish.class);
				
						intent.putExtras(data);
				        startActivity(intent);
				
			
					
			   break;

			default:
				break;
				
			}
		   
		    super.onActivityResult(requestCode, resultCode, data);
	
	};
	

		
		
        
    


	// 页面1--给底部的listview添加数据
	 public void do_BottomList(View container) {
		   xlist = (XListView) container.findViewById(R.id.xlistView);
		  
		
		
		   if (a==0) {
				xlist.addHeaderView(vciew, null, true);
				Toast.makeText(Main.this, a+"", 1000).show();
				a++;
		   }
	
		   xlist.setXListViewListener(this);
		
		   RoundImage user_head = (RoundImage) container
				.findViewById(R.id.note_user_head);
	     

		
		  user_head.setImageResource(R.drawable.icon_user_head);
		

		  NotesListAdapter msAdapter=new NotesListAdapter(Main.this);
		  
		  xlist.setAdapter(msAdapter);
	      xlist.setXListViewListener(this);

		  xlist.setPullLoadEnable(true);


		// 2015-11-13 内部item点击监听
		  xlist.setOnScrollListener(new OnScrollListener(
				) {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				if (xlist.getLastVisiblePosition()==14) {
					xlist.setPullLoadEnable(true);
					
				}
			}
		});
		   xlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(Main.this, "点击了第" + arg2 + "个项目", 2500).show();
		
			}
		   });
		// Utility.setListViewHeightBasedOnChildren(xlist);

	       }

	// 页面1--点击背景图弹窗口
	@SuppressLint("NewApi") public void change_bg(View v) {
	
		  AlertDialog.Builder ab = new AlertDialog.Builder(this);
		  ab.setMessage("是否修改背景图片？")
				.setPositiveButton("是", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {

						Intent intent1 = new Intent(Intent.ACTION_PICK, null);
						intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
						startActivityForResult(intent1, 1);
	                
						
						
						
						
						// 跳转到相册选择页面 TODO
						Toast.makeText(Main.this, "修改背景图片成功！", 2500).show();
					}
		 }).setNegativeButton("否", null).show();

		
	}

	// 页面2--扫一扫二维码 
	    public void scan_code(View v) {
//	    	2015.12.2
		Toast.makeText(Main.this, "扫描二维码", 2500).show();
		Intent openCameraIntent = new Intent(Main.this,CaptureActivity.class);
		startActivityForResult(openCameraIntent, 0);
	
	}

	// 页面2--饰品制作
	   public void make_gift(View v) {
		Toast.makeText(Main.this, "制作饰品。。。", 2500).show();
	}

	// 页面3--显示列表好友
	  public void do_listview_friends(View container) {
		//2015-11-27
		selector = new HashMap<String, Integer>();
		allFriends = new ArrayList<Friend>();
		newFriends = new ArrayList<Friend>();
		groups = new ArrayList<Group>();
		overlayThread = new OverlayThread();
		helper = new DatabaseHelper(this);
		overlayHandler = new Handler();
		isNeedFresh = true;

		contactList = (ListView) findViewById(R.id.list_view);
		// 右侧字母索引监听
		letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		locateProcess = 1;
		// contactList.setAdapter(adapter);
		contactList.setOnScrollListener(this);
		// 初始化字母弹出提示框，模拟好友数据，多人笔记本数据
		initOverlay();
		// 添加两个空白数据填充通讯录Adapter的前两项
		Friend f1 = new Friend();
		allFriends.add(f1);
		
		Friend f2 = new Friend();
		allFriends.add(f2);
		newFriends = InitData.friendsInit();
		allFriends.addAll(newFriends);
		groups = InitData.groupsInit();
		// 循环字母表，找出newFriends中对应字母的位置
		for (int j = 0; j < SortName.indexStr.length; j++) {
			for (int i = 0; i < newFriends.size(); i++) {
				if (newFriends.get(i).getName().equals(SortName.indexStr[j])) {
					selector.put(SortName.indexStr[j], i);
				}
			}
		}
		setAdapter(allFriends, groups);


	    }

	/**
	 * 绘制索引列表
	 */
	    public void getIndexView() {

		LinearLayout.LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT, height);
		for (int i = 0; i < indexStr.length; i++) {
			final TextView tv = new TextView(this);
			tv.setLayoutParams(params);
			tv.setText(indexStr[i]);
            tv.setTextSize(10);
			tv.setPadding(10, 0, 10, 0);
			layoutIndex.addView(tv);

			layoutIndex.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					float y = event.getY();
					int index = (int) (y / height);
					if (index > -1 && index < indexStr.length) {// 防止越界
						String key = indexStr[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listview_friends.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
								listview_friends.setSelectionFromTop(
										pos
												+ listview_friends
														.getHeaderViewsCount(),
										0);
							} else {
								listview_friends.setSelectionFromTop(pos, 0);// 滑动到第一项
							}
							tv_show.setVisibility(View.VISIBLE);

							tv_show.setText(indexStr[index]);
						}
					}
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#606060"));
						break;

					case MotionEvent.ACTION_MOVE:
						break;   
					case MotionEvent.ACTION_UP:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#00ffffff"));
						tv_show.setVisibility(View.GONE);
						break;
					}
					return true;
					
				}
			  });
		    }
	      }

	// 页面3--跳转到好友搜索页面
	public void to_searchFriends(View v) {
		Intent intent = new Intent(Main.this, Contact_Friend_Search.class);
		startActivity(intent);
	}

	// 页面3--跳转到新建多人笔记本的页面
	public void to_addGroup(View v) {
		Intent intent = new Intent(Main.this, Contact_Group_Add.class);
		startActivity(intent);
	}

	// 页面3--跳转到多人笔记本编辑页面
	public void to_groupEdit(View v) {
		Intent intent = new Intent(Main.this, Contact_Group_Edit.class);
		startActivity(intent);
	}

	// 页面3--点好友跳转到好友资料页面
	public void to_friendInfo(View v) {
		Intent intent = new Intent(Main.this, Contact_Friend_Info.class);
		startActivity(intent);
	}

	// 页面4--点头像栏跳转到个人信息页面
	public void to_userinfo(View v) {
		Intent intent = new Intent(Main.this, Me_UserInfo.class);
		startActivity(intent);
	}

	// 页面4--点建议栏跳转到建议页面
	public void to_advice(View v) {
		Intent intent = new Intent(Main.this, Me_Advice.class);
		startActivity(intent);
	}

	// 页面4--点未来信栏跳转到我的未来信页面
	public void to_Myweilaixin(View v) {
		Toast.makeText(Main.this, "我的未来信", 2500).show();
		Intent intent = new Intent(Main.this, Me_Myweilaixin.class);
		startActivity(intent);
	}

	// 页面4--点设置栏跳转到设置页面
	public void to_setting(View v) {
		Intent intent = new Intent(Main.this, Me_Setting.class);
		startActivity(intent);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
    mHandler.postDelayed(new Runnable() {
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		onLoad();
		
	  }
      }, 1000);
	  }

	@Override
	public void onLoadMore() {
		
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
			
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, "我在执行", 1000).show();
				onLoad();
				
			}
		}, 1000);
	         

	               
	        
		
	}

	private void onLoad() {
		xlist.stopRefresh();
		xlist.stopLoadMore();
		xlist.setRefreshTime("刚刚");
	}

	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onRestart() {
		System.out.println("当前页面下标：" + currIndex);
		if (currIndex == 2) {
			adapter.notifyDataSetChanged();
		}
		

		super.onRestart();
	}

	// 生命周期控制定位 TODO
	protected void onStop() {
		// mLocationClient.stop();
		System.out.println("---stop---");
		super.onStop();
	}

	private boolean mReady;

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		mReady = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private boolean isScroll = false;

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		public void onTouchingLetterChanged(final String s) {
			isScroll = false;
			if (ContactListAdapter.alphaIndexer.get(s) != null) {
				int position = ContactListAdapter.alphaIndexer.get(s);
				contactList.setSelection(position);
				overlay.setText(s);
				overlay.setVisibility(View.VISIBLE);
				overlayHandler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				overlayHandler.postDelayed(overlayThread, 1000);
			}
		}
	}
	
	private void InitLocation() {
		// 设置定位参数
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(10000); // 10分钟扫描1次
		// 需要地址信息，设置为其他任何值（string类型，且不能为null）时，都表示无地址信息。
		option.setAddrType("all");
		// 设置是否返回POI的电话和地址等详细信息。默认值为false，即不返回POI的电话和地址信息。
		option.setPoiExtraInfo(true);
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setProdName("通过GPS定位我当前的位置");
		// 禁用启用缓存定位数据
		option.disableCache(true);
		// 设置最多可返回的POI个数，默认值为3。由于POI查询比较耗费流量，设置最多返回的POI个数，以便节省流量。
		option.setPoiNumber(3);
		// 设置定位方式的优先级。
		// 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。
		option.setPriority(LocationClientOption.GpsFirst);
		mLocationClient.setLocOption(option);
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			Log.e("info", "city = " + arg0.getCity());
			if (!isNeedFresh) {
				return;
			}
			isNeedFresh = false;
			if (arg0.getCity() == null) {
				locateProcess = 3; // 定位失败
				contactList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				return;
			}
			currentCity = arg0.getCity().substring(0,
					arg0.getCity().length() - 1);
			locateProcess = 2; // 定位成功
			contactList.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {

		}
	}
	private void setAdapter(List<Friend> friends, List<Group> groups) {
		adapter = new ContactListAdapter(this, friends, groups);
		contactList.setAdapter(adapter);
		contactList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 针对好友列表设置点击监听，操作跳转
				if (position >= 2) {
					Intent intent = new Intent(Main.this,
							Contact_Friend_Info.class);
					intent.putExtra("friend", allFriends.get(position));
					startActivity(intent);
				}
			}
		});
	}
	
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL
				|| scrollState == SCROLL_STATE_FLING) {
			isScroll = true;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (!isScroll) {
			return;
		}
	}
//储存图片的2015.11.28
	private void setPicToView(Bitmap mBitmap) {
		 String sdStatus = Environment.getExternalStorageState();  
       if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
              return;  
          }  
		FileOutputStream b = null;
		File file = new File(path);
		file.mkdirs();// 创建文件夹
		String fileName =path + "head.jpg";//图片名字
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭流
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
//	裁剪图片的2015.11.28
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		 // aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

}
