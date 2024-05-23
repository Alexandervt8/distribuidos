#include <stdlib.h>
#include <stdio.h>
#include "CreditCardWallet.h"
CreditCardWallet* createWallet() {
    CreditCardWallet *wallet = (CreditCardWallet*)malloc(sizeof(CreditCardWallet));
    wallet->cards = NULL;
    wallet->size = 0;
    return wallet;
}
void addCreditCard(CreditCardWallet *wallet, CreditCard *card) {
    wallet->cards = (CreditCard**)realloc(wallet->cards, (wallet->size + 1) * sizeof(CreditCard*));
    wallet->cards[wallet->size] = card;
    wallet->size++;    }
void printAllCreditCards(CreditCardWallet *wallet) {
    for (int i = 0; i < wallet->size; i++) {
        printf("Número de Tarjeta: %s\n", wallet->cards[i]->cardNumber);
        printf("Titular: %s\n", wallet->cards[i]->cardHolderName);
        printf("Fecha de Expiración: %s\n", wallet->cards[i]->expirationDate);
        printf("----------------------------------------\n");
    }
}