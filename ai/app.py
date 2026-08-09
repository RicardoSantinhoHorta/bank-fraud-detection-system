from fastapi import FastAPI
from pydantic import BaseModel

from predictor import predict_risk


app = FastAPI()

class TransactionRequest(BaseModel):
    amount: float
    transactionAmountLevel: str
    taxHavenRiskLevel: str
    transactionType: str


@app.post("/predict")
def predict(transaction: TransactionRequest):

    risk_score = predict_risk(
        transaction.amount,
        transaction.transactionAmountLevel,
        transaction.taxHavenRiskLevel,
        transaction.transactionType
    )

    return {
        "riskScore": risk_score
    }