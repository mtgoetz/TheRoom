package edu.pitt.cs.cs1635.group4.roomhub;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;
import android.net.Uri;

import static android.app.Activity.RESULT_OK;

public class ProfileUpdate extends android.support.v4.app.Fragment {

    RoomHubApplication app;
    EditText nameField;
    EditText emailField;
    EditText phoneField;
    ImageView profImg;

    Button uploadButton;
    Button submitButton;
    Button cancelButton;

    private Bitmap newBitmap;
    private boolean newImage;

    private Uri filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (RoomHubApplication)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_update_fragment, container, false);
        newImage = false;

        nameField = (EditText)rootView.findViewById(R.id.update_name_edittext);
        emailField = (EditText) rootView.findViewById(R.id.update_email_edittext);
        phoneField = (EditText) rootView.findViewById(R.id.update_phone_edittext);
        profImg = (ImageView) rootView.findViewById(R.id.update_prof_imageview);
        uploadButton = (Button) rootView.findViewById(R.id.update_upload_button);
        submitButton = (Button) rootView.findViewById(R.id.update_submit_button);
        cancelButton = (Button) rootView.findViewById(R.id.update_cancel_button);

        nameField.setHint(app.getUserName());
        emailField.setHint(app.getUserEmail());
        phoneField.setHint(app.getUserPhone());

        //Bitmap img = getResizedBitmap(app.getUserImage(), 168, 168);
        profImg.setImageBitmap(Bitmap.createScaledBitmap(app.getUserImage(), 168, 168, false));
        //profImg.setImageBitmap(img);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: go to users images, then set the users profile image with the selected image
                //save image to db in app
                showFileChooser();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //TODO: commit changes (only those that have changed)
                //do this in app!



                String name = nameField.getText().toString();
                if(!name.trim().equals("")){
                    app.setUserName(name);
                }

                String email = emailField.getText().toString();
                if(!email.trim().equals("")) {
                    app.setUserEmail(email);
                }

                String phone = phoneField.getText().toString();
                if(!phone.trim().equals("")) {
                    app.setUserPhone(phone);
                }

                if(newImage) {
                    app.setUserImage(newBitmap);
                    newImage = !newImage;
                }

                getActivity().onBackPressed();
            }
        });




        return rootView;
    }

/*    public static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth, float newHeight) {


        //Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        //profileImage.setImageBitmap(Bitmap.createScaledBitmap(b, 120, 120, false));


        if(bitmapToScale == null)
            return null;
        int width = bitmapToScale.getWidth();
        int height = bitmapToScale.getHeight();

        Matrix matrix = new Matrix();

        matrix.postScale(newWidth / width, newHeight / height);

        return Bitmap.createBitmap(bitmapToScale, 0, 0, bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix, true);  }*/


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }



    /*
     * This is the method responsible for image upload
     * We need the full image path and the name for the image in this method
     * */
    public void uploadMultipart() {

        //TODO is this needed?


        //getting name for the image
        //String name = editText.getText().toString().trim();

        //getting the actual path of the image
/*        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                newBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);

                //newBitmap = getResizedBitmap(newBitmap, 168, 168);
                //profImg.setImageBitmap(scaleBitmap(newBitmap, 168, 168));
                profImg.setImageBitmap(Bitmap.createScaledBitmap(newBitmap, 168, 168, false));
                app.setUserImage(newBitmap);
                newImage = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
