package semana1.curso3.coursera.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import semana1.curso3.coursera.R;
import semana1.curso3.coursera.pojo.PetProfile;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetProfileViewHolder> {

    private Activity activity;
    private ArrayList<PetProfile> petProfiles;

    public PetProfileAdapter(Activity activity, ArrayList<PetProfile> petProfiles) {
        this.activity = activity;
        this.petProfiles = petProfiles;
    }

    @Override
    public PetProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet_profile, parent, false);
        return new PetProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetProfileViewHolder petProfileViewHolder, int position) {
        final PetProfile petProfile = petProfiles.get(position);
        Picasso.with(activity).load(petProfile.getPhoto()).placeholder(R.drawable.image_not_found).into(petProfileViewHolder.iv_photo);
        petProfileViewHolder.tv_rating.setText(String.valueOf(petProfile.getRating()));
    }

    @Override
    public int getItemCount() {
        return petProfiles.size();
    }

    static class PetProfileViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_photo;
        private TextView tv_rating;

        PetProfileViewHolder(View view) {
            super(view);
            iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
            tv_rating = (TextView) view.findViewById(R.id.tv_rating);
        }

    }

}