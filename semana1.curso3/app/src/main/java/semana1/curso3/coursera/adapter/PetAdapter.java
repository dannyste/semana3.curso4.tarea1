package semana1.curso3.coursera.adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import semana1.curso3.coursera.R;
import semana1.curso3.coursera.pojo.Pet;
import semana1.curso3.coursera.pojo.PetConstructor;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private Activity activity;
    private ArrayList<Pet> pets;
    private PetConstructor petConstructor;

    public PetAdapter(Activity activity, ArrayList<Pet> pets) {
        this.activity = activity;
        this.pets = pets;
        this.petConstructor = new PetConstructor(activity);
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder petViewHolder, int position) {
        final Pet pet = pets.get(position);
        petViewHolder.iv_photo.setImageResource(pet.getPhoto());
        petViewHolder.iv_bone_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet.setRating(pet.getRating() + 1);
                if (petConstructor.updatePetRating(pet) > 0) {
                    petViewHolder.tv_rating.setText(String.valueOf(pet.getRating()));
                }
                else {
                    pet.setRating(pet.getRating() - 1);
                    Snackbar.make(view, "Ocurrió un error", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        petViewHolder.tv_name.setText(pet.getName());
        petViewHolder.tv_rating.setText(String.valueOf(pet.getRating()));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    static class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_photo;
        private ImageView iv_bone_white;
        private TextView tv_name;
        private TextView tv_rating;

        PetViewHolder(View view) {
            super(view);
            iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
            iv_bone_white = (ImageView) view.findViewById(R.id.iv_bone_white);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_rating = (TextView) view.findViewById(R.id.tv_rating);
        }

    }

}