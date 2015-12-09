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
	// private ImageView mTabImg;// ����ͼƬ
	private ImageView mImg1, mImg2, mImg3, mImg4;
	private TextView mTxt1, mTxt2, mTxt3, mTxt4;
	private LinearLayout mTab1, mTab2, mTab3, mTab4;
	private int currIndex = 0;// ��ǰ��ǩҳ�ı��
	private int one;// ����ˮƽ����λ��
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
	private Handler handler;//��һ����ǩҳˢ������
    private Boolean judge=false;
	// 11-17
	private ListView listview_friends;
//	private HashMap<String, Integer> selector;// ��ź���������ĸ��λ��
	private LinearLayout layoutIndex;
	private TextView tv_show;
//	private ListViewAdapter adapter;
	private String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
//	private List<Friend> friends = null;
//	private List<Friend> newFriends = new ArrayList<Friend>();
	private int height;// ����߶�
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
	private TextView overlay; // �Ի�������ĸtextview
	private MyLetterListView letterListView; // A-Z listview
	private Handler overlayHandler;
	private OverlayThread overlayThread; // ��ʾ����ĸ�Ի���
	private HashMap<String, Integer> selector;// ��ź���������ĸ��λ��
	private ArrayList<Friend> allFriends; // ͨѶ¼�б��������˱ʼǱ�����+�����б�
	private ArrayList<Friend> friends;// �����б�
	private List<Friend> newFriends;// �����ĺ����б�
	private ArrayList<Group> groups;// ���˱ʼǱ��б�
	private LocationClient mLocationClient;
	private MyLocationListener mMyLocationListener;

	private String currentCity; // ���ڱ��涨λ���ĳ���
	private int locateProcess = 1; // ��¼��ǰ��λ��״̬ ���ڶ�λ-��λ�ɹ�-��λʧ��
	private boolean isNeedFresh;
	private DatabaseHelper helper;
	private int isrefresh = 0;
	private static String path="/sdcard/myHead/";
	private Bitmap bt ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// ����activityʱ���Զ����������
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

	// ��ȡ�ײ��˵����ĸ߶�
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		bottom_height = bottom.getHeight();
	}

	// ҳ�����ݳ�ʼ��
	public void init() {
		vciew=getLayoutInflater().from(this).inflate(R.layout.myheadr,  null );
		 head_image=(ImageView) vciew.findViewById(R.id.note_bg);
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		// ��ȡͼƬ
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

		// ��ȡ�ײ�����
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
		// ��ȡ������ǩ
		mTab1 = (LinearLayout) findViewById(R.id.bottom_note);
		mTab2 = (LinearLayout) findViewById(R.id.bottom_find);
		mTab3 = (LinearLayout) findViewById(R.id.bottom_contact);
		mTab4 = (LinearLayout) findViewById(R.id.bottom_me);

		// ������ǩ��Ӽ����¼�
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		mTab4.setOnClickListener(new MyOnClickListener(3));

		// ��Ҫ��ҳ��ʾ��Viewװ��������
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.tab_note, null);
		View view2 = mLi.inflate(R.layout.tab_find, null);
		View view3 = mLi.inflate(R.layout.tab_contact, null);
		View view4 = mLi.inflate(R.layout.tab_me, null);
		// ÿ��ҳ���view����
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);

		// ���ViewPager������������
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

			// �ڴ˴�ʵ������ҳ���еĶ���
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				// 2015-11-11 Ϊ��һ��ҳ��ʱ����xlistview������
				if (position == 0) {
					// TODO ҳ�����
					do_BottomList(container);
				}
				// 2015-11-17 Ϊ��3��ҳ��ʱ����xlistview������
				if (position == 2) {
					if (isrefresh == 0) {
						do_listview_friends(container);
						isrefresh++;
					}
				}
				// 11-18 Ϊ���ĸ�ҳ������Բ��ͷ��
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

	// �ײ��˵���Ӽ���
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

	// ������ǩҳ�л����������õײ�����Ч��
	public class MyOnPageChangeListener implements OnPageChangeListener {
		final int txt_pressed = Main.this.getResources().getColor(
				R.color.main_txt_pressed);
		final int txt_normal = Main.this.getResources().getColor(
				R.color.main_txt_normal);

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			// ����ѡ��ҳ��ͼ��Ϊpressed
			mImgs.get(arg0).setImageResource(imgs_pressed[arg0]);
			mTxts.get(arg0).setTextColor(txt_pressed);
			// �ƶ��Ķ���
			animation = new TranslateAnimation(currIndex * one, arg0 * one, 0,
					0);
			// ѡ��֮ǰ�ĵ�ǰҳ��ͼ���޸�Ϊnormal
			mImgs.get(currIndex).setImageResource(imgs_normal[currIndex]);
			mTxts.get(currIndex).setTextColor(txt_normal);

			currIndex = arg0;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
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
	 * ����ǩҳ���漰����һЩҳ����ת(��Ҫ�������ϵĹ��ܲ���)
	 * 
	 * @param
	 */

	// ҳ��1--���д�ռǵ����Զ����popmenu
	
	public void to_write_note(View v) {
		tv = (TextView) this.findViewById(R.id.tab_wtrie_note);
		// �����ֿؼ���Ӽ�������������Զ��崰��
		tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ʵ����SelectPicPopupWindow
				
				// ��ʾ����λ��
				menuWindow.showAtLocation(Main.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
						bottom_height); // ����layout��PopupWindow����ʾ��λ��
				ColorDrawable cd = new ColorDrawable(0x000000);
				menuWindow.setBackgroundDrawable(cd); 
			    lp=getWindow().getAttributes();
				lp.alpha = 0.4f;
				getWindow().setAttributes(lp);
				
			}
		});
	}

	
	// Ϊ������popmenuʵ�ּ�����
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
				Toast.makeText(Main.this, "�ı�", 2500).show();
				intent = new Intent(Main.this, Note_write_txt.class);
				startActivity(intent);
				break;
			case R.id.note_weilaixin:
				Toast.makeText(Main.this, "δ����", 2500).show();
				break;
			case R.id.note_short_aduio:
				Toast.makeText(Main.this, "����Ƶ", 2500).show();
				Intent intent22 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION); 
				judge=true;
				startActivityForResult(intent22,4); 
			
				break;
			case R.id.note_from_phone:
            //2015.12.3û�е���ϵͳ�������ת���Լ���������
				Intent intent2=new  Intent(Main.this, ImgFileListActivity.class);
				startActivity(intent2);

				
			
				break;
				
			default:
				break;  
			}
		}
		
	};
	
	   

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO ������������Ľ��������
	
		   switch (requestCode) {
//		   2015.12.2����ɨ��
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
						
						setPicToView(bt);//������SD����
						head_image.setImageBitmap(bt);//��ImageView��ʾ����
				
					}
				}
				break;//���������ͼƬ����Photo_pulish
		
			case 4:
			//	2015.12.2	����¼����
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
	

		
		
        
    


	// ҳ��1--���ײ���listview�������
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


		// 2015-11-13 �ڲ�item�������
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
				Toast.makeText(Main.this, "����˵�" + arg2 + "����Ŀ", 2500).show();
		
			}
		   });
		// Utility.setListViewHeightBasedOnChildren(xlist);

	       }

	// ҳ��1--�������ͼ������
	@SuppressLint("NewApi") public void change_bg(View v) {
	
		  AlertDialog.Builder ab = new AlertDialog.Builder(this);
		  ab.setMessage("�Ƿ��޸ı���ͼƬ��")
				.setPositiveButton("��", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {

						Intent intent1 = new Intent(Intent.ACTION_PICK, null);
						intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
						startActivityForResult(intent1, 1);
	                
						
						
						
						
						// ��ת�����ѡ��ҳ�� TODO
						Toast.makeText(Main.this, "�޸ı���ͼƬ�ɹ���", 2500).show();
					}
		 }).setNegativeButton("��", null).show();

		
	}

	// ҳ��2--ɨһɨ��ά�� 
	    public void scan_code(View v) {
//	    	2015.12.2
		Toast.makeText(Main.this, "ɨ���ά��", 2500).show();
		Intent openCameraIntent = new Intent(Main.this,CaptureActivity.class);
		startActivityForResult(openCameraIntent, 0);
	
	}

	// ҳ��2--��Ʒ����
	   public void make_gift(View v) {
		Toast.makeText(Main.this, "������Ʒ������", 2500).show();
	}

	// ҳ��3--��ʾ�б����
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
		// �Ҳ���ĸ��������
		letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		locateProcess = 1;
		// contactList.setAdapter(adapter);
		contactList.setOnScrollListener(this);
		// ��ʼ����ĸ������ʾ��ģ��������ݣ����˱ʼǱ�����
		initOverlay();
		// ��������հ��������ͨѶ¼Adapter��ǰ����
		Friend f1 = new Friend();
		allFriends.add(f1);
		
		Friend f2 = new Friend();
		allFriends.add(f2);
		newFriends = InitData.friendsInit();
		allFriends.addAll(newFriends);
		groups = InitData.groupsInit();
		// ѭ����ĸ���ҳ�newFriends�ж�Ӧ��ĸ��λ��
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
	 * ���������б�
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
					if (index > -1 && index < indexStr.length) {// ��ֹԽ��
						String key = indexStr[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listview_friends.getHeaderViewsCount() > 0) {// ��ֹListView�б�������������û�С�
								listview_friends.setSelectionFromTop(
										pos
												+ listview_friends
														.getHeaderViewsCount(),
										0);
							} else {
								listview_friends.setSelectionFromTop(pos, 0);// ��������һ��
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

	// ҳ��3--��ת����������ҳ��
	public void to_searchFriends(View v) {
		Intent intent = new Intent(Main.this, Contact_Friend_Search.class);
		startActivity(intent);
	}

	// ҳ��3--��ת���½����˱ʼǱ���ҳ��
	public void to_addGroup(View v) {
		Intent intent = new Intent(Main.this, Contact_Group_Add.class);
		startActivity(intent);
	}

	// ҳ��3--��ת�����˱ʼǱ��༭ҳ��
	public void to_groupEdit(View v) {
		Intent intent = new Intent(Main.this, Contact_Group_Edit.class);
		startActivity(intent);
	}

	// ҳ��3--�������ת����������ҳ��
	public void to_friendInfo(View v) {
		Intent intent = new Intent(Main.this, Contact_Friend_Info.class);
		startActivity(intent);
	}

	// ҳ��4--��ͷ������ת��������Ϣҳ��
	public void to_userinfo(View v) {
		Intent intent = new Intent(Main.this, Me_UserInfo.class);
		startActivity(intent);
	}

	// ҳ��4--�㽨������ת������ҳ��
	public void to_advice(View v) {
		Intent intent = new Intent(Main.this, Me_Advice.class);
		startActivity(intent);
	}

	// ҳ��4--��δ��������ת���ҵ�δ����ҳ��
	public void to_Myweilaixin(View v) {
		Toast.makeText(Main.this, "�ҵ�δ����", 2500).show();
		Intent intent = new Intent(Main.this, Me_Myweilaixin.class);
		startActivity(intent);
	}

	// ҳ��4--����������ת������ҳ��
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
				Toast.makeText(Main.this, "����ִ��", 1000).show();
				onLoad();
				
			}
		}, 1000);
	         

	               
	        
		
	}

	private void onLoad() {
		xlist.stopRefresh();
		xlist.stopLoadMore();
		xlist.setRefreshTime("�ո�");
	}

	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onRestart() {
		System.out.println("��ǰҳ���±꣺" + currIndex);
		if (currIndex == 2) {
			adapter.notifyDataSetChanged();
		}
		

		super.onRestart();
	}

	// �������ڿ��ƶ�λ TODO
	protected void onStop() {
		// mLocationClient.stop();
		System.out.println("---stop---");
		super.onStop();
	}

	private boolean mReady;

	// ��ʼ������ƴ������ĸ������ʾ��
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
				// �ӳ�һ���ִ�У���overlayΪ���ɼ�
				overlayHandler.postDelayed(overlayThread, 1000);
			}
		}
	}
	
	private void InitLocation() {
		// ���ö�λ����
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(10000); // 10����ɨ��1��
		// ��Ҫ��ַ��Ϣ������Ϊ�����κ�ֵ��string���ͣ��Ҳ���Ϊnull��ʱ������ʾ�޵�ַ��Ϣ��
		option.setAddrType("all");
		// �����Ƿ񷵻�POI�ĵ绰�͵�ַ����ϸ��Ϣ��Ĭ��ֵΪfalse����������POI�ĵ绰�͵�ַ��Ϣ��
		option.setPoiExtraInfo(true);
		// ���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����
		option.setProdName("ͨ��GPS��λ�ҵ�ǰ��λ��");
		// �������û��涨λ����
		option.disableCache(true);
		// �������ɷ��ص�POI������Ĭ��ֵΪ3������POI��ѯ�ȽϺķ�������������෵�ص�POI�������Ա��ʡ������
		option.setPoiNumber(3);
		// ���ö�λ��ʽ�����ȼ���
		// ��gps���ã����һ�ȡ�˶�λ���ʱ�����ٷ�����������ֱ�ӷ��ظ��û����ꡣ���ѡ���ʺ�ϣ���õ�׼ȷ����λ�õ��û������gps�����ã��ٷ����������󣬽��ж�λ��
		option.setPriority(LocationClientOption.GpsFirst);
		mLocationClient.setLocOption(option);
	}

	/**
	 * ʵ��ʵλ�ص�����
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
				locateProcess = 3; // ��λʧ��
				contactList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				return;
			}
			currentCity = arg0.getCity().substring(0,
					arg0.getCity().length() - 1);
			locateProcess = 2; // ��λ�ɹ�
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
				// ��Ժ����б����õ��������������ת
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
//����ͼƬ��2015.11.28
	private void setPicToView(Bitmap mBitmap) {
		 String sdStatus = Environment.getExternalStorageState();  
       if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����  
              return;  
          }  
		FileOutputStream b = null;
		File file = new File(path);
		file.mkdirs();// �����ļ���
		String fileName =path + "head.jpg";//ͼƬ����
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//�ر���
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
//	�ü�ͼƬ��2015.11.28
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		 // aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

}
