/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.dev_station.alarm;

import java.util.ArrayList;
import java.util.List;

import com.dev_station.alarm.database.Database;
import com.dev_station.alarm.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class AlarmListAdapter extends BaseAdapter {

	private AlarmActivity alarmActivity;
	private List<Alarm> alarms = new ArrayList<Alarm>();

	public static final String ALARM_FIELDS[] = { Database.COLUMN_ALARM_ACTIVE,
			Database.COLUMN_ALARM_TIME, Database.COLUMN_ALARM_DAYS };

	public AlarmListAdapter(AlarmActivity alarmActivity) {
		this.alarmActivity = alarmActivity;
//		Database.init(alarmActivity);
//		alarms = Database.getAll();
	}

	@Override
	public int getCount() {
		return alarms.size();
	}

	@Override
	public Object getItem(int position) {
		return alarms.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		if (null == view)
			view = LayoutInflater.from(alarmActivity).inflate(
					R.layout.alarm_list_element, null);

		Alarm alarm = (Alarm) getItem(position);

		 CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_alarm_active);
		 checkBox.setChecked(alarm.getAlarmActive());
		 checkBox.setTag(position);
		 checkBox.setOnClickListener(alarmActivity);
		 
		TextView alarmTimeView = (TextView) view
				.findViewById(R.id.textView_alarm_time);
		alarmTimeView.setText(alarm.getAlarmTimeString());

		
			TextView alarmDaysView = (TextView) view
					.findViewById(R.id.textView_alarm_days);
			alarmDaysView.setText(alarm.getRepeatDaysString());

		TextView alarmNameView = (TextView) view
				.findViewById(R.id.textView_alarm_name);
		alarmNameView.setText(alarm.getAlarmName());
		/*Button buttonAlarmName = (Button) view
				.findViewById(R.id.btnAlarmName);
		buttonAlarmName.setText(alarm.getAlarmName());*/
		return view;
	}

	public List<Alarm> getMathAlarms() {
		return alarms;
	}

	public void setMathAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

}
