package android.example.communication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers2);


        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("red", "weṭeṭṭi",R.drawable.color_red ,R.raw.color_red));
        words.add(new Words("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow ,R.raw.color_mustard_yellow));
        words.add(new Words("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow , R.raw.color_dusty_yellow));
        words.add(new Words("green", "chokokki",R.drawable.color_green , R.raw.color_green));
        words.add(new Words("brown", "ṭakaakki",R.drawable.color_brown ,R.raw.color_brown));
        words.add(new Words("gray", "ṭopoppi",R.drawable.color_gray , R.raw.color_gray));
        words.add(new Words("black", "kululli",R.drawable.color_black , R.raw.color_black));
        words.add(new Words("white", "kelelli",R.drawable.color_white , R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words ,  R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
   releaseMediaPlayer();
                Words word = words.get(position);
                mMediaPlayer =   MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    protected void onPause() {

        super.onPause();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

}
