package com.example.studentloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button CalculateButton;
    private EditText editLoanAmt, editIntRate, editMinPayment, editLoanTerm;
    private TextView monthlyPayment, totalInterest, numPayments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();

        CalculateButton = (Button) findViewById(R.id.calculate_button);
    }

    public void addListenerOnButton() {
        editLoanAmt = (EditText) findViewById(R.id.editLoanAmt); // takes user input from EditText
        editIntRate = (EditText) findViewById(R.id.editIntRate);
        editMinPayment = (EditText) findViewById(R.id.editMinPayment);
        editLoanTerm = (EditText) findViewById(R.id.editLoanTerm);

        CalculateButton = (Button) findViewById(R.id.calculate_button); // calculate button

        monthlyPayment = (TextView) findViewById(R.id.monthly_payment); // displays monthly payment, total interest, and # of payments
        totalInterest = (TextView) findViewById(R.id.total_interest);
        numPayments = (TextView) findViewById(R.id.num_payments);

        CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double loanAmt = Double.parseDouble(editLoanAmt.getText().toString());
                double intRate = Double.parseDouble(editIntRate.getText().toString());
                double minPayment = Double.parseDouble(editMinPayment.getText().toString());
                int loanTerm = Integer.parseInt(editLoanTerm.getText().toString());

                LoanInfo newLoan = new LoanInfo(loanAmt, intRate, minPayment, loanTerm);

                Double tempPayment = newLoan.calculatePayment();
                Double tempInterest = newLoan.calculateTotalInterest();
                int tempNumPayments = newLoan.getNumPayments();
                monthlyPayment.setText("Monthly payment: $" + String.format("%.2f",tempPayment)); // rounds to nearest tenths place
                totalInterest.setText("Total interest: $" + String.format("%.2f",tempInterest));
                numPayments.setText("# of payments: " + tempNumPayments);
            }
        });
    }

}