#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "CreditCard.h"

CreditCard* createCreditCard(const char *cardNumber, const char *cardHolderName, const char *expirationDate) {
    CreditCard *card = (CreditCard*) malloc(sizeof(CreditCard));
    if (card) {
        strcpy(card->cardNumber, cardNumber);
        strcpy(card->cardHolderName, cardHolderName);
        strcpy(card->expirationDate, expirationDate);
    }
    return card;    }

void printCreditCard(CreditCard card) {
    printf("Número de Tarjeta: %s\n", card.cardNumber);
    printf("Titular: %s\n", card.cardHolderName);
    printf("Fecha de Expiración: %s\n", card.expirationDate);
    printf("----------------------------------------\n");    }