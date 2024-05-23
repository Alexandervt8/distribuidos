#ifndef CREDITCARD_H
#define CREDITCARD_H
typedef struct {
    char cardNumber[20];
    char cardHolderName[50];
    char expirationDate[15];
} CreditCard;
CreditCard* createCreditCard(const char *cardNumber, const char *cardHolderName, const char *expirationDate);
void printCreditCard(CreditCard card);
#endif