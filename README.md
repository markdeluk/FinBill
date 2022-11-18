![alt text](money.png?raw=true "Title")  
# FinBill
Yet another app to manage expenses.

There are already some applications available on Google Play Store that provide lots of features regarding expense management, but sometimes they are difficult to use, and/or you have to pay a subscription for an app that should even manage... subscriptions!  
This project aims to propose a free and easy-to-use accounting application which tracks personal expenses dividing them by category, also recording recurrent ones (like subscriptions, bills, taxes…), and consequently calculate expenditure forecasts for next week, month, year...
  
Features:
- [ ] Expense/entrance insertion giving name, category, amount, notes
- [ ] Total and by-category expense calculation
- [ ] Recurrent expenses management
- [ ] Expenditure forecasts
- [ ] Depreciation of long-term expenses
  
Remember to do it!
- [ ] Multi-language translation (at least English, Danish, German, Italian, French, Spanish)


# To save my life!!!

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragmentContainerView"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:defaultNavHost="true"
        app:navGraph="@navigation/navigation"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/toolbar" />
    
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
    navController = navHostFragment.getNavController();