package com.source.util;

import android.os.Handler;

import java.util.*;

/**
 * Created by SlothMonkey on 14-2-24.
 */
public class TimerTaskFactory {

    private static long DefaultTaskStopTime = 2000;

    public static final int TaskType_StudentChat = 1;
    public static final int TaskType_TeacherChat = 2;
    public static final int TASK_TYPE_SCROLL_TO = 3;
    public static final int TASK_TYPE_Hide_TitleBottomView = 4;

    private Map<String, List<Timer>> chatReqMap = new HashMap<String, List<Timer>>();


    public void buildTimer(int taskType, Handler.Callback callback) {
        cleanTimer(taskType);
        startTimer(taskType, DefaultTaskStopTime, callback);
    }

    public void buildTimer(int taskType, long taskStopTime, Handler.Callback callback) {
        cleanTimer(taskType);
        startTimer(taskType, taskStopTime, callback);
    }


    private void startTimer(final int taskType, final long taskStopTime, final Handler.Callback callback) {

        LogUtil.d("----Timer Start! TaskType:" + taskType);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callback.handleMessage(null);
            }
        };

        timer.schedule(timerTask, taskStopTime);

        String key = String.valueOf(taskType);
        List<Timer> timerList = chatReqMap.get(key);
        if (timerList == null) {
            timerList = new ArrayList<Timer>();
            chatReqMap.put(key, timerList);
        }
        timerList.add(timer);

    }

    public void cleanTimer(int taskType) {

        List<Timer> timerList = chatReqMap.get(String.valueOf(taskType));
        LogUtil.d("----Timer cancel! TaskType:" + taskType + "--List:" + timerList);

        if (timerList == null) {
            return;
        }
        for (Timer timer : timerList) {
            if (timer != null) {
                timer.cancel();
            }
        }
        chatReqMap.put(String.valueOf(taskType), new ArrayList<Timer>());
    }


}
