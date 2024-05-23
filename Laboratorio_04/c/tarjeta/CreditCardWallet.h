#ifndef CREDITCARDWALLET_H
#define CREDITCARDWALLET_H
#include "CreditCard.h"
typedef struct {
    CreditCard **cards; // Array de punteros a CreditCard
    int size;           // Número de tarjetas en el monedero
} CreditCardWallet;
CreditCardWallet* createWallet();
void addCreditCard(CreditCardWallet *wallet, CreditCard *card);
void printAllCreditCards(CreditCardWallet *wallet);
#endif