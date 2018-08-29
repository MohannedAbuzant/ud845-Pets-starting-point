package com.example.android.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;


public final class PetsContract {
    private PetsContract() {}
    public static final String PATH_PETS = "pets";

    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static abstract class  petsEntry implements BaseColumns{
        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_Id =BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLMUN_GENDER = "gender";
        public static final String COLMUN_WEIGHT = "weight";
        public static final int gender_Unknown = 0;
        public static final int gender_Male=1;
        public static final int getGender_Female=2;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);



    }
}
