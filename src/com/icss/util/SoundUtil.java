package com.icss.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.icss.R;

public class SoundUtil {
	private static SoundPool mSoundPool = new SoundPool(10,
			AudioManager.STREAM_MUSIC, 0);
	private static SoundUtil soundUtil = null;

	private SoundUtil() {
	}

	public static SoundUtil init(Context context) {
		if (soundUtil == null) {
			soundUtil = new SoundUtil();
		}
		mSoundPool.load(context, R.raw.messageok, 1);
		mSoundPool.load(context, R.raw.messageerr, 1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return soundUtil;
	}

	public void paly(int soundId) {
		mSoundPool.play(soundId, 1, 1, 0, 0, 1);

	}
}
