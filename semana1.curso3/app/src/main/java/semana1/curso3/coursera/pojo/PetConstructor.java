package semana1.curso3.coursera.pojo;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import semana1.curso3.coursera.R;
import semana1.curso3.coursera.database.Constants;
import semana1.curso3.coursera.database.Database;

public class PetConstructor {

    private Context context;

    public PetConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getPets() {
        Database database = new Database(context);
        ArrayList<Pet> pets = database.getPets();
        if (pets.size() > 0) {
            return pets;
        }
        else {
            return insertPets(database);
        }
    }

    private ArrayList<Pet> insertPets(Database database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_one);
        contentValues.put(Constants.TABLE_PET_NAME, "Ruffo");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_two);
        contentValues.put(Constants.TABLE_PET_NAME, "Noli");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_three);
        contentValues.put(Constants.TABLE_PET_NAME, "Roy");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_four);
        contentValues.put(Constants.TABLE_PET_NAME, "Lazy");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_five);
        contentValues.put(Constants.TABLE_PET_NAME, "Nino");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_six);
        contentValues.put(Constants.TABLE_PET_NAME, "Cobo");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_seven);
        contentValues.put(Constants.TABLE_PET_NAME, "Dino");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_PHOTO, R.drawable.pet_eight);
        contentValues.put(Constants.TABLE_PET_NAME, "Chumi");
        contentValues.put(Constants.TABLE_PET_RATING, 0);
        database.insertPet(contentValues);

        return database.getPets();
    }

    public long updatePetRating(Pet pet) {
        Database database = new Database(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PET_RATING, pet.getRating());
        return database.updatePetRating(contentValues ,pet);
    }

    public ArrayList<Pet> getFiveFavoritePets() {
        Database database = new Database(context);
        return database.getFiveFavoritePets();
    }

}