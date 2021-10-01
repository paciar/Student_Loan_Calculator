package com.example.studentloancalculator;

public class LoanInfo {

    private double mLoanAmt, mIntRate, mMinPayment, mLoanFeePercent = 1.06; // loan fee is 1.06%
    private double mLoanFee, mTotalLoan, mMonthlyPayment, mTotalInterest, mTotalPayment;
    private int mLoanTerm, mNumPayments;

    public LoanInfo(double loanAmt, double intRate, double minPayment, int loanTerm) {
        mLoanAmt = loanAmt; // loan user is taking out
        mIntRate = intRate; // interest rate of the loan
        mMinPayment = minPayment; // minimum monthly payment required by loaner
        mLoanTerm = loanTerm; // loan term in years
    }

    public double calculatePayment() {
        mLoanFee = mLoanAmt * (mLoanFeePercent / 100);
        mTotalLoan = mLoanAmt + mLoanFee; // assuming the user cannot pay the loan fee, it is added onto the total loan
        mNumPayments = mLoanTerm * 12;
        mTotalPayment = mTotalLoan * Math.pow(1 + (mIntRate/100),mLoanTerm);
        mMonthlyPayment = mTotalPayment/mNumPayments;
        if (mMinPayment > mMonthlyPayment) {
            mMonthlyPayment = mMinPayment;
            mNumPayments = (int) Math.ceil(mTotalPayment / mMonthlyPayment);
            /*
            must reduce mNumPayments using the ceiling function (rounds up to nearest int) because
            monthly rate will be higher than originally calculated (meaning there should be less payments)
            */
        }
        return mMonthlyPayment;
    }

    public double calculateTotalInterest() { // total interest would be the extra cost on top of the total loan
        mTotalInterest = mTotalPayment - mTotalLoan;
        return mTotalInterest;
    }

    public int getNumPayments() {
        return mNumPayments;
    }
}
