package com.example.firebasecrud;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewAdapter extends FirebaseRecyclerAdapter<ProductData, ViewAdapter.RecycleHolder> {

    public ViewAdapter(FirebaseRecyclerOptions<ProductData> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull RecycleHolder holder, int position, @NonNull ProductData model) {
        holder.txtName.setText(model.getPname());
        holder.txtPrice.setText(model.getPprice());
        holder.txtDes.setText(model.getPdes());
        Log.d("MMM", "onBindViewHolder: data="+model.getPname());
        Glide
                .with(holder.imageView.getContext())
                .load(model.getPimg())
                .centerCrop()
                .into(holder.imageView);

        holder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(view.getContext(), holder.menu);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item)
                        {
                            int position = holder.getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION)
                            {
                                if (item.getItemId() == R.id.update)
                                {
                                    Toast.makeText(view.getContext(), "Update", Toast.LENGTH_SHORT).show();

                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                    Query applesQuery = ref.child("Products").orderByChild("id").equalTo(model.getId());
                                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                                                FirebaseDatabase database = FirebaseDatabase.getInstance(); // initializing object of database
                                                DatabaseReference myRef = database.getReference("Products").push(); // Creating main (parent) reference
                                                String id = myRef.getKey();

                                                ProductData dataModel = new ProductData(id,"CPU","12345","RRR","https://play-lh.googleusercontent.com/8W4Ai-J22SM8Xzwy75_wXqnYPm04zbc0QpXm6dfKiS-VNDgPjeS3rtu_yymuGWmGP4w=s48-rw");

                                                appleSnapshot.getRef().setValue(dataModel);
                                                //appleSnapshot.getRef().removeValue();
                                                notifyDataSetChanged();
                                            }
                                        }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                    notifyDataSetChanged();
                                }
                                else if (item.getItemId() == R.id.delete)
                                {
                                    Toast.makeText(view.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                    Query applesQuery = ref.child("Products").orderByChild("id").equalTo(model.getId());
                                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                                                FirebaseDatabase database = FirebaseDatabase.getInstance(); // initializing object of database
                                                DatabaseReference myRef = database.getReference("Products").push(); // Creating main (parent) reference
                                                String id = myRef.getKey();

                                                //ProductData dataModel = new ProductData(id,"CPU","12345","RRR","https://play-lh.googleusercontent.com/8W4Ai-J22SM8Xzwy75_wXqnYPm04zbc0QpXm6dfKiS-VNDgPjeS3rtu_yymuGWmGP4w=s48-rw");

                                                //appleSnapshot.getRef().setValue(dataModel);
                                                appleSnapshot.getRef().removeValue();
                                                notifyDataSetChanged();
                                            }
                                        }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_file,parent,false);
        RecycleHolder holder=new RecycleHolder(view);
        return holder;
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtDes;
        ImageView imageView,menu;

        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
                txtName=itemView.findViewById(R.id.vp_name);
                txtPrice=itemView.findViewById(R.id.vp_price);
                txtDes=itemView.findViewById(R.id.vp_des);
                imageView=itemView.findViewById(R.id.vp_img);
                menu=itemView.findViewById(R.id.menu);

        }
    }
}

//        @NonNull
//        @Override
//        public ViewAdapter.RecycleHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType)
//        {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_file, parent, false);
//            return new RecycleHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder (@NonNull ViewAdapter.RecycleHolder holder,int position)
//        {
//            View_Productdata item = productdata.get(position);
//            holder.t1.setText(item.getPname());
//            holder.t2.setText(String.valueOf(item.getPprice()));
//            holder.t3.setText(item.getPdes());
//
//        }
//    }

//    public class RecycleHolder extends RecyclerView.ViewHolder
//    {
//        TextView t1, t2, t3;
//        ImageView img;
//        ImageView menu;
//        public RecycleHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            menu = itemView.findViewById(R.id.menu);
//            t1 = itemView.findViewById(R.id.vp_name);
//            t2 = itemView.findViewById(R.id.vp_price);
//            t3 = itemView.findViewById(R.id.vp_des);
//            img = itemView.findViewById(R.id.vp_img);
//
//            menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    PopupMenu popupMenu = new PopupMenu(view.getContext(), menu);
//                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item)
//                        {
//                            int position = getAdapterPosition();
//                            if (position != RecyclerView.NO_POSITION)
//                            {
//                                if (item.getItemId() == R.id.update)
//                                {
//                                    Intent intent = new Intent(view.getContext(), Add_Product.class);
//                                    intent.putExtra("pos", productdata.get(position).getId());
//                                    intent.putExtra("puid", productdata.get(position).getUid());
//                                    intent.putExtra("pname", productdata.get(position).getPname());
//                                    intent.putExtra("pPrice", productdata.get(position).getPprice());
//                                    intent.putExtra("pDes", productdata.get(position).getPdes());
//
//                                    notifyDataSetChanged();
//                                }
//                                else if (item.getItemId() == R.id.delete)
//                                {
//
//                                }
//                            }
//                            return true;
//                        }
//                    });
//                    popupMenu.show();
//                }
//            });
//        }
//    }

