package com.codepath.apps.Balthazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.Balthazar.models.Tweet;

import java.util.Arrays;
import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    //  pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);

    }

    //  bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the data at position
        Tweet tweet = tweets.get(position);
        // bind the tweet with the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {return tweets.size(); }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    //  define a view holder
    public  class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvSymbol;
        TextView tvClock;
        TextView tvuserName;
        ImageView media_url;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvClock = itemView.findViewById(R.id.tvClock);
            tvuserName = itemView.findViewById(R.id.tvuserName);
            media_url = itemView.findViewById(R.id.media);
            tvSymbol = itemView.findViewById(R.id.Tag);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvuserName.setText(tweet.user.name);
            tvScreenName.setText(tweet.user.screenName);
            tvClock.setText(tweet.getFormattedTimestamp());
            media_url.setVisibility(View.GONE);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);

            try {
                List<String> ms = tweet.medias;
                if (!ms.isEmpty()) {
                    List<String> m = Arrays.asList(ms.get(0).split(" - "));
                    if (m.get(1).equals("photo")) {

                        media_url.setVisibility(View.VISIBLE);
                        Glide.with(context).load(m.get(0)).transform(new FitCenter(), new RoundedCorners(12)).into(media_url);
                    }
                }
            } catch (Exception e) {}


            }
            }
        }
