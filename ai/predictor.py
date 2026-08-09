import pandas as pd
import joblib


model = joblib.load("models/fraud_model.pkl")


def predict_risk(amount, transaction_amount_level, tax_haven_risk_level, transaction_type):
    transaction = pd.DataFrame([{
        "amount": amount,
        "transactionAmountLevel": transaction_amount_level,
        "taxHavenRiskLevel": tax_haven_risk_level,
        "transactionType": transaction_type
    }])
    return model.predict_proba(transaction)[0][1]