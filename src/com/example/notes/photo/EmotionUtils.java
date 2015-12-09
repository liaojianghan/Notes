package com.example.notes.photo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.example.notes.R;



import android.content.Intent;

public class EmotionUtils implements Serializable{
    
	public static  Map<String, Integer> emojiMap;
	
	static{
		emojiMap=new HashMap<String,Integer>();
		emojiMap.put("[�Ǻ�]", R.drawable.d_hehe);
		emojiMap.put("[����]", R.drawable.d_xixi);
		emojiMap.put("[����]", R.drawable.d_haha);
		emojiMap.put("[����]", R.drawable.d_aini);
		emojiMap.put("[�ڱ�ʺ]", R.drawable.d_wabishi);
		emojiMap.put("[�Ծ�]", R.drawable.d_chijing);
		emojiMap.put("[��]", R.drawable.d_yun);
		emojiMap.put("[��]", R.drawable.d_lei);
		emojiMap.put("[����]", R.drawable.d_chanzui);
		emojiMap.put("[ץ��]", R.drawable.d_zhuakuang);
		emojiMap.put("[��]", R.drawable.d_heng);
		emojiMap.put("[�ɰ�]", R.drawable.d_keai);
		emojiMap.put("[ŭ]", R.drawable.d_nu);
		emojiMap.put("[��]", R.drawable.d_han);
		emojiMap.put("[����]", R.drawable.d_haixiu);
		emojiMap.put("[˯��]", R.drawable.d_shuijiao);
		emojiMap.put("[Ǯ]", R.drawable.d_qian);
		emojiMap.put("[͵Ц]", R.drawable.d_touxiao);
		emojiMap.put("[Цcry]", R.drawable.d_xiaoku);
		emojiMap.put("[doge]", R.drawable.d_doge);
		emojiMap.put("[����]", R.drawable.d_miao);
		emojiMap.put("[��]", R.drawable.d_ku);
		emojiMap.put("[˥]", R.drawable.d_shuai);
		emojiMap.put("[����]", R.drawable.d_bizui);
		emojiMap.put("[����]", R.drawable.d_bishi);
		emojiMap.put("[����]", R.drawable.d_huaxin);
		emojiMap.put("[����]", R.drawable.d_guzhang);
		emojiMap.put("[����]", R.drawable.d_beishang);
		emojiMap.put("[˼��]", R.drawable.d_sikao);
		emojiMap.put("[����]", R.drawable.d_shengbing);
		emojiMap.put("[����]", R.drawable.d_qinqin);
		emojiMap.put("[ŭ��]", R.drawable.d_numa);
		emojiMap.put("[̫����]", R.drawable.d_taikaixin);
		emojiMap.put("[��������]", R.drawable.d_landelini);
		emojiMap.put("[�Һߺ�]", R.drawable.d_youhengheng);
		emojiMap.put("[��ߺ�]", R.drawable.d_zuohengheng);
		emojiMap.put("[��]", R.drawable.d_xu);
		emojiMap.put("[ί��]", R.drawable.d_weiqu);
		emojiMap.put("[��]", R.drawable.d_tu);
		emojiMap.put("[����]", R.drawable.d_kelian);
		emojiMap.put("[�����]", R.drawable.d_dahaqi);
		emojiMap.put("[����]", R.drawable.d_jiyan);
		emojiMap.put("[ʧ��]", R.drawable.d_shiwang);
		emojiMap.put("[��]", R.drawable.d_ding);
		emojiMap.put("[����]", R.drawable.d_yiwen);
		emojiMap.put("[��]", R.drawable.d_kun);
		emojiMap.put("[��ð]", R.drawable.d_ganmao);
		emojiMap.put("[�ݰ�]", R.drawable.d_baibai);
		emojiMap.put("[����]", R.drawable.d_heixian);
		emojiMap.put("[����]", R.drawable.d_yinxian);
		emojiMap.put("[����]", R.drawable.d_dalian);
		emojiMap.put("[ɵ��]", R.drawable.d_shayan);
		emojiMap.put("[��ͷ]", R.drawable.d_zhutou);
		emojiMap.put("[��è]", R.drawable.d_xiongmao);
		emojiMap.put("[����]", R.drawable.d_tuzi);
	}
	public static int getImgByName(String imgName) {
		Integer integer = emojiMap.get(imgName);
		return integer == null ? -1 : integer;
	}
}	
		
		
		
		

