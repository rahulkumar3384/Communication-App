package android.example.communication_app;

public class Words {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    private int mAudioResourceId;
    /** Image Resource id*/
    private int mImageResourceId = N0_IMAGE_PROVIDED;
    private static final int N0_IMAGE_PROVIDED = -1;
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Words(String defaultTranslation, String miwokTranslation , int audioResourceId ) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId  = audioResourceId;

    }
    public Words(String defaultTranslation, String miwokTranslation , int resourceId , int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = resourceId;
        mAudioResourceId  = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId(){return mImageResourceId;}

    public boolean hasImage(){
        return mImageResourceId != N0_IMAGE_PROVIDED;
    }


    public int getAudioResourceId() {return mAudioResourceId;
    }


}