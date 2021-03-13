package android.example.communication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Words("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Words("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Words("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Words("I’m feeling good.", "kuchi achit" , R.raw.phrase_im_feeling_good));
        words.add(new Words("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Words("Yes, I’m coming.", "hәә’ әәnәm" ,R.raw.phrase_yes_im_coming ));
        words.add(new Words("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Words("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Words("Come here.", "әnni'nem",R.raw.phrase_come_here));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words , R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            //checking if another sound is playing then the resources should release//
            releaseMediaPlayer();
            // get position of current word//
            Words word = words.get(position);
            // giving input to media player//
            mMediaPlayer =   MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
            // starting the media palyer//
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