package com.fr4gus.android.oammblo.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fr4gus.android.oammblo.R;
import com.fr4gus.android.oammblo.bo.Tweet;
import com.fr4gus.android.oammblo.services.TwitterService;
import com.fr4gus.android.oammblo.services.TwitterServiceFactory;

public class TimeLineActivity extends Activity {

	public static enum TweetType {
		TWEET, REPLY
	}

	ListView mTimeLine;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		mTimeLine = (ListView) findViewById(R.id.time_line_list_view);
		mTimeLine.setAdapter(new TimeLineAdapter(this, new ArrayList<Tweet>()));

		AsyncTask<Void, Void, List<Tweet>> task = new AsyncTask<Void, Void, List<Tweet>>() {

			@Override
			protected List<Tweet> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				TwitterService service = TwitterServiceFactory.getService();
				List<Tweet> tweets = service.getTimeLine();
				return tweets;
			}

			@Override
			protected void onPostExecute(List<Tweet> result) {
				// TODO Auto-generated method stub
				TimeLineAdapter adapter = (TimeLineAdapter) mTimeLine
						.getAdapter();
				adapter.addList(result);
			}

		}.execute();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Debug.startMethodTracing("viewholder_trace");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Debug.stopMethodTracing();
	}

	private static class TimeLineAdapter extends BaseAdapter {
		List<Tweet> tweets;
		LayoutInflater inflater;

		public TimeLineAdapter(Context context, List<Tweet> tweets) {
			this.tweets = tweets;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addList(List<Tweet> tweets) {
			this.tweets.addAll(tweets);
			notifyDataSetChanged();
		}

		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			Tweet tweet = (Tweet) getItem(position);
			if (tweet.isReply()) {
				return TweetType.REPLY.ordinal();
			}

			return TweetType.TWEET.ordinal();
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return TweetType.values().length;
		}

		public int getCount() {
			return tweets.size();
		}

		public Object getItem(int position) {
			return tweets.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			System.out.println("Posicion "+position);
			Tweet tweet = (Tweet) getItem(position);
			System.out.println("Tweet "+tweet.getContent()+" "+tweet.getAuthor().getDisplayName());
			TweetViewHolder holder = null;
			if (convertView == null) {
				if (tweet.isReply()) {
					convertView = inflater.inflate(
							R.layout.list_item_tweet_reply, parent, false);
				} else {
					convertView = inflater.inflate(R.layout.list_item_tweet,
							parent, false);
				}
				holder = new TweetViewHolder();
				convertView.setTag(holder);
				holder.authorName = (TextView) convertView.findViewById(R.id.list_item_tweet_displayname);
				holder.content = (TextView) convertView.findViewById(R.id.list_item_tweet_content);
			} else {
				holder = (TweetViewHolder) convertView.getTag();
			}
			holder.authorName.setText(tweet.getAuthor().getDisplayName());
			holder.content.setText(tweet.getContent());

			return convertView;
		}
	}

	private static class TweetViewHolder {
		public TextView authorName;
		public TextView content;
		public ImageView photo;
		public TextView date;
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.activity_time_line, menu);
	// return true;
	// }
}
