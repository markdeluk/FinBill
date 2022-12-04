package com.marco.finbill.ui.backup;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marco.finbill.R;

import java.util.Arrays;
import java.util.List;

public class BackupFragment extends Fragment {

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(new FirebaseAuthUIActivityResultContract(), this::onSignInResult);
    private FirebaseAuth mAuth;
    private List<AuthUI.IdpConfig> providers;
    private Intent signInIntent;

    public BackupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        // Choose authentication providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());

        // Create and launch sign-in intent
        signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.piggybank)
                .setTheme(R.style.Theme_FinBill)
                .setIsSmartLockEnabled(true)
                .build();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_backup_before_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Log.e("BackupFragment", "Login button clicked");
            signInLauncher.launch(signInIntent);
        });
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            getLayoutInflater().inflate(R.layout.fragment_backup_after_login, (ViewGroup) getView());
        } else {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.need_login);
            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            getLayoutInflater().inflate(R.layout.fragment_backup_after_login, (ViewGroup) getView());
        }
        else {
            getLayoutInflater().inflate(R.layout.fragment_backup_before_login, (ViewGroup) getView());
        }
    }

}