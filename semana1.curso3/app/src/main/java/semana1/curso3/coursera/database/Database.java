package semana1.curso3.coursera.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import semana1.curso3.coursera.pojo.Pet;

public class Database extends SQLiteOpenHelper {

    private Context context;

    public Database(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Constants.TABLE_PET + "(" +
                                        Constants.TABLE_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        Constants.TABLE_PET_PHOTO + " INTEGER, " +
                                        Constants.TABLE_PET_NAME + " TEXT, " +
                                        Constants.TABLE_PET_RATING + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PET);
        onCreate(sqLiteDatabase);
    }


    public ArrayList<Pet> getPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TABLE_PET;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(cursor.getInt(0));
            pet.setPhoto(cursor.getInt(1));
            pet.setName(cursor.getString(2));
            pet.setRating(cursor.getInt(3));
            pets.add(pet);
        }
        sqLiteDatabase.close();
        return pets;
    }

    public long insertPet(ContentValues contentValues) {
        long result;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        result = sqLiteDatabase.insert(Constants.TABLE_PET,null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updatePetRating(ContentValues contentValues, Pet pet) {
        long result;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        result = sqLiteDatabase.update(Constants.TABLE_PET, contentValues, Constants.TABLE_PET_ID + " = ?", new String[] { String.valueOf(pet.getId()) });
        sqLiteDatabase.close();
        return result;
    }

    public ArrayList<Pet> getFiveFavoritePets() {
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TABLE_PET + " ORDER BY " + Constants.TABLE_PET_RATING + " DESC LIMIT 5";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(cursor.getInt(0));
            pet.setPhoto(cursor.getInt(1));
            pet.setName(cursor.getString(2));
            pet.setRating(cursor.getInt(3));
            pets.add(pet);
        }
        sqLiteDatabase.close();
        return pets;
    }

}